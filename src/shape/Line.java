package shape;

import java.util.ArrayList;

/**
 * A line (actually a line-segment) connects two points - a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 *
 * @author Eden Barda, 208932202.
 * @version 1.0
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructor for line.
     *
     * @param start line's start point.
     * @param end   line's end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Defining the start point and the end point of the line.
     *
     * @param x1 start point x coordinate.
     * @param y1 start point y coordinate.
     * @param x2 end point x coordinate.
     * @param y2 end point y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates the middle point of the line ((x1 + x2)/2, (y1 + y2)/2).
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2; // Mid-section finding.
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return this.start - the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return this.end - the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the slope of the line - ((y1 - y2) / (x1 - x2) = m).
     *
     * @return the slope of the line.
     */
    public double slope() {
        return (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX()); // slope = (y1 - y2) / (x1 - x2).
    }

    /**
     * Find n in this equation - (y = x*m + n).
     *
     * @return n in this equation (y = x*m + n).
     */
    public double findN() {
        return (this.start.getY() - (this.slope() * this.start.getX())); // y = slope * x + n -> n = y - slope * x.
    }

    /**
     * Check if the line contained in other line.
     *
     * @param other other line.
     * @return if the line contained in the other one.
     */
    public boolean isContained(Line other) {
        if (this.slope() == other.slope() && this.findN() == other.findN()) {
            double x1 = this.start.getX();
            double x2 = this.end.getX();
            double y1 = this.start.getY();
            double y2 = this.end.getY();
            return ((((x1 >= other.start.getX() && x1 <= other.start.getX()) || (x1 <= other.start.getX()
                    && x1 >= other.start.getX())) && ((x2 >= other.start.getX() && x2 <= other.start.getX())
                    || (x2 <= other.start.getX() && x2 >= other.start.getX()))) && (((y1 >= other.start.getY()
                    && y1 <= other.start.getY()) || (y1 <= other.start.getY() && y1 >= other.start.getY()))
                    && ((y2 >= other.start.getY() && y2 <= other.start.getY()) || (y2 <= other.start.getY()
                    && y2 >= other.start.getY()))));
        } else {
            return false;
        }
    }

    /**
     * Checks if one of the lines parallels the X axis and the other the Y axis.
     *
     * @param other other line.
     * @return if it does.
     */
    private boolean isInterParallelAxis(Line other) {
        if ((this.start.getX() == this.end.getX() && other.start.getY() == other.end.getY())
                || (other.start.getX() == other.end.getX() && this.start.getY() == this.end.getY())) {
            return (((this.start.getX() <= other.start.getX() && this.end.getX() >= other.start.getX())
                    || (other.start.getX() <= this.start.getX() && other.end.getX() >= this.start.getX()))
                    && ((this.start.getY() <= other.start.getY() && this.end.getY() >= other.start.getY())
                    || (other.start.getY() <= this.start.getY() && other.end.getY() >= this.start.getY())));
        } else {
            return false;
        }
    }

    /**
     * Checks if both lines are parallels to the X axis.
     *
     * @param other other line.
     * @return if it does.
     */
    private boolean isParallelYAndSameLine(Line other) {
        Line l = new Line(this.middle(), other.middle());
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()
                && other.start.getX() == this.end.getX()) {
            return (l.length() >= ((this.length() + other.length()) / 2));
        } else {
            return false;
        }
    }

    /**
     * Checks if both lines are parallels to the Y axis.
     *
     * @param other other line.
     * @return if it does.
     */
    private boolean isParallelXAndSameLine(Line other) {
        Line l = new Line(this.middle(), other.middle());
        if (this.start.getY() == this.end.getY() && other.start.getY() == other.end.getY()
                && other.start.getY() == this.end.getY()) {
            return (l.length() >= ((this.length() + other.length()) / 2));
        } else {
            return false;
        }
    }

    /**
     * Check if the lines intersect.
     *
     * @param other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.isParallelYAndSameLine(other) || this.isParallelXAndSameLine(other)) {
            return true;
        }
        double x = (other.findN() - this.findN()) / (this.slope() - other.slope());
        double y = ((other.findN() * this.slope()) - (this.findN() * other.slope())) / (this.slope() - other.slope());
        if ((this.isContained(other)) || (other.isContained(this))) {
            return true;
        }
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            double theY = (other.slope() * this.start.getX()) + other.findN();
            return ((this.start().getY() <= theY && this.end.getY() >= theY
                    || this.end.getY() <= theY && this.start.getY() >= theY)
                    && (other.start().getY() <= theY && other.end.getY() >= theY
                    || other.end.getY() <= theY && other.start.getY() >= theY));
        }
        if (other.start.getX() == other.end.getX() && this.start.getX() != this.end.getX()) {
            double theY = (this.slope() * other.start.getX()) + this.findN();
            return ((other.start().getY() <= theY && other.end.getY() >= theY
                    || other.end.getY() <= theY && other.start.getY() >= theY)
                    && (this.start().getY() <= theY && this.end.getY() >= theY
                    || this.end.getY() <= theY && this.start.getY() >= theY));
        }
        if (this.start.getY() == this.end.getY() && other.start.getY() != other.end.getY()) {
            double theX = (this.start.getY() - other.findN()) / other.slope();
            return ((this.start().getX() <= theX && this.end.getX() >= theX
                    || this.end.getX() <= theX && this.start.getX() >= theX)
                    && (other.start().getX() <= theX && other.end.getX() >= theX
                    || other.end.getX() <= theX && other.start.getX() >= theX));
        }
        if (other.start.getY() == other.end.getY() && this.start.getY() != this.end.getY()) {
            double theX = (other.start.getY() - this.findN()) / this.slope();
            return ((other.start().getX() <= theX && other.end.getX() >= theX
                    || other.end.getX() <= theX && other.start.getX() >= theX)
                    && (this.start().getX() <= theX && this.end.getX() >= theX
                    || this.end.getX() <= theX && this.start.getX() >= theX));
        } else {
            if (this.slope() == other.slope() && this.findN() == other.findN()) {
                Line l = new Line(this.middle(), other.middle());
                return (l.length() >= ((this.length() + other.length()) / 2));
            }
            if (this.slope() == other.slope() && this.findN() != other.findN()) {
                return false;
            }
            return ((((x >= (this.start.getX()) && x <= (this.end.getX())) || (x <= (this.start.getX())
                    && x >= (this.end.getX()))) && ((y >= (this.start.getY()) && y <= (this.end.getY()))
                    || (y <= (this.start.getY()) && y >= (this.end.getY())))) && (((x >= (other.start.getX())
                    && x <= (other.end.getX())) || (x <= (other.start.getX()) && x >= (other.end.getX())))
                    && ((y >= (other.start.getY()) && y <= (other.end.getY())) || (y <= (other.start.getY())
                    && y >= (other.end.getY())))));
        }
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other line.
     * @return interPoint - the intersection point. or null.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        Line l = new Line(this.middle(), other.middle());
        if (this.isInterParallelAxis(other)) {
            if (this.start.getX() == this.end.getX()) {
                return new Point(this.start.getX(), other.start.getY());
            } else {
                return new Point(other.start.getX(), this.start.getY());
            }
        }
        if (this.isParallelXAndSameLine(other)) {
            if (l.length() == ((this.length() + other.length()) / 2)) {
                double commonX = this.end.getX();
                if (this.start.getX() == other.start.getX()) {
                    commonX = this.start.getX();
                }
                return new Point(commonX, this.start.getY());
            }
        }
        if (this.isParallelYAndSameLine(other)) {
            if (l.length() == ((this.length() + other.length()) / 2)) {
                double commonY = this.end.getY();
                if (this.start.getY() == other.start.getY()) {
                    commonY = this.start.getY();
                }
                return new Point(this.start.getX(), commonY);
            }
        }
        double x = (other.findN() - this.findN()) / (this.slope() - other.slope());
        double y = ((other.findN() * this.slope()) - (this.findN() * other.slope())) / (this.slope() - other.slope());
        if (this.isIntersecting(other)) {
            if ((this.isContained(other)) || (other.isContained(this))) {
                return null;
            }

            // checks if one line is Parallels to the Y axis.
            if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
                double commonY = (this.start.getX() * other.slope()) + other.findN();
                return new Point(this.start.getX(), commonY);
            }
            if (other.start.getX() == other.end.getX() && this.start.getX() != this.end.getX()) {
                double commonY = (other.start.getX() * this.slope()) + this.findN();
                return new Point(other.start.getX(), commonY);
            }

            // checks if one line is Parallels to the X axis.
            if (this.start.getY() == this.end.getY() && other.start.getY() != other.end.getY()) {
                double commonX = (this.start.getY() - other.findN()) / other.slope();
                return new Point(commonX, this.start.getY());
            }
            if (other.start.getY() == other.end.getY() && this.start.getY() != this.end.getY()) {
                double commonX = (other.start.getY() - this.findN()) / this.slope();
                return new Point(commonX, other.start.getY());
            } else {
                if (this.slope() == other.slope() && this.findN() == other.findN()) {
                    if (l.length() > ((this.length() + other.length()) / 2)) {
                        return null;
                    } else {
                        if (this.start.getX() == other.start.getX()) {
                            return new Point(this.start.getX(), this.start.getY());
                        } else {
                            return new Point(this.end.getX(), this.end.getY());
                        }
                    }
                }
                if (this.start.getX() == this.end.getX() || other.start.getX() == other.end.getX()) {
                    return null;
                } else {
                    return new Point(x, y);
                }
            }
        } else {
            return null;
        }
    }

    /**
     * @param other line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect a rectangle.
     * @return the closest intersection to the line's start point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).size() == 0) {
            return null;
        } else {
            ArrayList<Point> intersections = (ArrayList<Point>) rect.intersectionPoints(this);
            Point closestPoint = intersections.get(0);
            double minimalDistance = this.start.distance(closestPoint);
            for (Point i : intersections) {
                if (minimalDistance > this.start.distance(i)) {
                    minimalDistance = this.start.distance(i);
                    closestPoint = i;
                }
            }
            return closestPoint;
        }
    }
}