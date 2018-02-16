/*
 * DrawingPanel.java
 *
 * @author Gill Windall
 *
 * A specialised JPanel used as the canvas for simple drawings.
 *
 * NOT created using the NetBeans GUI builder
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import simpledrawer.Container;
import simpledrawer.ContainerFactory;
import simpledrawer.InteractiveShape;
import simpledrawer.DrawableI;
import simpledrawer.Entity;

public class DrawingPanel extends JPanel {

    // current settings used when drawing
    private int currentThickness;
    private Color currentColor;
    private Entity.EntityType currentEntityType;
    private float currentBrightness;
    private int currentRotation;

    public static Container selected;

    private List<Point> currentPoints; // x and y points for shape being drawn

    // position of the latest click
    private int x, y;
    private List<Point> translateCoords;

    // A List that stores the shapes that appear on the JPanel
    private List<Container> containers;

    private int indexSelectedShape;

    //Holds the 
    int orginalX, orginalY;

    private DrawingState state = DrawingState.DRAWING;

    /* Default constructor.  Sets default values for line colour, thickness 
     * and shape type.
     */
    public DrawingPanel() {
        this(Color.BLACK, 5, Entity.EntityType.RECTANGLE);
    }

    /* Constructor used to create a DrawingPanel with a
     * specified line colour, thickness and shape type
     */
    public DrawingPanel(Color c, int t, Entity.EntityType et) {
        this.addMouseListener(new MouseWatcher());
        this.addMouseMotionListener(new MouseMotionWatcher());
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        x = -1;
        y = -1;
        currentColor = c;
        currentThickness = t;
        currentEntityType = et;
        currentRotation = 0;
        currentBrightness = 1;

        // instantiate the ArrayList to store shapes
        containers = new ArrayList<>();
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
        rotateTheta = currentRotation * Math.PI / 180;
        g2d.rotate(rotateTheta, this.getWidth() / 2, this.getHeight() / 2);

        // Loop though the ArrayList drawing
        // all the shapes stored in it
        for (Object aShape : containers) {
            // draw the correct sort of shape: line or oval or triangle
            Container shape = (Container) aShape;
            //   System.out.println(shape.getContained().toString());
            if (aShape instanceof DrawableI) {
                DrawableI ld = (DrawableI) aShape;
                ld.drawShape(g2d, currentBrightness);
            }
        }

        g2d.setStroke(s);  // restore saved stroke

        if (currentPoints != null && currentPoints.size() >= 1) { // draw dot where line started
            for (int i = 0; i < currentPoints.size(); i++) {
                g2d.setColor(currentColor);
                g2d.fillOval(currentPoints.get(i).x, currentPoints.get(i).y, 3, 3);
            }
        }
    }

    /**
     * @return the currentShapeType
     */
    public Entity.EntityType getCurrentShapeType() {
        return currentEntityType;
    }

    /**
     * @param currentShapeType the currentShapeType to set
     */
    public void setCurrentShapeType(Entity.EntityType currentShapeType) {
        this.currentEntityType = currentShapeType;
    }

    /**
     * @return the currentBrightness
     */
    public float getCurrentBrightness() {
        return currentBrightness;
    }

    /**
     * @return the currentColor
     */
    public Color getColour() {
        return this.currentColor;
    }

    /*
     * currentBrightness is passed in as a number in the range
     * 0 to 1.  In this class it needs to be in the range 0.75 to
     * 1.25 which is what the division by 2 and addition of
     * 0.75 achieves.
     */
    public void setCurrentBrightness(float currentBrightness) {
        this.currentBrightness = (currentBrightness / 2) + 0.75F;
        repaint();
    }

    /* MouseWatcher is an inner class used to handle the
     * mouse events generated by the user clicking on the drawing panel
     */
    private class MouseWatcher extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
                      // reset the rotation to 0 otherwise things get messy.

            currentRotation = 0;
            selected = getContainerAtLoc(e.getPoint());
            if (selected != null) {
                if (DrawingState.MOVING.equals(state)) {
                    InteractiveShape selected = (InteractiveShape) containers.get(indexSelectedShape);
                    int offsetX = e.getX() - orginalX;
                    int offsetY = e.getY() - orginalY;
                    containers.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX, offsetY)));
                    //shapes.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX,offsetY)));
                    repaint();
                }
                selected.Select();
                //        System.out.println(selected.getShapeType().toString());
                orginalX = e.getX();
                orginalY = e.getY();
                translateCoords = new ArrayList<Point>();

                for (Point coords : selected.getContained().getStructuralPoints()) {
                    translateCoords.add(coords);
                }

                state = DrawingState.MOVING;
                return;
            }
            state = DrawingState.DRAWING;
            if (currentPoints == null) { // must be starting a new shape
                currentPoints = new ArrayList<>();
                Point firstPoint = new Point();
                firstPoint.x = e.getX();
                firstPoint.y = e.getY();
                currentPoints.add(firstPoint);
                System.out.print(firstPoint.toString());
            } else { // shape must have already been started
                // decide what to do based on the current shape;
                currentPoints.add(e.getPoint());
                if(currentPoints.size() == ContainerFactory.getRequiredPoints(currentPoints, currentColor, currentThickness, currentEntityType)){
                Container container = ContainerFactory.createEntity(currentPoints, currentColor, currentThickness, currentEntityType);
                System.out.println(container.getContained().toString());
                containers.add(container);
                currentPoints = null;
                }
            }
            repaint(); 
        }
    }

    public class MouseMotionWatcher implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (DrawingState.MOVING.equals(state)) {
                InteractiveShape selected = (InteractiveShape) containers.get(indexSelectedShape);
                int offsetX = e.getX() - orginalX;
                int offsetY = e.getY() - orginalY;
                containers.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX, offsetY)));
                //shapes.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX,offsetY)));
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

    }

    public boolean isFirstClick() {
        return currentPoints == null ;
    }

    public boolean isDoubleClick() {
        int size = currentPoints.size();
        return size>=2 && currentPoints.get(size-2).equals(currentPoints.get(size-1));
    }

    /**
     * @param locPoint coordinates of the point to check
     * @return the shape at that location or null if there is nothing
     *
     */
    public Container getContainerAtLoc(Point locPoint) {

        if (selected != null) {
            selected.deSelect();
        }

        Container container = null;
        int count = 0;
        for (Container s : this.containers) {
            if (s.contains(locPoint)) {
                this.indexSelectedShape = count;
                container = s;
            }
            count++;
        }
        return container;
    }

    public void setCurrentThickness(int currentThickness) {
        this.currentThickness = currentThickness;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void clearDisplay() {
        // Empty the ArrayList and clear the display.
        containers.clear();
        repaint();
    }

    /* The whole drawing area can be rotated left or right.
     * The amount passed in is the amount in degrees to rotate.
     * A negative number roates to the left and a positive number to
     * the right
     */
    public void rotate(int amount) {
        currentRotation += amount;
        repaint();
    }

    public void setShapes(List shapes) {
        this.containers = shapes;
    }
}
