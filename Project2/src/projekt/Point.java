package projekt;


public class Point implements Cloneable {
    final public static double PRECISION = 0.001;

    // if we declare x and y final then
    // Point would be immutable
    // (Like String or wrappers)

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + (int) x + "," + (int) y + "]";
    }

    private boolean compare(double a, double b) {
        return (Math.abs(a - b) < PRECISION);
    }

    public boolean equals(Point obj) {
        return compare(x, obj.x) && compare(y, obj.y);
    }

    // In all arithmetic methods we try to
    // preserve the original object.
    public Point multiply(double k) {
        return new Point(x * k, y * k);
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point subtract(Point p) {
        return new Point(x - p.x, y - p.y);
    }

    public static Point midPoint(Point a, Point b) {
        return (a.add(b)).multiply(0.5);
    }

    public static Point vector(Point a, Point b) {
        return (b.subtract(a));
    }

    public double vecLength() {
        return Math.sqrt(x * x + y * y);
    }

    public Point rotate(double angle, Point center) {
        // not implemented yet - check Linear Algebra
        // notes for rotation formulas.
        // return new Point(..., ...);
        return null;
    }

    // This method is obligatory for Cloneable interface
    // By default (in Object) it makes a shallow copy
    // of object properties
    public Point clone() {
        return new Point(x, y);
        // we can also do it like this:
        // return (Point) super.clone(); // super=Object here
    }
}
