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
import java.util.List;

public class SLine extends SShape {

    //To draw a line you only need a starting point and end point
    public SLine(List<Point> p, Color c, int t) {
        super(p,c ,t,2, Entity.EntityType.LINE);
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
        // scale the brightness of the colour
        //Color c = scaleColour(getColor(), currentBrightness);
        g2d.setColor(super.getColor());
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        // draw the line
        g2d.drawLine(getX(), getY(), getXEnd(), getYEnd());
    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX() + "Last " + this.getXEnd() + ", " + this.getYEnd();
    }

}
