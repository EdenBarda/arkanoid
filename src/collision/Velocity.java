package collision;

import shape.Point;

/**
 * velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Velocity {
    private final double dy;
    private final double dx;

    /**
     * the contractor for velocity.
     *
     * @param dx the change in velocity in x coordinate.
     * @param dy the change in velocity in y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * alternative way to define velocity (by angle & speed) while 0 is up.
     *
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle);
        double dx = (speed * Math.sin(radianAngle));
        double dy = -(speed * Math.cos(radianAngle));
        return new Velocity(dx, dy);
    }

    /**
     * return the dx value of this velocity.
     *
     * @return this.dx - dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * return the dy value of this velocity.
     *
     * @return this.dy - dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p point.
     * @return new point after the velocity change.
     */
    public Point applyToPoint(Point p) {
        p.setX(p.getX() + this.dx);
        p.setY(p.getY() + this.dy);
        return p;
    }
}