package simpledrawer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class SRectangle extends Shape {

    public SRectangle(Point origin, int width, int height, Color c, int t, Entity.EntityType et) {
        super(origin, width, height, c, t, Entity.EntityType.RECTANGLE);
    }

    public SRectangle() {
        super(new Point(1, 0), 100, 100, Color.GRAY, 5, Entity.EntityType.RECTANGLE);
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
        g2d.setColor(super.getColor());
        // Get start point
        // Draw the line
        // Set the thickness of the line                
        g2d.setStroke(new BasicStroke(this.getThickness()));
        if (super.isItFilled()) {
            g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        } else {
            g2d.drawRect(getX(), getY(), getWidth(), getHeight());
        }

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
