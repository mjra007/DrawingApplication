/*
 * SimpleLine.java
 *
 * @author Gill Windall
 *
 * Represents a line that can be drawn on a drawing area
 *
 */
package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.Drawer;
import simpledrawer.InteractiveShape;
import simpledrawer.Shape;

public class SimpleLine extends Shape implements Drawer, InteractiveShape {

    //To draw a line you only need a starting point and end point
    public SimpleLine(Point startingPoint, Point endingPoint, Color c, int t, ShapeType st) {
        super(c, t, st);
        List<Point> listVertices = new ArrayList<>();
        listVertices.add(startingPoint);
        listVertices.add(endingPoint);
        super.setVertices(listVertices);
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
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        // get start point
        int xs = this.getXStart();
        int ys = this.getYStart();

        // draw the line
        g2d.drawLine(xs, ys, this.getXEnd(), this.getYEnd());
    }

    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Line2D.Double line = new Line2D.Double(this.getXStart(), this.getXEnd(), this.getYStart(), this.getYEnd());
        Rectangle rect = line.getBounds();
        if (rect.contains(p)) {
            doesIt = true;
        }
        return doesIt;
    }

    @Override
    public Shape translate(List<Point> list,Point offset) {
        Shape shape =null;
        int offsetX = offset.x;
        int offsetY = offset.y;
        shape.setVertice(0, new Point(list.get(0).x + offsetX, list.get(0).y + offsetY));
        shape.setVertice(1, new Point(list.get(1).x+ offsetX, list.get(1).y + offsetY));
        return shape;
    }

}
