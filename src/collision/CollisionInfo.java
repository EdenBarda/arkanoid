package collision;

import shape.Point;
import visible.Collidable;


/**
 * The class defines the collision info that object can get.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class CollisionInfo {
    private final Point point;
    private final Collidable collidable;

    /**
     * The constructor for CollisionInfo.
     *
     * @param collidable a collidable.
     * @param point      a point.
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.collidable = collidable;
        this.point = point;
    }

    /**
     * The point at which the collision occurs.
     *
     * @return the point.
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * The collidable object involved in the collision.
     *
     * @return the collidable.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}