package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micael#
 *
 * This class aims to represent the most basic and abstract behaviours of
 * anything you wish to draw. Therefore we are keeping details such as the width
 * and weight, color, whether it is selected or not and structural points for
 * the
 *
 */
public abstract class Entity {

    // Type of shape e.g. line or oval
    private final EntityType type;
    /**
     * just hold whether the shape is selected by the user or not but the actual
     * method is not included in this class
     */
    
    private boolean selected = false;
    
    /**
     * indicates whether the user is selecting corners or not
     */
    private boolean selectedCorners = false;
    /**
     * Structure points are the ones that hold the points that 
     *allow the drawing of the entity
     */
    private List<Point> structuralPoints = new ArrayList<Point>();

    /**
     * Everything you draw has a color
     */
    private Color color;

    public Entity(List<Point> structuralPoints, Color newColor, EntityType et) {
        structuralPoints = structuralPoints;
        setColor(newColor);
        type =et;
    }

    public Entity() {
        this.type = EntityType.LINE;
        color = Color.black;
    }


    /*
    All the current DrawableI Types that our program holds should be here
     */
    public static enum EntityType {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    /**
     *
     * @return the color for the entity
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * 
     * @param newColor sets the entityt color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     *
     * @param list
     *
     * Every entity holds some points that are crucial to draw it, move it,
     * resize it. They are held in this list
     */
    public void setStructuralPoints(List<Point> list) {
        this.structuralPoints = list;
    }

    /**
     *
     * @return List of key points
     */
    public List<Point> getStructuralPoints() {
        return this.structuralPoints;
    }


    public Entity.EntityType getEventType() {
        return this.type;
    }

    public void setSelect(Boolean b) {
        selected = b;
    }

    /**
     *
     * Deselect the shape shoubl be use to unselect the shape
     *
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
     *
     * @return whether the shape is selected
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     *
     * @return whether the corners are selected
     */
    public boolean areCornersSelected() {
        return this.selectedCorners;
    }


}
