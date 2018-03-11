package simpledrawer.Readers;

import java.awt.Color;
import java.awt.Point;
import simpledrawer.shapes.Entity;

public class ShapeEvent {

    private Point origin;
    private int height;
    private int width;
    private int borderThickness;
    private boolean filled;
    private Color filledColor;
    private Entity.EntityType type;

    private ShapeEvent(Point o, int h, int w, int bT, boolean f, Color fC, Entity.EntityType t) {
        this.origin = o;
        this.height = h;
        this.width = w;
        this.borderThickness = bT;
        this.filled = f;
        this.filledColor = fC;
        this.type = t;

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

    public Entity.EntityType type() {
        return this.type;
    }
    

}
