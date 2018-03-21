package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

public class SLine extends Shape {

    //To draw a line you only need a starting point and end point
    public SLine(Shape shape) {
        super(shape);
    }

    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getXEnd() {
        return this.getStructuralPoints().get(1).x;
    }

    /**
     * @return an int that is the y of the opposite point of the origin
     */
    public int getYEnd() {
        return this.getStructuralPoints().get(1).y;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Scale the brightness of the colour
        // Color c = scaleColour(getColor(), currentBrightness);
        g2d.setColor(super.getColor());
        // Set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        // Draw the line
        g2d.drawLine(getX(), getY(), this.getX() + getWidth(), this.getY() + getHeight());
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