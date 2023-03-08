package visible;

import biuoop.DrawSurface;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
import level.GameLevel;
import shape.Rectangle;
import shape.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the block class.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final java.awt.Color color;

    /**
     * Constructor for block.
     *
     * @param point  the left-upper point of the block.
     * @param width  the width of the block.
     * @param height the height of the block.
     * @param color  the color of the block.
     */
    public Block(Point point, double width, double height, java.awt.Color color) {
        this.rectangle = new Rectangle(point, width, height);
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * @return the shape of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
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
        if (collisionPoint.isPointOnLine(this.rectangle.getUpperLine())
                || collisionPoint.isPointOnLine(this.rectangle.getBottomLine())) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionPoint.isPointOnLine(this.rectangle.getRightLine())
                || collisionPoint.isPointOnLine(this.rectangle.getLeftLine())) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * Draw the block on the surface.
     *
     * @param surface the game surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Notify the block that time has passed.
     */
    public void timePassed() {
    }

    /**
     * Charge of adding the block to the game.
     *
     * @param g the game class.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove a block from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a hit listener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a hit listener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify all the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}