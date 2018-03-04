/*
 * Canvas.java
 *
 * @author Gill Windall
 *
 * A specialised JPanel used as the canvas for simple drawings.
 *
 * NOT created using the NetBeans GUI builder
 */
package GUI.Views;

import GUI.Controllers.CanvasController;
import GUI.View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import simpledrawer.DrawableI;
import simpledrawer.shapes.Entity;

public class Canvas extends JPanel implements View {

    private CanvasController controller;

    /* Constructor used to create a Canvas with a
     * specified line colour, thickness and shape type
     */
    public Canvas() {
        this.setBorder(BorderFactory.createLoweredBevelBorder());

    }

    public void addController(CanvasController ct) {
        controller = ct;
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
    }

    /*
     * paint the drawing including all shapes drawn so far
     *
     * paintComponent() is invoked when repaint() is called and
     * when the GUI needs redrawing e.g. because it has been covered
     * by another window
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D needed to set line thickness
        Graphics2D g2d = (Graphics2D) g;

        Stroke s = g2d.getStroke(); // save stroke to restore later

        // rotate the drawing by the current rotation amount
        double rotateTheta;
        rotateTheta = controller.getCurrentRotation() * Math.PI / 180;
        g2d.rotate(0, this.getWidth() / 2, this.getHeight() / 2);

        // Loop though the ArrayList drawing
        // all the shapes stored in it
        for (Object aShape : controller.getDrawingList()) {
            // draw the correct sort of shape: line or oval or triangle
            Entity shape = (Entity) aShape;
            //   System.out.println(shape.getContained().toString());
            if (aShape instanceof DrawableI) {
                DrawableI ld = (DrawableI) aShape;
                ld.drawShape(g2d);
            }
        }
        g2d.setStroke(s);  // restore saved stroke
        if (controller.getClicks() != null && controller.getClicks().size() >= 1) { // draw dot where line started
            for (int i = 0; i < controller.getClicks().size(); i++) {
                System.out.print(i);
                g2d.setColor(Color.BLACK);
                g2d.fillOval(controller.getClicks().get(i).x, controller.getClicks().get(i).y, 3, 3);
            }
        }

    }

    @Override
    public void refresh() {
        System.out.println("sdsds");
        repaint();
    }

}
