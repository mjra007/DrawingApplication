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
import java.util.ArrayList;
import java.util.List;
import simpledrawer.InteractiveShape;
import simpledrawer.Entity;
import simpledrawer.DrawableI;

/**
 *
 * @author micael
 */
public class SRectangle extends Entity{

    int height;
    int width;
    
    public SRectangle(Point origin, int width, int height, Color c, int t, ShapeType st) {
        super(new ArrayList<Point>(){{add(origin);}},width,height,c, t, st);
    }
    
    public int getXEnd() {
        return getX() + this.getWidth();
    }


    public int getYEnd() {
        return getY() + this.getHeight();
    }
    
    public int getY(){
        return super.getStructuralPoints().get(0).y;
    }
    
    public int getX(){
        return super.getStructuralPoints().get(0).x;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getWidth(){
        return this.height;
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(this.getColour(), currentBrightness);
        g2d.setColor(c);
        // get start point
        // draw the line
                // set the thickness of the line                
        g2d.setStroke(new BasicStroke(this.getThickness()));
        g2d.drawRect(getX(), getY(), getWidth() , getHeight());
    }




}
