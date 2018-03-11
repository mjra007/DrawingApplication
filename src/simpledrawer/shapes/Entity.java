package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.DrawableI;

/**
 * This class aims to represent the most basic and abstract behaviours of
 * anything you wish to draw. Therefore we are keeping details such as the width
 * and weight, color, whether it is selected or not and structural points for
 * the
 */
public abstract class Entity implements DrawableI {

    // Type of shape e.g. line or oval
    private final EntityType type;

    private List<Point> structuralPoints = new ArrayList<Point>();

    /**
     * Everything you draw has a color
     */
    private Color color;

    public Entity(List<Point> structuralPoints, Color newColor, EntityType et) {
        this.structuralPoints = structuralPoints;
        setColor(newColor);
        type = et;
    }

    public Entity() {
        this.type = EntityType.LINE;
        color = Color.black;
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        System.out.println("You forgot to implement the drawing method!");
    }


    /*
     * All the current DrawableI Types that our program holds should be here
     */
    public static enum EntityType {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    /**
     * @return the color for the entity
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @param newColor sets the entity color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
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

    public Entity.EntityType getEventType() {
        return this.type;
    }

}
