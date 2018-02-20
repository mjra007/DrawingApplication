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
package simpledrawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class Container implements DrawableI, InteractiveShape {

    //Points required to draw the shape contained
    Entity contained = null;
    boolean selected = false;
    boolean selectedCorners = false;
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public Container(Entity contained) {
        this.contained = contained;
    }

    //returns the reorganized coords for the container
    public List<Point> getReorganizedCoords() {
        return Utils.getReorganizedCoords(contained.getStructuralPoints().get(0), contained.getStructuralPoints().get(1));
    }

    public Point getOrigin() {
        return new Point(getReorganizedCoords().get(0).x - 5, getReorganizedCoords().get(0).y - 5);
    }

    public int getWidth() {
        return (getReorganizedCoords().get(1).x - getReorganizedCoords().get(0).x) + 10;
    }

    public int getHeight() {
        return (getReorganizedCoords().get(1).y - getReorganizedCoords().get(0).y) + 10;
    }

    public Point getEndPoint() {
        return new Point(getWidth() + getOrigin().x, getHeight() + getOrigin().y);
    }

    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(getOrigin(), getEndPoint());
        if (rectangle.contains(p)) {
            doesIt = true;
        }
        return doesIt;
    }

    @Override
    public Container translate(List<Point> old, Point offset) {
        for (int i = 0; i < old.size(); i++) {
            Point oldPoint = old.get(i);
            this.contained.getStructuralPoints().set(i, new Point(oldPoint.x + offset.x, oldPoint.y + offset.y));
        }
        return this;
    }

    @Override
    public Container resize(List<Point> old,Point offSet) {

            Point oldPoint = old.get(1);
            
            this.contained.getStructuralPoints().set(1, new Point( offSet.x, offSet.y));
       
        return this;
    }

    @Override
    public boolean containsBottomRightCorner(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getEndPoint().x - 10, getEndPoint().y - 10), new Point(getEndPoint().x + 10, getEndPoint().y + 10));
        if (rectangle.contains(p)) {
            doesIt = true;
            System.out.println("bottomright");
        }
        return doesIt;
    }

    @Override
    public boolean containsBottomLeftCorner(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getOrigin().x - 10, getEndPoint().y - 10), new Point(getOrigin().x + 10, getEndPoint().y + 10));
        if (rectangle.contains(p)) {
            doesIt = true;
            System.out.println("bottomleft");
        }
        return doesIt;
    }

    @Override
    public boolean containsTopRightCorner(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getEndPoint().x - 10, getOrigin().y - 10), new Point(getEndPoint().x + 10, getOrigin().y + 10));
        if (rectangle.contains(p)) {
            doesIt = true;
            System.out.println("topright");
        }
        return doesIt;
    }

    @Override
    public boolean containsTopLeftCorner(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getOrigin().x - 10, getOrigin().y - 10), new Point(getOrigin().x + 10, getOrigin().y + 10));
        if (rectangle.contains(p)) {
            doesIt = true;
            System.out.println("topleft");
        }
        return doesIt;
    }

    /*
    All the current Entity Types that our program holds should be here
     */
    public static enum EntityTypes {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    public void contain(Entity entity) {
        this.contained = entity;
    }

    public Entity getContained() {
        return this.contained;
    }

    public boolean Select() {
        return selected = true;
    }

    public boolean deSelect() {
        return selected = false;
    }

    public boolean SelectCorners() {
        return selectedCorners = true;
    }

    public boolean deSelectCorners() {
        return selectedCorners = false;
    }

    public void drawResizeIndicator(Graphics2D g2d, Point point) {
        g2d.drawOval(point.x - 5, point.y - 5, 10, 10);
    }

    public Rectangle getDrawableContainer() {
        Rectangle rect = new Rectangle();
        rect.setLocation(getOrigin());
        rect.height = getHeight();
        rect.width = getWidth();
        return rect;
    }

    /*  public  getDrawableCorner(Point origin){
        
    }*/
    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        g2d.setStroke(dashed);
        g2d.setColor(Color.blue);
        if (selected) {
            g2d.draw(getDrawableContainer());
        }
        contained.drawShape(g2d, currentBrightness);
        g2d.setColor(Color.MAGENTA);
        if (selectedCorners) {
            drawResizeIndicator(g2d, new Point(getOrigin()));
            drawResizeIndicator(g2d, new Point(getEndPoint()));
            drawResizeIndicator(g2d, new Point(getEndPoint().x, getOrigin().y));
            drawResizeIndicator(g2d, new Point(getOrigin().x, getEndPoint().y));
        }
    }

}
