package simpledrawer.shapes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.DrawableI;

public abstract class Entity implements DrawableI {

    // Type of shape e.g. line or oval
    private final EntityType type; // Currently always SHAPE
    private final int pointsRequired;
    private int width;
    private int height;
    private boolean selected = false;
    private boolean selectedCorners = false;

    // These points are crucical points that the shapes depend on
    private List<Point> structuralPoints = new ArrayList<Point>();

    public Entity(List<Point> list, int pointsRequired, EntityType et) {
        structuralPoints = list;
        type = et;
        this.pointsRequired = pointsRequired;
        // This might not always be the case if not it should be overriden 
        this.width = list.get(1).x - list.get(0).x;
        this.height = list.get(1).y - list.get(0).y;
    }

    public Entity() {
        this.type = EntityType.LINE;
        this.height = 5;
        this.width = 5;
        pointsRequired = 0;
    }

    /*
     * All the current DrawableI Types that our program holds should be here
     */
    public static enum EntityType {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    /**
     * @param list Every entity holds some points that are crucial to draw it,
     * move it, resize it. They are held in this list
     */
    public void setStructuralPoints(List<Point> list) {
        this.structuralPoints = list;
    }

    /**
     * @return List of key points
     */
    public List<Point> getStructuralPoints() {
        return this.structuralPoints;
    }

    /**
     * @return an integer that represents the no of points that entity requires.
     * Rectangle 2, Triangle 3 etc
     */
    public int getPointsRequired() {
        return pointsRequired;
    }

    public Entity.EntityType getEventType() {
        return this.type;
    }

    /**
     * @return endPointX - origiPointX you might want to override the method
     * depending on what shape your entity takes
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return endPointY - origiPointY you might want to override the method
     * depending on what shape your entity takes
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return endPointX - origiPointX you might want to override the method
     * depending on what shape your entity takes
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return endPointY - origiPointY you might want to override the method
     * depending on what shape your entity takes
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Select the shape should be set the shape
     */
    public void Select() {
        selected = true;
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
     * @return a container with the shape
     */
    public Container containShape() {
        return new Container(this);
    }

}
