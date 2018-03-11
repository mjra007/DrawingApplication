package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

public abstract class Shape extends Entity implements ContainerI {

    private int height;
    private int width;
    private int borderThickness;
    private boolean filled = false;
    //if not assigned let s just use the same as the border because why not
    public Color filledColor = super.getColor();

    /**
     * @param origin origin of the shape - point where the user clicked first
     * @param width the width of the shape
     * @param height the height of the shape
     * @param c border color of the shape
     * @param t thickness of the border
     * @param pR points required to draw the shape
     * @param et EntityType of the shape
     */
    public Shape(Point origin, int width, int height, Color c, int t, Entity.EntityType et) {
        super(Arrays.asList(origin, new Point(origin.x + width, origin.y + height)), c, et);
        borderThickness = t;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return super.getStructuralPoints().get(0).x;
    }

    public int getY() {
        return super.getStructuralPoints().get(0).y;
    }

    @Override
    public Point getOrigin() {
        return super.getStructuralPoints().get(0);
    }

    public int getThickness() {
        return this.borderThickness;
    }

    /**
     * @return endPointX - origiPointX you might want to override the method
     * depending on what shape your entity takes
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    public boolean isItFilled(){
        return this.filled;
    }
    
    public void setFilled(boolean b){
        this.filled = b;
    }
    
    public Color getFilledColor(){
        return this.filledColor;
    }
    
    public void setFilledColor(Color c){
        this.filledColor=c;
    }
    
    /**
     * @return endPointY - origiPointY you might want to override the method
     * depending on what shape your entity takes
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @param newWidth
     * @return endPointX - origiPointX you might want to override the method
     * depending on what shape your entity takes
     */
    @Override
    public void setWidth(int newWidth) {
        super.getStructuralPoints().set(1, new Point(getOrigin().x + newWidth, getOrigin().y));
        this.width = newWidth;
    }

    /**
     * @return endPointY - origiPointY you might want to override the method
     * depending on what shape your entity takes
     */
    public void setHeight(int newHeight) {
        super.getStructuralPoints().set(1, new Point(getOrigin().x, getOrigin().y + newHeight));
        this.height = newHeight;
    }

}
