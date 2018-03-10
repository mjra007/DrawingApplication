package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.List;

public class STriangle extends Shape {

    public STriangle(Point origin, int width, int height, Color c, int t,  Entity.EntityType et) {
        super(origin,width,height, c, t,  Entity.EntityType.TRIANGLE);
    }

    public STriangle() {
        super(new Point(1, 0),100,100, Color.GRAY, 5, Entity.EntityType.TRIANGLE);
    }

    /**
     * @return the area in pixels of the triangle. Does this work okay?
     */
    public double getArea() {
        int term1 = getVertices().get(0).x * (getVertices().get(1).y - getVertices().get(2).y);
        int term2 = getVertices().get(1).x * (getVertices().get(2).y - getVertices().get(0).y);
        int term3 = getVertices().get(2).x * (getVertices().get(0).y - getVertices().get(1).y);
        return Math.abs((term1 + term2 + term3) / 2.0);
    }

    public List<Point> getVertices() {
        return this.getStructuralPoints();
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        // Scale the brightness of the colour
        // Color c = scaleColour(getColor());
        g2d.setColor(super.getColor());
        // Set the thickness of the line
        g2d.setStroke(new BasicStroke(getThickness()));

        Point top = new Point(getOrigin().x + getWidth()/2, getOrigin().y);
        Point right = new Point(getOrigin().x, getOrigin().y + getHeight());
        Point left = new Point(getOrigin().x + getWidth(), getOrigin().y + getHeight());
        // Draw the triangle
        int[] xx = new int[]{top.x,right.x,left.x};
        int[] yy = new int[]{top.y,right.y,left.y};
        g2d.drawLine(top.x, top.y, right.x, right.y);
        g2d.drawLine(top.x, top.y, left.x, left.y);
        g2d.drawLine(right.x, right.y, left.x, left.y);
    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX();
    }

    @Override
    public void setOrigin(Point newPoint) {
        super.getStructuralPoints().set(0, newPoint);
    }
}
