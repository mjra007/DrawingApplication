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

public class Shape {
    
    /*List of vertices
    * When it makes sense the first
    * point should be always the starting point
    */
    private List<Point> vertices;
    private Color colour;
    private int thickness;
    // Type of shape e.g. line or oval
    private ShapeType shapeType;
    private String eventType; // currently always SHAPE
    
    public Shape(Color c, int t, ShapeType st) {
        vertices = new ArrayList<>();
        colour = c;
        thickness = t;
        shapeType = st;
    }
    
    public Shape(){
        this.vertices = new ArrayList<>();
        this.colour = new Color(0,0,0);
        this.thickness = 5;
        this.shapeType = ShapeType.LINE;
        this.eventType = "shape";
    }
    
    public Shape(Point startPoint, Point endPoint, Color c, int t, ShapeType st) {
        vertices = new ArrayList<>();
        colour = c;
        thickness = t;
        shapeType = st;
    } 
    
    public Shape(List<Point> vertices, Color colour, int thickness, ShapeType shapeType) {
        this.vertices = vertices;
        this.colour = colour;
        this.thickness = thickness;
        this.shapeType = shapeType;
    }
    public Shape(List<Point> vertices, Color colour, int thickness, ShapeType shapeType, String eventType) {
        this.vertices = vertices;
        this.colour = colour;
        this.thickness = thickness;
        this.shapeType = shapeType;
        this.eventType = eventType;
    }

    public void setVertices(List<Point> vertices){
        this.vertices = vertices;
    }
    
    public void setVertice(int index, Point point){
        this.vertices.set(index, point);
    }
    
    public List<Point> getVertices(){
        return this.vertices;
    }
    
    public Point getVertice(int index){
        return this.vertices.get(index);
    }  
    
    public Color getColour() {
        return colour;
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
