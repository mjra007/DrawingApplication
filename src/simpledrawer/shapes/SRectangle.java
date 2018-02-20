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
import java.util.ArrayList;
import java.util.List;
import simpledrawer.Entity;
import simpledrawer.SShape;
import simpledrawer.Utils;


/**
 *
 * @author micael
 */
public class SRectangle extends SShape {

    int height;
    int width;

    public SRectangle(List<Point> p, Color c, int t, Entity.EntityType et) {
        super(Utils.getReorganizedCoords(p.get(0), p.get(1)), c ,t, 2 ,et);
    }

    public int getXEnd() {
        return getX() + getWidth();
    }

    public int getYEnd() {
        return getY() + getHeight();
    }


    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(getColor(), currentBrightness);
        g2d.setColor(c);
        // get start point
        // draw the line
        // set the thickness of the line                
        g2d.setStroke(new BasicStroke(this.getThickness()));
        g2d.drawRect(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public String toString(){
        return "Color: "+super.getColor()+" Thick: "+super.getThickness()+" Points: "+super.getStructuralPoints().toString()+" width: "+super.getWidth()+" height: "+super.getHeight()+"  "+super.getX()+" , "+super.getX()+"Last "+this.getXEnd()+", "+this.getYEnd();
    }
}
