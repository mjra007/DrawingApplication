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
import java.awt.RenderingHints;
import simpledrawer.Utils;

/**
 *
 * @author micael
 */
public class SRectangle extends Shape {


    public SRectangle(Point origin, int width, int height, Color c, int t, int pR, Entity.EntityType et) {
        super(origin,width,height, c, t, 2, Entity.EntityType.RECTANGLE);
    }

    public SRectangle() {
        super(new Point(1,0),100,100, Color.GRAY, 5, 2, Entity.EntityType.RECTANGLE);
    }

    /**
     * @return an int that is the x of the opposite point of the origin
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

    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // scale the brightness of the colour
        //Color c = scaleColour(getColor(), currentBrightness);
        g2d.setColor(super.getColor());
        // get start point
        // draw the line
        // set the thickness of the line                
        g2d.setStroke(new BasicStroke(this.getThickness()));
        g2d.drawRect(getX(), getY(), getWidth(), getHeight());
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
