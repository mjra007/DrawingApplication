/*
 * SimpleOval.java
 *
 * @author Gill Windall
 *
 * Represents an oval that can be drawn on a drawing area
 *
 */
package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.Drawer;
import simpledrawer.InteractiveShape;
import simpledrawer.Shape;

public class SimpleOval extends Shape implements Drawer, InteractiveShape{


    public SimpleOval(Point startingPoint, Point endingPoint, Color c, int t, ShapeType st) {
        super(c, t, st);
        List<Point> listVertices = new ArrayList<>();
        listVertices.add(startingPoint);
        listVertices.add(endingPoint);
        super.setVertices(listVertices);
    }

    /**
     * @return the xBottomRight
     */
    public int getxBottomRight() {
        return super.getVertice(1).x;
    }

    /**
     * @param xBottomRight the xBottomRight to set
     */
    public void setxBottomRight(int xBottomRight) {
        //the second point on the list is the last point of the line
        Point point = super.getVertice(1);
        point.x = xBottomRight;
        super.setVertice(1, point);
    }

    /**
     * @return the yBottomRight
     */
    public int getyBottomRight() {
        return super.getVertice(1).y;
    }

    /**
     * @param yBottomRight the yBottomRight to set
     */
    public void setyBottomRight(int yBottomRight) {
        //the second point on the list is the last point of the line
        Point point = super.getVertice(1);
        point.y = yBottomRight;
        super.setVertice(1, point);    }

    public int getXStart() {
        return super.getVertice(0).x;
    }

    public void setXStart(int xStart) {
        //the second point on the list is the last point of the line
        Point point = super.getVertice(0);
        point.x = xStart;
        super.setVertice(0, point);    
    }

    public int getYStart() {
        return super.getVertice(0).y;
    }

    public void setYStart(int yStart) {
        //the second point on the list is the last point of the line
        Point point = super.getVertice(0);
        point.y = yStart;
        super.setVertice(0, point);    
    }
   /**
    * 
    * @return the area in pixels of the ellipse.  Does this always work?
    */
    public double getArea() {
        int line1 = this.getxBottomRight() - this.getXStart();
        int line2 = this.getyBottomRight() - this.getYStart();
        System.out.println(line1 + ", " + line2 + " " + Math.PI * line1/2 * line2/2);
        return Math.PI * line1/2 * line2/2;
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(this.getColour(), currentBrightness);
        g2d.setColor(c);
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));

        int xs = this.getXStart();
        int ys = this.getYStart();
        
        // draw the oval        
        g2d.drawOval(xs, ys,
                this.getxBottomRight() - xs ,
                this.getyBottomRight() - ys);    }

    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Ellipse2D.Double oval = new Ellipse2D.Double(this.getXStart(),this.getYStart(),this.getxBottomRight()-this.getXStart(),this.getyBottomRight()-this.getYStart());
        if(oval.contains(p)) doesIt = true;
        return doesIt;
    }
    
    @Override
    public Shape translate(List<Point> list,Point offset) {
        int offsetX = offset.x;
        int offsetY = offset.y;
        this.setVertice(0, new Point(list.get(0).x + offsetX, list.get(0).y + offsetY));
        this.setVertice(1, new Point(list.get(1).x+ offsetX, list.get(1).y + offsetY));
        return this;
    }
}
