/**
 * The container object is of a rectangular shape and holds the different
 * entities that can be drawn. The container hooks into the different entities
 * (which is basically a rectangle) and makes it easier to
 * resize them and move them around. Any entity that only requires an origin point, width and height
 * can be contained in a container
 */
package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container;

import com.rits.cloning.Cloner;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.CopyPasteCutI;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.InteractiveShape;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.EntityType;

public class Container extends DrawableEntity implements InteractiveShape, CopyPasteCutI, ContainerI {

    //Points required to draw the shape contained
    private DrawableEntity contained = null;
    private final static float[] DASH1 = {8.0f};
    private final static BasicStroke DASHED  = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, DASH1, 0.0f);
    /**
     * just hold whether the shape is selected by the user or not but the actual
     * method is not included in this class
     */

    private boolean selected = false;

    protected Container(DrawableEntity drawableEntity) {
        super(new DrawableEntity.Builder()
                .setColor(Color.gray)
                .setStructuralPoints(Arrays.asList(new Point(drawableEntity.getOrigin().x - 5, drawableEntity.getOrigin().y - 5), new Point(drawableEntity.getWidth() + drawableEntity.getOrigin().x, drawableEntity.getHeight() + drawableEntity.getOrigin().y)))
                .setWidth((drawableEntity.getWidth()) + 10)
                .setHeight((drawableEntity.getHeight()) + 10)
                .setType(EntityType.CONTAINER)
                .build());
        this.contained = drawableEntity;
    }

    /**
     * would probably be better not to do these claculations at runtime, and
     * just change the origin and other points when there is a change.
     *
     * @return the origin point of the rectangle
     */
    @Override
    public Point getOrigin() {
        return new Point(contained.getOrigin().x - 5, contained.getOrigin().y - 5);
    }

    /**
     * @return the width of this container
     */
    @Override
    public int getWidth() {
        return (contained.getWidth()) + 10;
    }

    /**
     * @return the height of the container which is 10 more than the actual
     * shape it is containing
     */
    @Override
    public int getHeight() {
        return (contained.getHeight()) + 10;
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        getContained().setHeight(height - 10);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        getContained().setWidth(width - 10);
    }

    /**
     * @return the end point (opposite to the origin )
     */
    public Point getEndPoint() {
        return new Point(getWidth() + getOrigin().x, getHeight() + getOrigin().y);
    }

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

    public Container rotate(List<Point> oldPoints, Point offset) {
        System.out.println(this.getClass().getName()+" rotate()  is not implemented");
        return null;
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

    @Override
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
        return super.getStructuralPoints();
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

    /**
     * @return the contained SHAPE
     */
    public DrawableEntity getContained() {
        return this.contained;
    }


    public void setSelect(Boolean b) {
        selected = b;
    }


    /**
     * @return whether the shape is selected
     */
    public boolean isSelected() {
        return this.selected;
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
    public void draw(Graphics2D g2d) {
        g2d.setStroke(DASHED);
        g2d.setColor(this.getColor());
        if (this.isSelected()) {
            g2d.draw(getDrawableContainer());

        }
        getContained().draw(g2d);

    }

    @Override
    public Object clone() {
        return new Cloner().deepClone(this);
    }

}
