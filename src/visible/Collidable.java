package visible;

import collision.Velocity;
import shape.Rectangle;
import shape.Point;

/**
 * The Collidable interface will be used by things that can be collided with.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter          the hitter ball.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}