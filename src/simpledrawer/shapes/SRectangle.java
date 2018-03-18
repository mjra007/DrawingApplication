package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

public class SRectangle extends Shape {

    public SRectangle(Shape shape) {
        super(shape);    
    }


    /**
     * @return an int that is the x of the opposite point of the origin
     */
    public int getXEnd() {
        return getX() + getWidth();
    }

    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getYEnd() {
        return getY() + getHeight();
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        // Scale the brightness of the colour
        // Color c = scaleColour(getColor(), currentBrightness);
        // Get start point
        // Draw the line
        // Set the thickness of the line                
        if (super.isItFilled()) {
            g2d.setColor(filledColor);
            g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        }
        g2d.setColor(super.getColor());
        g2d.setStroke(new BasicStroke(this.getThickness()));
        g2d.drawRect(getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX() + "Last " + this.getXEnd() + ", " + this.getYEnd();
    }

    @Override
    public void setOrigin(Point newPoint) {
        super.getStructuralPoints().set(0, newPoint);
    }
}
