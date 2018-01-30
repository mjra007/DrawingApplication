/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import simpledrawer.Drawer;
import simpledrawer.InteractiveShape;
import simpledrawer.Shape;

/**
 *
 * @author micael
 */
public class SimpleRectangle extends Shape implements Drawer, InteractiveShape {

    public SimpleRectangle(List<Point> p, Color c, int t, ShapeType st) {
        super(p, c, t, st);

    }
    
    public int getXEnd() {
        return super.getVertice(1).x;
    }

    public void setXEnd(int xEnd) {
        //the second point on the list is the last point of the line
        Point point = super.getVertice(1);
        point.x = xEnd;
        super.setVertice(1, point);
    }

    public int getYEnd() {
        return super.getVertice(1).y;
    }
    
    public void setYEnd(int yEnd) {
        //the second point on the list is the last point of the line
        Point point = super.getVertice(1);
        point.y = yEnd;
        super.setVertice(1, point);    
    }

    public int getWidth(){
        return this.getXEnd()-this.getXStart();
    }
    
    public int getHeight(){
        return this.getYEnd()-this.getYStart(); 
    }   
    public int getXStart() {
        return super.getVertice(0).x;
    }

    public void setXStart(int xStart) {
        //the first point on the list is the last point of the line
        Point point = super.getVertice(0);
        point.x = xStart;
        super.setVertice(0, point);    
    }

    public int getYStart() {
        return super.getVertice(0).y;
    }

    public void setYStart(int yStart) {
        //the first point on the list is the last point of the line
        Point point = super.getVertice(0);
        point.y = yStart;
        super.setVertice(0, point);       
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(this.getColour(), currentBrightness);
        g2d.setColor(c);
        // get start point
        int xs = this.getXStart();
        int ys = this.getYStart();
        int width = this.getXEnd()-xs;
        int height = this.getYEnd()-ys;
        // draw the line
                // set the thickness of the line                
        g2d.setStroke(new BasicStroke(this.getThickness()));
        g2d.drawRect(xs, ys, width , height);
    }

    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(this.getWidth(), this.getHeight());
        rectangle.setLocation(this.getVertices().get(0));
        if(rectangle.contains(p)) doesIt=true;
        return doesIt;
    }
    
        @Override
    public void translate(Point p) {
        Point pointA = this.getVertice(0);
        Point pointB = this.getVertice(1);
        pointA.translate(p.x, p.y);
        pointB.translate(p.x, p.y);
        this.setVertice(0, pointA);
        this.setVertice(1, pointB);
    }

}
