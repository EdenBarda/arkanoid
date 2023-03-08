package visible;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Velocity;
import level.GameLevel;
import shape.Rectangle;
import shape.Point;
import shape.Line;

import java.awt.Color;

/**
 * Rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final int speed;
    private static final int WIDTH = 800;
    private static final int WALL_WIDTH = 25;

    /**
     * The constructor of Paddle.
     *
     * @param rectangle a rectangle.
     * @param keyboard  the game keyboard of gui.
     * @param speed     the paddles speed.
     */
    public Paddle(Rectangle rectangle, biuoop.KeyboardSensor keyboard, int speed) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * Set the new place of the paddle.
     *
     * @param newX the new coordinate of the upper left x.
     */
    private void setPlace(Double newX) {
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()), this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }

    /**
     * Move the paddle to hte one step left.
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() > WALL_WIDTH) {
            setPlace(this.rectangle.getUpperLeft().getX() - this.speed);
        } else {
            setPlace((double) WALL_WIDTH);
        }
    }

    /**
     * Move the paddle to hte one step right.
     */
    public void moveRight() {
        if (this.getCollisionRectangle().getUpperLeft().getX() < WIDTH - WALL_WIDTH - this.rectangle.getWidth()) {
            setPlace(this.rectangle.getUpperLeft().getX() + this.speed);
        } else {
            setPlace((double) WIDTH - this.rectangle.getWidth() - WALL_WIDTH);
        }
    }

    /**
     * Notify the paddle that time has passed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle on the surface.
     *
     * @param d the game surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.PINK);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) (this.rectangle.getWidth()), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Return the shape of the upper line of the paddle as a rectangle.
     *
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), 0);
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter          the hitter ball.
     * @return the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Cut the paddle to 5 different parts.
        double paddlePart = this.rectangle.getWidth() / 5;
        double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());
        Line firstLine = new Line(this.rectangle.getUpperLine().end().getX(),
                this.rectangle.getUpperLine().end().getY(), this.rectangle.getUpperLine().end().getX() + paddlePart,
                this.rectangle.getUpperLine().end().getY());
        Line secondLine = new Line(this.rectangle.getUpperLine().end().getX() + paddlePart,
                this.rectangle.getUpperLine().end().getY(),
                this.rectangle.getUpperLine().end().getX() + 2 * paddlePart,
                this.rectangle.getUpperLine().end().getY());
        Line thirdLine = new Line(this.rectangle.getUpperLine().end().getX() + 2 * paddlePart,
                this.rectangle.getUpperLine().end().getY(),
                this.rectangle.getUpperLine().end().getX() + 3 * paddlePart,
                this.rectangle.getUpperLine().end().getY());
        Line fourthLine = new Line(this.rectangle.getUpperLine().end().getX() + 3 * paddlePart,
                this.rectangle.getUpperLine().end().getY(),
                this.rectangle.getUpperLine().end().getX() + 4 * paddlePart,
                this.rectangle.getUpperLine().end().getY());
        Line fifthLine = new Line(this.rectangle.getUpperLine().end().getX() + 4 * paddlePart,
                this.rectangle.getUpperLine().end().getY(),
                this.rectangle.getUpperLine().start().getX(),
                this.rectangle.getUpperLine().end().getY());

        //Set the "hit" to every part of the paddle.
        if (collisionPoint.isPointOnLine(firstLine)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (collisionPoint.isPointOnLine(secondLine)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (collisionPoint.isPointOnLine(thirdLine)) {
            return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        }
        if (collisionPoint.isPointOnLine(fourthLine)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (collisionPoint.isPointOnLine(fifthLine)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else {
            return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game class.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}