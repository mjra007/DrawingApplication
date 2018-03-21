package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

public class SOval extends Shape {

    public SOval(Shape shape) {
        super(shape);
    }

    /**
     * @return an int that is the y of the opposite point of the origin
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

    /**
     * @return the area in pixels of the ellipse. Does this always work?
     */
    public double getArea() {
        int line1 = getXEnd() - getX();
        int line2 = getYEnd() - getY();
        System.out.println(line1 + ", " + line2 + " " + Math.PI * line1 / 2 * line2 / 2);
        return Math.PI * line1 / 2 * line2 / 2;
    }

    @Override
    public void draw(Graphics2D g2d) {
        //  Scale the brightness of the colour
        //  Color c = scaleColour(super.getColor(), currentBrightness);
        //  Set the thickness of the line
        if (super.isItFilled()) {
            g2d.setColor(super.getFilledColor());
            g2d.fillOval(getX(), getY(), getWidth(), getHeight());
        }
        // Draw the oval    
        g2d.setColor(super.getColor());
        g2d.setStroke(new BasicStroke(this.getThickness()));
        g2d.drawOval(getX(), getY(),
                getWidth(),
                getHeight());
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
