/**
 * The container object is of a rectangular shape and holds the different
 * entities that can be drawn. The container hooks into the different entities
 * (which is basically a rectangle) and makes it easier to
 * resize them and move them around. Any entity that only requires an origin point, width and height
 * can be contained in a container
 */
package simpledrawer.shapes;

import com.rits.cloning.Cloner;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import simpledrawer.CopyPasteCutI;
import simpledrawer.DrawableI;
import simpledrawer.InteractiveShape;

public class Container implements DrawableI, InteractiveShape, CopyPasteCutI {

    //Points required to draw the shape contained
    ContainerI contained = null;
    final static float dash1[] = {8.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
    /**
     * just hold whether the shape is selected by the user or not but the actual
     * method is not included in this class
     */

    private boolean selected = false;

    /**
     * indicates whether the user is selecting corners or not
     */
    private boolean selectedCorners = false;

    public Container(ContainerI contained) {
        this.contained = contained;
    }

    /**
     * would probably be better not to do these claculations at runtime, and
     * just change the origin and other points when there is a change.
     *
     * @return the origin point of the rectangle
     */
    public Point getOrigin() {
        return new Point(contained.getOrigin().x - 5, contained.getOrigin().y - 5);
    }

    /**
     * @return the width of this container
     */
    public int getWidth() {
        return (contained.getWidth()) + 10;
    }

    /**
     * @return the height of the container which is 10 more than the actual
     * shape it is containing
     */
    public int getHeight() {
        return (contained.getHeight()) + 10;
    }

    /**
     * @return the end point (opposite to the origin )
     */
    public Point getEndPoint() {
        return new Point(getWidth() + getOrigin().x, getHeight() + getOrigin().y);
    }

    /*  public void setEndPoint(Point p){
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(getContained().getOrigin(),p);
     //   this.contained.setHeight(rectangle.height);
        this.contained.setWidth(rectangle.width);
    }*/
    /**
     * @param p point that this method checks
     * @return true if point is within the rectangle and false if it is not
     */
    @Override
    public boolean contains(Point p) {
        boolean doesIt = false;
        Rectangle rectangle = new Rectangle();
        /**
         * Setting the rectangle with this method prevents the endX to be lower
         * than the originX and the endY to be lower than the originY. Sometimes
         * users can select the end point first.
         */
        rectangle.setFrameFromDiagonal(getOrigin(), getEndPoint());
        if (contains(p, rectangle)) {
            doesIt = true;
        }
        setSelect(doesIt);
        return doesIt;
    }

    /**
     * @param old set of points before the movement occured
     * @param offset offset to add to the old points
     * @return returns a container with the entity in the new location
     */
    @Override
    public Container updateLocation(Point old, Point offset) {

        contained.setOrigin(new Point(old.x + offset.x, old.y + offset.y));

        return this;

    }

    @Override
    public Color scaleColour(Color c, float currentBrightness) {
        return DrawableI.super.scaleColour(c, currentBrightness); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param p point that this method checks
     * @param r rectangle that the method refers to to check if the point is
     * inside
     * @return true if point is within the rectangle and false if it is not
     */
    @Override
    public boolean contains(Point p, Rectangle r) {
        boolean doesIt = false;
        Rectangle rectangle = r;
        /**
         * Setting the rectangle with this method prevents the endX to be lower
         * than the originX and the endY to be lower than the originY. Sometimes
         * users can select the end point first.
         */
        if (rectangle.contains(p)) {
            doesIt = true;
        }
        setSelect(doesIt);
        return doesIt;
    }

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

    private Rectangle getBottomSide() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getOrigin().x, getEndPoint().y - 5), new Point(getEndPoint().x, getEndPoint().y));
        return rectangle;
    }

    private Rectangle getRightSide() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFrameFromDiagonal(new Point(getEndPoint().x - 5, getOrigin().y), new Point(getEndPoint().x, getEndPoint().y));
        return rectangle;
    }

    public boolean rightSideContains(Point p) {
        boolean cornerDetected = false;
        if (contains(p, getRightSide())) {
            cornerDetected = true;
        }
        return cornerDetected;
    }

    public boolean bottomSideContains(Point p) {
        boolean cornerDetected = false;
        if (contains(p, getBottomSide())) {
            cornerDetected = true;
        }
        return cornerDetected;
    }

    public boolean topRightCornerContains(Point p) {
        boolean cornerDetected = false;
        if (contains(p, getTopRightCorner())) {
            cornerDetected = true;
            System.out.println("topright");
        }
        return cornerDetected;
    }

    public boolean topLeftCornerContains(Point p) {
        boolean cornerDetected = false;
        if (contains(p, getTopLeftCorner())) {
            cornerDetected = true;
            System.out.println("topleft");
        }
        return cornerDetected;
    }

    public boolean bottomRightCornerContains(Point p) {
        boolean cornerDetected = false;
        if (contains(p, getBottomRightCorner())) {
            cornerDetected = true;
            System.out.println("bottomright");
        }
        return cornerDetected;
    }

    public boolean bottomLeftCornerContains(Point p) {
        boolean cornerDetected = false;
        if (contains(p, getBottomLeftCorner())) {
            cornerDetected = true;
        }
        return cornerDetected;
    }

    @Override
    public Container linearResizing(float amount) {
        if (contained.getHeight() + amount > 5) {
            getContained().setHeight((int) (contained.getHeight() + amount));
            getContained().setWidth((int) (contained.getWidth() + amount));
        }
        return this;
    }

    public Container resize(Dimension old, Point offset, InteractiveShape.SelectedPart part) {
        if (part.equals(InteractiveShape.SelectedPart.BOTTOMSIDE) && old.height + offset.y > 5) {
            this.getContained().setHeight(old.height + offset.y);
        } else if (part.equals(InteractiveShape.SelectedPart.RIGHTSIDE) && old.width + offset.x > 5) {
            this.getContained().setWidth(old.width + offset.x);

        }
        return this;
    }

    @Override
    public List<Point> getStructuralPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container rotate(float amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrigin(Point p) {
        getContained().setOrigin(new Point(p.x + 5, p.y + 5));
        System.out.println("sd");
    }


    /*
     * All the current DrawableI Types that our program holds should be here
     */
    public static enum EntityTypes {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    /**
     * @return the contained SHAPE
     */
    public ContainerI getContained() {
        return this.contained;
    }

    /**
     * @param g2d
     * @param point Draws the indicator for the linearResizing functionality so
     * the user knows that he can linearResizing the shape
     */
    public void drawResizeIndicator(Graphics2D g2d, Point point) {
        g2d.drawOval(point.x - 5, point.y - 5, 10, 10);
    }

    public void setSelect(Boolean b) {
        selected = b;
    }

    /**
     * Deselect the shape shoubl be use to unselect the shape
     */
    public void deSelect() {
        selected = false;
    }

    /**
     * Used to select the corners
     */
    public void SelectCorners() {
        selectedCorners = true;
    }

    /**
     * Unselect corners
     */
    public void deSelectCorners() {
        selectedCorners = false;
    }

    /**
     * @return whether the shape is selected
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * @return whether the corners are selected
     */
    public boolean areCornersSelected() {
        return this.selectedCorners;
    }

    /**
     * @return returns teh rectangle to be drawn that represents the container
     */
    public Rectangle getDrawableContainer() {
        Rectangle rect = new Rectangle();
        rect.setLocation(getOrigin());
        rect.height = getHeight();
        rect.width = getWidth();
        return rect;
    }

    /**
     * @param g2d
     * @param currentBrightness Draws the container if shape is selected
     */
    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setStroke(dashed);
        g2d.setColor(Color.gray);
        if (this.isSelected()) {
            g2d.draw(getDrawableContainer());

        }

        if (contained instanceof DrawableI) {
            DrawableI drawable = (DrawableI) getContained();
            drawable.drawShape(g2d);
        }

    }

    @Override
    public Object clone() {
        return new Cloner().deepClone(this);
    }

}
