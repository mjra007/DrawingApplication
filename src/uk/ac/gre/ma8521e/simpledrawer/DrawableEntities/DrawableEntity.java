package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities;

import com.rits.cloning.Cloner;
import drawingpanel.DrawableI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity.Builder;

/**
 * A drawableEntity is the most basic object you can have. Everything that can
 * be drawn or seen has a width, height and a color. Every object created to be
 * drawn should extend the DrawableEntity class and write it s own
 * implementation of the draw method to be able to be drawn.
 *
 */
public class DrawableEntity implements DrawableI  {

    // Type of entity e.g. line or oval
    private final EntityType TYPE;
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

    /*  public DrawableEntity(Builder b) {
    }*/
    public DrawableEntity(DrawableEntity dE) {
        this.structuralPoints = dE.structuralPoints;
        this.height = dE.height;
        this.width = dE.width;
        this.color = dE.color;
        this.TYPE = dE.TYPE;
    }

    private DrawableEntity(List<Point> structuralPoints, int height, int width, Color color, EntityType type) {
        this.structuralPoints = structuralPoints;
        this.height = height;
        this.width = width;
        this.color = color;
        this.TYPE = type;
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
     * X******s * * * * *******
     *
     * If it was a Rectangle It would returnthe x point in that square
     *
     */
    public Point getOrigin() {
        return this.getStructuralPoints().get(0);
    }

    public void setOrigin(Point newPoint) {
        this.getStructuralPoints().set(0, newPoint);
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
    public void draw(Graphics2D g2d) {
        System.out.println("You forgot to implement the drawing method!");
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
    public EntityType getEntityType() {
        return this.TYPE;
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

        public Builder setType(EntityType type) {
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
            return new DrawableEntity(structuralPoints, height, width, color, type);
        }

    }

}
