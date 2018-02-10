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
import java.util.ArrayList;
import simpledrawer.Entity;

public class SLine extends Entity {


    //To draw a line you only need a starting point and end point
    public SLine(Point startingPoint, Point endingPoint, int width, int height, Color c, int t, ShapeType st) {
        super(new ArrayList<Point>(){{add(startingPoint);add(endingPoint);}}, width ,height, c, t, st);
    }

    public int getX() {
        return this.getStructuralPoints().get(0).x;
    }

    public int getY() {
        return this.getStructuralPoints().get(0).y;
    }

    public int getXEnd(){
        return this.getStructuralPoints().get(1).x;
    }
    
    public int getYEnd(){
        return this.getStructuralPoints().get(1).y;
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(this.getColour(), currentBrightness);
        g2d.setColor(c);
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        // get start point


        // draw the line
        g2d.drawLine(getX(), getY(), getXEnd(), getYEnd());
    }



}
