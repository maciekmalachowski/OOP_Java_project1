package projekt;

import javax.swing.*;
import java.awt.*;
// The class is abstract,
// hence new Shape(..) won't work
abstract public class Shape {
    protected String name;
    // We will need color in draw()
    protected Color color;
    // We will remember the computed value
    private Double area = null;
    // We will remember the computed value
    private Double perimeter = null;
    // The center of a figure
    Point center;
    // True for uninitialized object
    protected boolean isEmpty = false;
    // We will use empty objects to create // JComboBox

    public Shape() {
        isEmpty = true;
    }

    public Shape(Color color, Point center) {
        this.color = color;
        this.center = center;
    }

    public double getArea() {
        if (area == null) {
            area = computeArea();
        }
        return area;
    }

    public double getPerimeter() {
        if (perimeter == null) {
            perimeter = computePerimeter();
        }
        return perimeter;
    }

    public void initialize(JFrame frame, Color color, Point center) {
        this.color = color;
        this.center = center;
        getExtraData(frame);
        isEmpty = false;
    }

    abstract protected void getExtraData(JFrame frame);

    abstract public void draw(Graphics2D g2d);

    abstract protected double computeArea();

    abstract protected double computePerimeter();

    abstract public Point getCorner();

    public String toString() {
        String str = name;
        if (!isEmpty) {
            str += ", area: " + (int) getArea();
            str += "\n[" + printColor(color) + "]";
            str += "\nCenter: " + center;
        }
        return str;
    }

    public static String printColor(Color color) {
        return "rgb " + color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}
