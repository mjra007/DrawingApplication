/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micae
 */
public class SShape extends Entity{
    
    Color colour;
    int thickness;

    public SShape(List<Point> p, Color c, int t,int pR, Entity.EntityType et){
        super(p, pR, et);
        colour=c;
        thickness=t;
    }   
    
    public int getX(){
        return super.getStructuralPoints().get(0).x;
    }
    
    public int getY(){
        return super.getStructuralPoints().get(0).y;
    }

    public Color getColor(){
        return this.colour;
    }
    
    public int getThickness(){
        return this.thickness;
    }
    
    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
