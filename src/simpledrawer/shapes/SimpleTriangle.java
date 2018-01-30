/*
 * SimpleTriangle.java
 *
 * @author Gill Windall
 *
 * Represents a triangle that can be drawn on a drawing area
 *
 */
package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.List;
import simpledrawer.Drawer;
import simpledrawer.InteractiveShape;
import simpledrawer.Shape;

public class SimpleTriangle extends Shape implements Drawer, InteractiveShape{

    public SimpleTriangle(List<Point> p, Color c, int t, ShapeType st) {
        super(p, c, t, st);
    }
    /**
     *
     * @return the area in pixels of the triangle. Does this work okay?
     */
    public double getArea() {
        List<Point> vertices = super.getVertices();
        int term1 = vertices.get(0).x * (vertices.get(1).y - vertices.get(2).y);
        int term2 = vertices.get(1).x * (vertices.get(2).y - vertices.get(0).y);
        int term3 = vertices.get(2).x * (vertices.get(0).y - vertices.get(1).y);
        return Math.abs((term1 + term2 + term3) / 2.0);
    }

        /**
     *
     * @return the area in pixels of the triangle. Does this work okay?
     */
    public double getArea(List<Point> v) {
        List<Point> vertices = v;
        int term1 = vertices.get(0).x * (vertices.get(1).y - vertices.get(2).y);
        int term2 = vertices.get(1).x * (vertices.get(2).y - vertices.get(0).y);
        int term3 = vertices.get(2).x * (vertices.get(0).y - vertices.get(1).y);
        return Math.abs((term1 + term2 + term3) / 2.0);
    }  
    
    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(this.getColour(), currentBrightness);
        g2d.setColor(c);
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));

        // draw the triangle
        g2d.drawLine(this.getVertices().get(0).x, this.getVertices().get(0).y, this.getVertices().get(1).x, this.getVertices().get(1).y);
        g2d.drawLine(this.getVertices().get(2).x, this.getVertices().get(2).y, this.getVertices().get(1).x, this.getVertices().get(1).y);
        g2d.drawLine(this.getVertices().get(2).x, this.getVertices().get(2).y, this.getVertices().get(0).x, this.getVertices().get(0).y);    
    }

    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        double area = getArea();
        Point a= this.getVertice(0);
        Point b = this.getVertice(1);
        Point c = this.getVertice(2);
        double pab = getArea(Arrays.asList(p,a,b));
        double pbc = getArea(Arrays.asList(p,b,c));
        double pac = getArea(Arrays.asList(p,a,c));
        if(pab+pbc+pac == area)doesIt = true;
        return doesIt;    
    }
    
    @Override
    public Shape translate(List<Point> list,Point offset) {
        Shape shape =this;
        int offsetX = offset.x;
        int offsetY = offset.y;
        shape.setVertice(0, new Point(list.get(0).x + offsetX, list.get(0).y + offsetY));
        shape.setVertice(1, new Point(list.get(1).x+ offsetX, list.get(1).y + offsetY));
        shape.setVertice(2, new Point(list.get(2).x+ offsetX, list.get(2).y + offsetY));
        return shape;
    }
      
}
