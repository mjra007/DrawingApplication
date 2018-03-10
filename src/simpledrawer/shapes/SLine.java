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
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.List;

public class SLine extends Shape {

    //To draw a line you only need a starting point and end point
    public SLine(Point origin, int width, int height, Color c, int t, int pR, Entity.EntityType et) {
        super(origin,width,height, c, t, 2, Entity.EntityType.LINE);
    }

    public SLine(){
       super(new Point(1,0),100,100, Color.black, 5, 2, Entity.EntityType.LINE);
    }
    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getXEnd() {
        return this.getStructuralPoints().get(1).x;
    }

    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getYEnd() {
        return this.getStructuralPoints().get(1).y;
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // scale the brightness of the colour
        //Color c = scaleColour(getColor(), currentBrightness);
        g2d.setColor(super.getColor());
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        // draw the line
        g2d.drawLine(getX(), getY(), this.getX()+getWidth(), this.getY()+getHeight());
    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX() + "Last " + this.getXEnd() + ", " + this.getYEnd();
    }

    @Override
    public void setOrigin(Point newPoint) {
        super.getStructuralPoints().set(0, newPoint);
    }

}
