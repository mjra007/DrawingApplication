package GUI.Views;

import GUI.Controllers.CanvasController;
import GUI.View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import simpledrawer.DrawableI;

public class CanvasView extends JPanel implements View {

    private CanvasController controller;
    private JPopupMenu rightMenu;
    private JMenuItem fill = new JMenuItem("Fill");
    private JMenuItem delete = new JMenuItem("Delete");
    private JMenuItem copy = new JMenuItem("Copy");
    private JMenuItem paste = new JMenuItem("Paste");
    private JMenuItem cut = new JMenuItem("Cut");
    private JMenuItem backgroundMenu = new JMenuItem("Change Background");

    /* Constructor used to create a CanvasView with a
     * specified line colour, thickness and shape type
     */
    public CanvasView() {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    public JPopupMenu changeMenu(boolean shapeSelected) {
        if (shapeSelected) {
            rightMenu = new JPopupMenu();
            rightMenu.add(fill);
            rightMenu.add(delete);
            rightMenu.add(copy);
            rightMenu.add(cut);
            rightMenu.add(paste);
        } else {
            rightMenu = new JPopupMenu();
            rightMenu.add(backgroundMenu);
            rightMenu.add(paste);
        }
        return rightMenu;
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
                g2d.setColor(Color.BLACK);
                g2d.fillOval(controller.getClicks().get(i).x, controller.getClicks().get(i).y, 3, 3);
            }
        }

    }

    public JPopupMenu getRightMenu() {
        return this.rightMenu;
    }

    public JMenuItem getDelete() {
        return this.delete;
    }

    public JMenuItem getFilled() {
        return this.fill;
    }

    public JMenuItem getCopy() {
        return this.copy;
    }

    public JMenuItem getCut() {
        return this.cut;
    }

    public JMenuItem getPaste() {
        return this.paste;
    }
    
    public JMenuItem getBackgroundMenu(){
        return this.backgroundMenu;
    }
    
    @Override

    public void refresh() {
        repaint();
    }

}
