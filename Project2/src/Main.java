import java.awt.Color;
import projekt.Circle;
import projekt.Ellipse;
import projekt.Point;
import projekt.Rectangle;
import projekt.Shape;

public class Main {
    public static void main(String[] args) {
        Circle c1=new Circle(Color.BLUE, new Point(20,20),10);
        System.out.println(c1);
        System.out.println(c1.getArea());
        System.out.println(c1.getPerimeter());
        System.out.println(c1.getCircumscribedSquare(Color.RED));
        Shape c2=new Circle(Color.BLUE, new Point(20,20),10);
        System.out.println(c2);
        //This won't compile, because c2 is declared as Shape.
        //c2.getCircumscribedSquare(Color.RED);
        //but this will compile ok.
        System.out.println(c2);
        Shape c3=((Circle) c2).getCircumscribedSquare(Color.RED);
        System.out.println(c3);
        Rectangle r=new Rectangle(Color.PINK,new Point(15,15),10,20);
        System.out.println(r);
        System.out.println(r.getCircumscribedCircle(Color.CYAN));
        System.out.println(new Ellipse(Color.YELLOW,new Point(10,10),20,30));
    }
}




