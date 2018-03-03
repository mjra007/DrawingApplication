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
import java.util.List;

import simpledrawer.Utils;

public class SOval extends SShape {

    public SOval(List<Point> p , Color c, int t) {
        super(Utils.getReorganizedCoords(p.get(0), p.get(1)), c, t,2 , Entity.EntityType.OVAL);
    }
    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getXEnd() {
        return getX() + getWidth();
    }
    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getYEnd() {
        return getY() + getHeight();
    }

    /**
     *
     * @return the area in pixels of the ellipse. Does this always work?
     */
    public double getArea() {
        int line1 = getXEnd() - getX();
        int line2 = getYEnd() - getY();
        System.out.println(line1 + ", " + line2 + " " + Math.PI * line1 / 2 * line2 / 2);
        return Math.PI * line1 / 2 * line2 / 2;
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        // scale the brightness of the colour
      //  Color c = scaleColour(super.getColor(), currentBrightness);
        g2d.setColor(super.getColor());
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        
        // draw the oval        
        g2d.fillOval(getX(), getY(),
                getWidth(),
                getHeight());
    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX() + "Last " + this.getXEnd() + ", " + this.getYEnd();
    }

}
