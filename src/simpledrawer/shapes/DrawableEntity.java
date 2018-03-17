package simpledrawer.shapes;

import com.rits.cloning.Cloner;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.shapes.DrawableEntity.Builder;
import simpledrawer.DrawableI;

/**
 * A drawableEntity is the most basic object you can have. Everything that can
 * be drawn or seen has a width, height and a color. Every object created to be
 * drawn should extend the DrawableEntity class and write it s own
 * implementation of the draw method to be able to be drawn.
 *
 */
public class DrawableEntity implements DrawableI {

    // Type of entity e.g. line or oval
    private final EntityType type;
    private int height;
    private int width;
    /**
     * Every entity holds some points that are crucial to draw it, They are held
     * in this list
     */
    private List<Point> structuralPoints = new ArrayList<Point>();
    /**
     * Everything you draw has a color
     */
    private Color color;

    private DrawableEntity(Builder d) {
        this.structuralPoints = d.structuralPoints;
        this.height = d.height;
        this.width = d.width;
        this.color = d.color;
        type = d.type;
    }

    /**
     *
     * @return the width of this drawableEntity
     */
    public int getWidth() {
        return this.width;
    }

    /**
     *
     * @return the height of the drawableEntity
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param newWidth
     *
     * set a new width for this shape
     */
    public void setWidth(int newWidth) {
        getStructuralPoints().set(1, new Point(getOrigin().x + newWidth, getOrigin().y));
        this.width = newWidth;
    }

    /**
     *
     * @param newHeight
     *
     * set a new height for this shape
     */
    public void setHeight(int newHeight) {
        getStructuralPoints().set(1, new Point(getOrigin().x, getOrigin().y + newHeight));
        this.height = newHeight;
    }

    /**
     *
     * @return the first of the structural points of your shape
     *
     * X******s
     *  * *
     *  * *
     *  *******
     *
     * If it was a Rectangle It would returnthe x point in that square
     *
     */
    public Point getOrigin() {
        return this.getStructuralPoints().get(0);
    }

    /**
     *
     * @return the x of the getOrigin() point
     */
    public int getX() {
        return getStructuralPoints().get(0).x;
    }

    /**
     *
     * @return the y of the getOrigin() point
     */
    public int getY() {
        return getStructuralPoints().get(0).y;
    }

    /**
     *
     * @param g2d
     *
     * meant to be implemented by the dev when creating a new shape
     */
    @Override
    public void drawShape(Graphics2D g2d) {
        System.out.println("You forgot to implement the drawing method!");
    }

    /**
     *
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

    /**
     *
     * @return the type of entity
     */
    public DrawableEntity.EntityType getEntityType() {
        return this.type;
    }

    @Override
    public Object clone() {
        return new Cloner().deepClone(this);
    }

    public static final class Builder {

        private EntityType type;
        private int height;
        private int width;
        private List<Point> structuralPoints = new ArrayList<Point>();
        private Color color;

        
        public Builder setType(DrawableEntity.EntityType type) {
            this.type = type;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder setStructuralPoints(List<Point> list) {
            this.structuralPoints = list;
            return this;
        }

        public DrawableEntity build() {
            return new DrawableEntity(this);
        }

    }

}
