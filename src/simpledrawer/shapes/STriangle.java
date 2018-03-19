package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;


public class STriangle extends Shape {

    public STriangle(Shape shape) {
        super(shape);    
    }
    
    @Override
    public void drawShape(Graphics2D g2d) {
        // Scale the brightness of the colour
        // Color c = scaleColour(getColor());

        Point top = new Point(getOrigin().x + getWidth() / 2, getOrigin().y);
        Point right = new Point(getOrigin().x, getOrigin().y + getHeight());
        Point left = new Point(getOrigin().x + getWidth(), getOrigin().y + getHeight());
        // Draw the triangle
        int[] xx = new int[]{top.x, right.x, left.x};
        int[] yy = new int[]{top.y, right.y, left.y};
        if (super.isItFilled()) {
            g2d.setColor(filledColor);
            g2d.fillPolygon(xx, yy, 3);
        }
        g2d.setColor(super.getColor());
        // Set the thickness of the line
        g2d.setStroke(new BasicStroke(getThickness()));
        g2d.drawPolygon(xx, yy, 3);

    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX();
    }


}
