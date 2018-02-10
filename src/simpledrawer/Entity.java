/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import simpledrawer.shapes.ShapeType;
import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micael
 */
public abstract class Entity implements DrawableI {

    /*List of vertices
    * When it makes sense the first
    * point should be always the starting point
     */
    private Color colour;
    private int thickness;
    // Type of shape e.g. line or oval
    private ShapeType shapeType;
    private String eventType; // currently always SHAPE
    int width;
    int height;

    //These points are crucical points that the shapes depend on
    List<Point> structuralPoints = new ArrayList<Point>();

    public Entity(List<Point> list,int width, int height, Color c, int t, ShapeType st) {
        colour = c;
        structuralPoints = list;
        thickness = t;
        shapeType = st;
        this.height = height;
        this.width = width;
    }

    public Entity() {
        this.colour = new Color(0, 0, 0);
        this.thickness = 5;
        this.shapeType = ShapeType.LINE;
        this.eventType = "shape";
        this.height=5;
        this.width = 5;
    }

    public Entity(List<Point> points,int height,int width, Color colour, int thickness, ShapeType shapeType, String eventType) {
        this.colour = colour;
        this.structuralPoints = points;
        this.thickness = thickness;
        this.shapeType = shapeType;
        this.eventType = eventType;
        this.height = height;
        this.width = width;
    }

    public Color getColour() {
        return colour;
    }

    public void setStructuralPoints(List<Point> list) {
        this.structuralPoints = list;
    }

    public List<Point> getStructuralPoints() {
        return this.structuralPoints;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    /**
     * The method converts the string representation of the colour passed to it
     * to the corresponding static constant of class Color e.g. "red" will be
     * converted to Color.RED. The strange code is an example of "reflection".
     * See
     * http://stackoverflow.com/questions/5822384/getting-a-color-from-a-string-input
     * for more explanation.
     *
     * @param colour string representation of the Color required
     */
    public void setColourByString(String colour) {
        try {
            Color c;
            Field f = Color.class.getField(colour.toUpperCase());
            this.colour = (Color) f.get(null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            this.colour = Color.BLACK;
        }
    }
}
