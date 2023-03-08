package visible;

import biuoop.DrawSurface;
import collision.Velocity;
import level.GameLevel;
import shape.Point;
import shape.Line;

import java.awt.Color;

/**
 * Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Ball implements Sprite {
    private double x;
    private double y;
    private final int r;
    private final java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private static final int PADDLE_LENGTH = 7;

    /**
     * Constructor for the ball.
     *
     * @param center point of the center of the ball.
     * @param r      the radius.
     * @param color  his color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.x = center.getX();
        this.y = center.getY();
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * Constructor for the ball.
     *
     * @param x     x coordinate.
     * @param y     y coordinate.
     * @param r     the radius.
     * @param color his color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * @return this.x - x value.
     */
    public int getX() {
        return (int) this.x;
    }

    /**
     * @return this.y - y value.
     */
    public int getY() {
        return (int) this.y;
    }

    /**
     * @return point of the center of the ball.
     */
    public Point getCenter() {
        return new Point(this.x, this.y);
    }

    /**
     * @param x new x coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y new y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return this.r - the radius value.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return this.color - the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface surface object.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * set a velocity object.
     *
     * @param v new velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * set a velocity object by dx & dy parameters.
     *
     * @param dx - the change in x coordinate.
     * @param dy - the change in y coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Set the game environment of the ball.
     *
     * @param gameEnvironment - game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * The function move a ball.
     */
    public void moveOneStep() {
        Point start = new Point(this.getCenter().getX(), this.getCenter().getY());
        Point end = this.getVelocity().applyToPoint(this.getCenter());
        Line trajectory = new Line(start, end);
        if (this.gameEnvironment == null) {
            this.setX(end.getX());
            this.setY(end.getY());
        }
        if (this.gameEnvironment.getClosestCollision(trajectory) == null) {
            this.setX(end.getX());
            this.setY(end.getY());
        } else {
            Line collisionLine = new Line(start, this.gameEnvironment.getClosestCollision(trajectory).collisionPoint());
            Point launch = (new Line(collisionLine.middle(), start)).middle();

            // if it hit the paddle and stuck in it, the ball will escape up.
            if (collisionLine.start().almostEqual(collisionLine.end()) && this.gameEnvironment.getClosestCollision(
                    trajectory).collisionObject().getCollisionRectangle().getHeight() == PADDLE_LENGTH) {
                this.setX(launch.getX());
                this.setY(launch.getY() - 3);
            } else {
                this.setX(launch.getX());
                this.setY(launch.getY());
                this.setVelocity(this.gameEnvironment.getClosestCollision(trajectory).collisionObject().hit(this,
                        this.gameEnvironment.getClosestCollision(trajectory).collisionPoint(), this.getVelocity()));
            }
        }
    }

    /**
     * Notify the ball that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Charge of adding the ball to the game.
     *
     * @param g the game class.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove a block from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}