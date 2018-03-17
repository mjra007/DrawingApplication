package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

/**
 * Shape object implements DrawableEntity because this shapes are meant to be
 * drawn in the canvas and ContainerI because we want to make them interactive
 * as possible
 *
 * Furthermore it holds common variables to almost all shapes like
 * borderthickness, if the shape is filled insid and the filled color
 */
public class Shape extends DrawableEntity implements ContainerI {

    //border of the shape
    private int borderThickness;
    //whether the shape is filled with a color
    private boolean filled = false;
    //if not assigned let s just use the same as the border because why not
    public Color filledColor = super.getColor();

    /**
     * @param origin origin of the shape - point where the user clicked first
     * @param width the width of the shape
     * @param height the height of the shape
     * @param c border color of the shape
     * @param t thickness of the border
     * @param et EntityType of the shape
     */
    public Shape(Point origin, int width, int height, Color c, int t, DrawableEntity.EntityType et) {
        super(new DrawableEntity.Builder()
                .setStructuralPoints(Arrays.asList(origin, new Point(origin.x + width, origin.y + height)))
                .setColor(c)
                .setHeight(height)
                .setWidth(width)
                .setType(et)
                .build());
        borderThickness = t;
    }

    public Shape(Shape shape) {
        super(new DrawableEntity.Builder()
                .setStructuralPoints(Arrays.asList(shape.getOrigin(), new Point(shape.getOrigin().x + shape.getWidth(), shape.getOrigin().y + shape.getHeight())))
                .setColor(shape.getColor())
                .setHeight(shape.getHeight())
                .setWidth(shape.getWidth())
                .setType(shape.getEntityType())
                .build());
        borderThickness = shape.getThickness();
    }

    /**
     *
     * @return the thickness of this shape
     */
    public int getThickness() {
        return this.borderThickness;
    }

    /**
     *
     * @param thickness set a new thickness for the shape
     */
    public void setThickness(int thickness) {
        this.borderThickness = thickness;
    }

    /**
     *
     * @return a boolean if shape is filled
     */
    public boolean isItFilled() {
        return this.filled;
    }

    /**
     *
     * @param b set a shape to filled or not with a boolean
     */
    private void setFilled(boolean b) {
        this.filled = b;
    }

    /**
     *
     * @return get the color that was used to fill the shape
     */
    public Color getFilledColor() {
        return this.filledColor;
    }

    /**
     *
     * @param c set the color used to fill the shape
     */
    public void setFilledColor(Color c) {
        setFilled(true);
        this.filledColor = c;
    }

    /**
     *
     * @param newPoint set the origin of the shape
     */
    @Override
    public void setOrigin(Point newPoint) {
        this.getStructuralPoints().set(0, newPoint);
    }

    public static class Builder {
        //border of the shape
        private int borderThickness;
        //whether the shape is filled with a color
        private boolean filled = false;
        //if not assigned let s just use the same as the border because why not
        public Color filledColor;
   
    }
}
