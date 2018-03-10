package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;

public class STriangle extends Shape {

    public STriangle(Point origin, int width, int height, Color c, int t, int pR, Entity.EntityType et) {
        super(origin,width,height, c, t, 2, Entity.EntityType.TRIANGLE);
    }

    public STriangle() {
        super(new Point(1, 0),100,100, Color.GRAY, 5, 3, Entity.EntityType.TRIANGLE);
    }

    /**
     *
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

    public int getWidth() {
        int finalwidth = 0;
        for (int i = 0; i < this.getStructuralPoints().size(); i++) {
            Point point1 = getStructuralPoints().get(i);
            int calculate = finalwidth - point1.x;
            if (finalwidth > calculate) {
                finalwidth = point1.x;
            }
        }
        return finalwidth;
    }

    public int getHeight() {
        int finalHeight = 0;
        for (int i = 0; i < this.getStructuralPoints().size(); i++) {
            Point point1 = getStructuralPoints().get(i);
            if (finalHeight < point1.y) {
                finalHeight = point1.y;
            }
        }
        return finalHeight;
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // Scale the brightness of the colour
        // Color c = scaleColour(getColor());
        g2d.setColor(super.getColor());
        // Set the thickness of the line
        g2d.setStroke(new BasicStroke(getThickness()));

        // Draw the triangle
        g2d.drawLine(this.getVertices().get(0).x, this.getVertices().get(0).y, this.getVertices().get(1).x, this.getVertices().get(1).y);
        g2d.drawLine(this.getVertices().get(2).x, this.getVertices().get(2).y, this.getVertices().get(1).x, this.getVertices().get(1).y);
        g2d.drawLine(this.getVertices().get(2).x, this.getVertices().get(2).y, this.getVertices().get(0).x, this.getVertices().get(0).y);
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
