package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

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
     * @param b
     */
    public Shape(Shape b) {
        super(new DrawableEntity.Builder()
                .setStructuralPoints(Arrays.asList(b.getOrigin(), new Point(b.getOrigin().x + b.getWidth(), b.getOrigin().y + b.getHeight())))
                .setColor(b.getColor())
                .setHeight(b.getHeight())
                .setWidth(b.getWidth())
                .setType(b.getEntityType())
                .build());
        borderThickness = b.borderThickness;
        filled = b.filled;
        filledColor = b.filledColor;
    }

    private Shape(Point origin,int width,int height,Color color,int borderThickness,EntityType type) {
        super(new DrawableEntity.Builder()
                .setStructuralPoints(Arrays.asList(origin, new Point(origin.x + width, origin.y +height)))
                .setColor(color)
                .setHeight(height)
                .setWidth(width)
                .setType(type)
                .build());
        this.borderThickness = borderThickness;
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

    public static final class Builder {

        //border of the shape
        private int borderThickness;
        //whether the shape is filled with a color
        private boolean filled = false;
        //if not assigned let s just use the same as the border because why not
        public Color filledColor;
        private EntityType type;
        private int height;
        private int width;
        private Point origin;
        private Color color;

        public Builder setType(DrawableEntity.EntityType type) {
            this.type = type;
            return this;
        }

        public Builder isShapeFilled(boolean b){
            this.filled=b;
            return this;
        }
        
        public Builder setBorderThickness(int t){
            this.borderThickness=t;
            return this;
        }
        
        public Builder setColor(Color c){
            this.color=c;
            return this;
        }
        
        public Builder setFilledColor(Color c ){
            this.filledColor=c;
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

        public Builder setOrigin(Point origin) {
            this.origin = origin;
            return this;
        }
        
        public Shape build(){
            return new Shape(origin,width,height,color,borderThickness,type);
        }

    }
}
