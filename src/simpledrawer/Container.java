/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author ma8521e
 *
 * The container object is of a rectangular shape and holds the different
 * entities that can be drawn. The different entities hook into the the
 * container object (which is basically a rectangle) and makes it easier to
 * resize them and move them around. Any shape can be contained in a container
 * if it is drawable. This container will avoid having to implement
 * moving/resizing/rotating methods for every single entity
 *
 * ########## # # # Entity # # # ##########
 */
public class Container implements DrawableI, InteractiveShape {

    Entity entityContained;
    Point containerOrigin = null;
    int containerHeight;
    int containerWidth;
    boolean selected = false;
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public Container(Entity entityContained, Point containerOrigin, int width, int height) {
        this.entityContained = entityContained;
        entityContained.structuralPoints.set(0, new Point(containerOrigin.x + 5, containerOrigin.y + 5));
        this.containerOrigin = containerOrigin;
        this.containerHeight = entityContained.getHeight() + 10;
        this.containerWidth = entityContained.getWidth() + 10;
    }

    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(containerHeight, containerWidth);
        rectangle.setLocation(this.entityContained.getStructuralPoints().get(0));
        if (rectangle.contains(p)) {
            doesIt = true;
        }
        return doesIt;
    }

    @Override
    public Container translate(List<Point> old, Point offset) {
        int offsetX = offset.x;
        int offsetY = offset.y;
        for (int i = 0; i < old.size(); i++) {
            Point point = old.get(i);
            this.entityContained.structuralPoints.set(i, new Point(point.x + offsetX + 5, point.y + offsetY + 5));
        }
        this.containerOrigin = new Point(old.get(0).x + offsetX, old.get(0).y + offsetY);
        return this;
    }

    @Override
    public Container resize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    All the current Entity Types that our program holds should be here
     */
    public static enum EntityTypes {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    public void contain(Entity entity) {
        this.entityContained = entity;
    }

    public Entity getContained() {
        return this.entityContained;
    }

    public boolean Select() {
        return selected = true;
    }

    public boolean deSelect() {
        return selected = false;
    }

    public Rectangle getDrawableContainer() {
        Rectangle rect = new Rectangle();
        rect.setLocation(containerOrigin);
        rect.height = containerHeight;
        rect.width = containerWidth;
        return rect;
    }

    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        g2d.setStroke(dashed);
        g2d.setColor(Color.blue);
        if(selected){
        g2d.draw(getDrawableContainer());
        }
        entityContained.drawShape(g2d, currentBrightness);
    }
}
