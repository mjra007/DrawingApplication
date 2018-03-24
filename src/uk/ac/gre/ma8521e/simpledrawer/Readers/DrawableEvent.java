package uk.ac.gre.ma8521e.simpledrawer.Readers;

import java.awt.Color;
import java.awt.Point;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.EntityType;

public class DrawableEvent {

    private Point origin;
    private int height;
    private int width;
    private int borderThickness;
    private boolean filled;
    private Color filledColor;
    private Color color;
    private EntityType type;

    private DrawableEvent(Point o, int h, int w, int bT, boolean f, Color fC, Color c, EntityType t) {
        this.origin = o;
        this.height = h;
        this.width = w;
        this.borderThickness = bT;
        this.filled = f;
        this.filledColor = fC;
        this.color = c;
        this.type = t;
    }

    public DrawableEvent(DrawableEntity shape) {
        this.origin = shape.getOrigin();
        this.height = shape.getHeight();
        this.width = shape.getWidth();
        this.borderThickness = shape.getThickness();
        this.filled = shape.isItFilled();
        this.filledColor = shape.getFilledColor();
        this.type = shape.getEntityType();
        this.color = shape.getColor();
    }

    public Point getOrigin() {
        return this.origin;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getThickness() {
        return this.borderThickness;
    }

    public boolean getFilled() {
        return this.filled;
    }

    public Color getFilledColor() {
        return this.filledColor;
    }

    public Color getColor() {
        return this.color;
    }

    public EntityType type() {
        return this.type;
    }
    
    public String toString(){
        return this.type.toString();
    }

}
