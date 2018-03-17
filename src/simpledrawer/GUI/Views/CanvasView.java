package simpledrawer.GUI.Views;

import simpledrawer.GUI.Controllers.CanvasController;
import simpledrawer.GUI.View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import simpledrawer.DrawableI;
import simpledrawer.shapes.DrawingIndicator;

public class CanvasView extends JPanel implements View{

    private CanvasController controller;

    /* Constructor used to create a CanvasView with a
     * specified line colour, thickness and shape type
     */
    public CanvasView() {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }


    public void addController(CanvasController ct) {
        controller = ct;
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
        this.addMouseWheelListener(controller);
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

        if (controller.isBetterGraphicsSelected()) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        Stroke s = g2d.getStroke(); // save stroke to restore later

        // rotate the drawing by the current rotation amount
        double rotateTheta;
        rotateTheta = controller.getCurrentRotation() * Math.PI / 180;
        g2d.rotate(rotateTheta, this.getWidth() / 2, this.getHeight() / 2);

        // Loop though the ArrayList drawing
        // all the shapes stored in it
        for (DrawableI aShape : controller.getDrawingList().values()) {
            // draw the correct sort of shape: line or oval or triangle
            //   System.out.println(shape.getContained().toString());

            DrawableI ld = (DrawableI) aShape;
            ld.drawShape(g2d);

        }
        g2d.setStroke(s);  // restore saved stroke
        if (controller.getClicks() != null && controller.getClicks().size() >= 1) { // draw dot where line started
            for (int i = 0; i < controller.getClicks().size(); i++) {
                new DrawingIndicator(controller.getClicks().get(i)).drawShape(g2d);
            }
        }

    }


    @Override

    public void refresh() {
        repaint();
    }

}
