package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;


public class STriangle extends Shape {

    public STriangle(Shape shape) {
        super(shape);    
    }

    /**
     * @return The area in pixels of the triangle.
     */
    public double getArea() {
        int term1 = super.getStructuralPoints().get(0).x * (super.getStructuralPoints().get(1).y - super.getStructuralPoints().get(2).y);
        int term2 = super.getStructuralPoints().get(1).x * (super.getStructuralPoints().get(2).y - super.getStructuralPoints().get(0).y);
        int term3 = super.getStructuralPoints().get(2).x * (super.getStructuralPoints().get(0).y - super.getStructuralPoints().get(1).y);
        return Math.abs((term1 + term2 + term3) / 2.0);
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
