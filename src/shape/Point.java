package shape;

/**
 * A point has an x and a y value, and can measure the distance to other points.
 * and if it is equal to another point.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = 0.5;

    /**
     * Constructor for point.
     *
     * @param x gets x coordinate.
     * @param y gets y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the distance of this point to the other point.
     *
     * @param other point.
     * @return the distance between the 2 points.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Return true is the points are equal, false otherwise.
     *
     * @param other point.
     * @return if the points are equal or not.
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * Return true is the points are almost equal, false otherwise.
     *
     * @param other point.
     * @return if the points are equal or not.
     */
    public boolean almostEqual(Point other) {
        return (Math.abs(this.x - other.x) < EPSILON) && (Math.abs(this.y - other.y) < EPSILON);
    }

    /**
     * Return the x value of this point.
     *
     * @return this.x - x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return this.y - y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set new x.
     *
     * @param x new x coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set new y.
     *
     * @param y new y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Checks if the point is on the line With possible deviation of 1.5 pixels.
     *
     * @param l a line.
     * @return if it does.
     */
    public boolean isPointOnLine(Line l) {
        double a = Math.abs(this.distance(l.start()) + this.distance(l.end()) - l.length());
        double deviationRange = 0;
        return a <= deviationRange;
    }
}