package projekt;

import javax.swing.*;
import java.awt.Point;
import java.awt.*;

public class Circle extends Ellipse {

    public Circle() {
        super();
        name = "Circle";
    }

    public Circle(Color color, Point center,
                  double radius) {
        super(color, center, radius, radius);
        name = "Circle";
    }

    protected void getExtraData(JFrame frame) {
        String radiusInput = JOptionPane.showInputDialog(frame,
                "Enter radius ", "50");
        double radius = Double.parseDouble(radiusInput);
        this.ax1 = radius;
        this.ax2 = radius;
    }

    public Square getCircumscribedSquare(Color color) {
        return new Square(color, center, 2 * ax1);
    }

}



