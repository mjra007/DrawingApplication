package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shape;

public class SLine extends Shape {

    //To draw a line you only need a starting point and end point
    public SLine(DrawableEntity drawable) {
        super(drawable);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Scale the brightness of the colour
        // Color c = scaleColour(getColor(), currentBrightness);
        g2d.setColor(super.getColor());
        // Set the thickness of the line
        g2d.setStroke(new BasicStroke(this.getThickness()));
        // Draw the line
        g2d.drawLine(getX(), getY(), getX()+super.getWidth(), getY()+super.getHeight());
    }

    @Override
    public String toString() {
        return "Color: " + super.getColor() + " Thick: " + super.getThickness() + " Points: " + super.getStructuralPoints().toString() + " width: " + super.getWidth() + " height: " + super.getHeight() + "  " + super.getX() + " , " + super.getX() ;
    }

}
