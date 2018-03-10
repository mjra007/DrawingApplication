/**
 *
 * @author ma8521e
 *
 * The container object is of a rectangular shape and holds the different
 * entities that can be drawn. The container hooks into the different entities
 * (which is basically a rectangle) and makes it easier to
 * resize them and move them around. Any entity that only requires an origin point, width and height
 * can be contained in a container
 *
 */
package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import simpledrawer.DrawableI;
import simpledrawer.InteractiveShape;

public class Container implements DrawableI, InteractiveShape {

    //Points required to draw the shape contained
    ContainerI contained = null;
    final static float dash1[] = {8.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

    public Container(ContainerI contained) {
        this.contained = contained;
    }


    /**
     * would probably be better not to do these claculations at runtime, and
     * just change the origin and other points when there is a change.
     */
    /**
     *
     * @return the origin point of the rectangle
     */
    public Point getOrigin() {
        return new Point(contained.getOrigin().x- 5, contained.getOrigin().y - 5);
    }

    /**
     *
     * @return the width of this container
     */
    public int getWidth() {
        return (contained.getWidth()) + 10;
    }

    /**
     *
     * @return the height of the container which is 10 more than the actual
     * shape it is containing
     */
    public int getHeight() {
        return (contained.getHeight()) + 10;
    }

    /**
     *
     * @return the end point (opposite to the origin )
     */
    public Point getEndPoint() {
        return new Point(getWidth() + getOrigin().x, getHeight() + getOrigin().y);
    }

    /**
     *
     * @param p point that this method checks
     * @return true if point is within the rectangle and false if it is not
     */
    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        /*setting the rectangle with this method prevents the endX to be lower than
        the originX and the endY to be lower than the originY. Sometimes users
        can select the end point first.
         */
        rectangle.setFrameFromDiagonal(getOrigin(), getEndPoint());
        if (contains(p, rectangle)) {
            doesIt = true;
        }
        return doesIt;
    }

    /**
     *
     * @param old set of points before the movement occured
     * @param offset offset to add to the old points
     * @return returns a container with the entity in the new location
     */
    @Override
    public Container updateLocation(List<Point> old, Point offset) {
        for (int i = 0; i < old.size(); i++) {
            Point oldPoint = old.get(i);
            contained.setOrigin(new Point(oldPoint.x + offset.x, oldPoint.y + offset.y));
        }
        return this;

    }

    @Override
    public Color scaleColour(Color c, float currentBrightness) {
        return DrawableI.super.scaleColour(c, currentBrightness); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param p point that this method checks
     * @param r rectangle that the method refers to to check if the point is
     * inside
     * @return true if point is within the rectangle and false if it is not
     */
    @Override
    public boolean contains(Point p, Rectangle r) {
        boolean doesIt = false;
        Rectangle rectangle = r;
        /*setting the rectangle with this method prevents the endX to be lower than
        the originX and the endY to be lower than the originY. Sometimes users
        can select the end point first.*/
        rectangle.setFrameFromDiagonal(getOrigin(), getEndPoint());
        if (rectangle.contains(p)) {
            doesIt = true;
        }
        return doesIt;
    }

    @Override
    public Container rotate(List<Point> old, Point offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Rectangle getBottomRightCorner() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getEndPoint().x - 10, getEndPoint().y - 10), new Point(getEndPoint().x + 10, getEndPoint().y + 10));
        return rectangle;
    }

    private Rectangle getBottomLeftCorner() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getOrigin().x - 10, getEndPoint().y - 10), new Point(getOrigin().x + 10, getEndPoint().y + 10));
        return rectangle;
    }

    private Rectangle getTopLeftCorner() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getOrigin().x - 10, getOrigin().y - 10), new Point(getOrigin().x + 10, getOrigin().y + 10));
        return rectangle;
    }

    private Rectangle getTopRightCorner() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getEndPoint().x - 10, getOrigin().y - 10), new Point(getEndPoint().x + 10, getOrigin().y + 10));
        return rectangle;
    }

    public boolean topRightCornerContains(Point p) {
        boolean doesIt = false;
        if (contains(p, getTopRightCorner())) {
            doesIt = true;
            System.out.println("topright");
        }
        return doesIt;
    }

    public boolean topLeftCornerContains(Point p) {
        boolean doesIt = false;
        if (contains(p, getTopLeftCorner())) {
            doesIt = true;
            System.out.println("topleft");
        }
        return doesIt;
    }

    public boolean bottomRightCornerContains(Point p) {
        boolean doesIt = false;
        if (contains(p, getBottomRightCorner())) {
            doesIt = true;
            System.out.println("bottomright");
        }
        return doesIt;
    }

    public boolean bottomLeftCornerContains(Point p) {
        boolean doesIt = false;
        if (contains(p, getBottomLeftCorner())) {
            doesIt = true;
        }
        return doesIt;
    }

    @Override
    public Container resize(float amount) {
        getContained().setHeight((int) (contained.getHeight() + amount));
        getContained().setWidth((int) (contained.getWidth() + amount));
        return this;
    }


    @Override
    public Container resize(List<Point> old, Point offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /*
    All the current DrawableI Types that our program holds should be here
     */
    public static enum EntityTypes {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    /**
     *
     * @return the contained SHAPE
     */
    public ContainerI getContained() {
        return this.contained;
    }

    /**
     *
     * @param g2d
     * @param point
     *
     * draws the indicator for the resize functionality so the user knows that
     * he can resize the shape
     */
    public void drawResizeIndicator(Graphics2D g2d, Point point) {
        g2d.drawOval(point.x - 5, point.y - 5, 10, 10);
    }

    /**
     *
     * @return returns teh rectangle to be drawn that represents the container
     */
    public Rectangle getDrawableContainer() {
        Rectangle rect = new Rectangle();
        rect.setLocation(getOrigin());
        rect.height = getHeight();
        rect.width = getWidth();
        return rect;
    }

    /*  public  getDrawableCorner(Point origin){
        
    }*/
    /**
     *
     * @param g2d
     * @param currentBrightness Draws the container if shape is selected
     */
    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setStroke(dashed);
        g2d.setColor(Color.gray);
        g2d.draw(getDrawableContainer());
        g2d.setColor(Color.MAGENTA);
        /*    if (contained.areCornersSelected()) {
            drawResizeIndicator(g2d, new Point(getOrigin()));
            drawResizeIndicator(g2d, new Point(getEndPoint()));
            drawResizeIndicator(g2d, new Point(getEndPoint().x, getOrigin().y));
            drawResizeIndicator(g2d, new Point(getOrigin().x, getEndPoint().y));
        }*/
    }

}
