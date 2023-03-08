package shape;

import java.util.ArrayList;

/**
 * The class that defines rectangle.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Rectangle {
    private final double width;
    private final double height;
    private final Point upperLeft;


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper-left corner point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line that need to check his intersections with the rectangle.
     * @return intersections - list of points of intersections of the rectangle's border with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersections = new ArrayList<>();
        if (line.isIntersecting(this.getUpperLine())) {
            intersections.add(line.intersectionWith(this.getUpperLine()));
        }
        if (line.isIntersecting(this.getBottomLine())) {
            intersections.add(line.intersectionWith(this.getBottomLine()));
        }
        if (line.isIntersecting(this.getLeftLine())) {
            intersections.add(line.intersectionWith(this.getLeftLine()));
        }
        if (line.isIntersecting(this.getRightLine())) {
            intersections.add(line.intersectionWith(this.getRightLine()));
        }
        return intersections;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(upperRight, this.upperLeft);
    }

    /**
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(downLeft.getX() + this.width, downLeft.getY());
        return new Line(downLeft, downRight);
    }

    /**
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(upperRight.getX(), upperRight.getY() + this.height);
        return new Line(upperRight, downRight);
    }

    /**
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(downLeft, this.upperLeft);
    }
}