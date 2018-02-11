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
import java.util.ArrayList;
import java.util.List;
import simpledrawer.Entity;

public class STriangle extends Entity{

    List<Point> vertices = new ArrayList<Point>();
    
    public STriangle(List<Point> p,int width , int height, Color c, int t, ShapeType st) {
        super(p,width ,height, c , t, st);
        this.vertices = p;
    }
    /**
     *
     * @return the area in pixels of the triangle. Does this work okay?
     */
    public double getArea() {
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
        int term1 = vertices.get(0).x * (vertices.get(1).y - vertices.get(2).y);
        int term2 = vertices.get(1).x * (vertices.get(2).y - vertices.get(0).y);
        int term3 = vertices.get(2).x * (vertices.get(0).y - vertices.get(1).y);
        return Math.abs((term1 + term2 + term3) / 2.0);
    }  
    
    public List<Point> getVertices(){
        return this.vertices;
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

}
