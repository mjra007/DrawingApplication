/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Arrays;
import simpledrawer.DrawableI;

/**
 *
 * @author micae
 */
public abstract class Shape extends Entity implements DrawableI,ContainerI {

    public int height;
    public int width;
    public int borderThickness;
    public final int pointsRequired;
    public boolean filled = false;
    //if not assigned let s just use the same as the border because why not
    public Color filledColor = super.getColor();

    /**
     *
     * @param origin origin of the shape - point where the user clicked first
     * @param width the width of the shape
     * @param height the height of the shape
     * @param c border color of the shape
     * @param t thickness of the border
     * @param pR points required to draw the shape
     * @param et EntityType of the shape
     */
    public Shape(Point origin, int width, int height, Color c, int t, int pR, Entity.EntityType et) {
        super(Arrays.asList(origin, new Point(origin.x + width, origin.y + height)), c, et);
        borderThickness = t;
        this.pointsRequired = pR;
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @return an integer that represents the no of points that entity requires.
     * Rectangle 2, Triangle 3 etc
     *
     */
    public int getPointsRequired() {
        return pointsRequired;
    }

    public int getX() {
        return super.getStructuralPoints().get(0).x;
    }

    public int getY() {
        return super.getStructuralPoints().get(0).y;
    }

    public Point getOrigin() {
        return super.getStructuralPoints().get(0);
    }

    public int getThickness() {
        return this.borderThickness;
    }

    /**
     *
     * @return endPointX - origiPointX you might want to override the method
     * depending on what shape your entity takes
     *
     */
    public int getWidth() {
        return this.width;
    }

    /**
     *
     * @return endPointY - origiPointY you might want to override the method
     * depending on what shape your entity takes
     *
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return endPointX - origiPointX you might want to override the method
     * depending on what shape your entity takes
     *
     */
    public void setWidth(int newWidth) {
        super.getStructuralPoints().set(1, new Point(getOrigin().x + newWidth, getOrigin().y));
        this.width = newWidth;
    }

    /**
     *
     * @return endPointY - origiPointY you might want to override the method
     * depending on what shape your entity takes
     *
     */
    public void setHeight(int newHeight) {
        super.getStructuralPoints().set(1, new Point(getOrigin().x, getOrigin().y+newHeight));
        this.height = newHeight;
    }

    /**
     * Select the shape should be set the shape to selected
     */
    @Override
    public void drawShape(Graphics2D g2d) {
        System.out.println("You forgot to implement the method to draw this Shape!");
    }



}
