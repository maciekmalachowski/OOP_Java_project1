package projekt;

import javax.swing.*;
import java.awt.*;

abstract public class Shape {
    protected String name;
    protected Color color;
    private Double area = null;
    private Double perimeter = null;
    Point center;
    protected boolean isEmpty = false;

    public Shape(){
        isEmpty = true;
    }

    public Shape(Color color, Point center){
        this.color = color;
        this.center = center;
    }

    public double getArea() {
        if (area == null){
            area = computeArea();
        }
        return area;
    }

    public double getPerimeter() {
        if (perimeter == null){
            perimeter = computePerimeter();
        }
        return perimeter;
    }

    public void initialize(JFrame frame, Color color, Point center){
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

    public String toString(){
        String str = name;
        if (!isEmpty){
            str += ", area: " + (int)getArea();
            str += "\n[" + printColor(color) + "], ";
            str += "center: " + center;
        }
        return str;
    }

    public String printColor(Color color){
        return "rgb " + color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
    }
}
