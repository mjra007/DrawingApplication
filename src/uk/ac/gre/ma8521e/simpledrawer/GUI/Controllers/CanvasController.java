package uk.ac.gre.ma8521e.simpledrawer.GUI.Controllers;

import drawingpanel.DrawableI;
import drawingpanel.DrawingPanel;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Views.RightMenuView;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.SwingUtilities;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.ContainerSpawnAnimation;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Models.CanvasOptions;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.InteractiveShape;
import uk.ac.gre.ma8521e.simpledrawer.Utils;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.Container;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.ContainerI;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.Shape;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.ShapeFactory;

public class CanvasController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private DrawingPanel canvas;
    private CanvasOptions canvasOptions;
    private RightMenuView rightMenu;

    public CanvasController(CanvasOptions co) {
        this.canvasOptions = co;
    }

    public void addView(DrawingPanel view, RightMenuView rightView) {
        this.canvas = view;
        this.rightMenu = rightView;
        setupListeners();
    }

    public void setupListeners() {
        canvas.addPropertyChangeListener("remove", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                drawingListChangedListener(evt);
            }
        });
        canvas.addPropertyChangeListener("put", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                drawingListChangedListener(evt);
            }
        });
    }

    public void drawingListChangedListener(PropertyChangeEvent evt) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //  canvasOptions.addMouseClick(e.getPoint());

    }

    private boolean isRightClick(MouseEvent e) {
        return SwingUtilities.isRightMouseButton(e);
    }

    public DrawingPanel getView() {
        return (DrawingPanel) canvas;
    }

    public void showRightClickMenu(Point p) {
        rightMenu.buildPopupMenu().show(getView(), p.x, p.y);
        rightMenu.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //saving the last click
        canvasOptions.addLastClick(e.getPoint());
        /**
         * if the this mouse click was right click let s call the right click
         * popup menu
         */
        if (isRightClick(e)) {
            showRightClickMenu(e.getPoint());
        }

        //if it is left click we might want to save some details
        if (!isRightClick(e)) {
            if (canvasOptions.getState().equals(DrawingState.MOVING)) {
                canvasOptions.resetOldClicks();
                DrawableI drawable = this.canvas.getSelectedDrawing();
                if (drawable instanceof Container) {
                    Container container = (Container) drawable;
                    if (container.getContained() instanceof DrawableEntity) {
                        DrawableEntity entity = (DrawableEntity) container.getContained();
                        canvasOptions.addMouseClicktoOldPoints(entity.getStructuralPoints().get(0));
                    }
                }
            }
            if (canvas.getSelectedDrawing() instanceof Container) {
                Container container = (Container) canvas.getSelectedDrawing();
                canvasOptions.setSavedDimension(new Dimension(container.getWidth(), container.getHeight()));
            }
            if (canvasOptions.getState().equals(DrawingState.DRAWING)) {
                if (canvasOptions.getClicks() == null) {
                    //as it is let s add the current point clicked to the current points list
                    canvasOptions.addMouseClick(e.getPoint());
                    //in case it is not the first point
                } else if (canvasOptions.getClicks() != null) {
                    //add point to the list
                    canvasOptions.addMouseClick(e.getPoint());
                    //and check wehther we meet the amount of required points for the shape selected
                    //   System.out.print("" + this.canvasOptions.getEntityTypeSelected());
                    System.out.println("size" + canvasOptions.getClicks().size());
                    if (canvasOptions.getClicks().size() == 2) {
                        List<Point> reorganizedCoords = Utils.getReorganizedCoords(canvasOptions.getClicks().get(0), canvasOptions.getClicks().get(1));
                        int width = reorganizedCoords.get(1).x - reorganizedCoords.get(0).x;
                        int height = reorganizedCoords.get(1).y - reorganizedCoords.get(0).y;

                        DrawableEntity entity = ShapeFactory.createShape(
                                new Shape.Builder()
                                        .setOrigin(reorganizedCoords.get(0))
                                        .setWidth(width)
                                        .setHeight(height)
                                        .setColor(canvasOptions.getCurrentColor())
                                        .setBorderThickness(canvasOptions.getCurrentThickness())
                                        .setType(canvasOptions.getEntityTypeSelected())
                                        .build());
                        
                        if (entity instanceof ContainerI) {
                            ContainerI entityContainerI = (ContainerI) entity;
                            Container container = entityContainerI.contain(entityContainerI);
                            // System.out.println(""+container.getContained().toString());
                            ContainerSpawnAnimation t = new ContainerSpawnAnimation(container,canvas);
                            t.start();
                            canvas.addDrawing(container);

                        } else {
                            canvas.addDrawing(entity);
                        }
                        //reseting the cpoints selected
                        canvasOptions.resetClicks();
                    }
                }
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("" + canvasOptions.getState());

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (null != canvasOptions.getState()) {
                switch (canvasOptions.getState()) {
                    case MOVING: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        if (drawable instanceof InteractiveShape) {
                            InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                            //figuring out the offset of our first click and the last done
                            int offsetX = e.getX() - canvasOptions.getLastClick().x;
                            int offsetY = e.getY() - canvasOptions.getLastClick().y;
                            canvas.setDrawing(canvas.getSelectedDrawingIndex(), interactiveSelected.updateLocation(canvasOptions.getOldClicks().get(0), new Point(offsetX, offsetY)));

                        }
                        break;
                    }
                    case RESIZING_BOTTOM: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvasOptions.getLastClick().x;
                        int offsetY = e.getY() - canvasOptions.getLastClick().y;
                        canvas.setDrawing(canvas.getSelectedDrawingIndex(), interactiveSelected.resize(canvasOptions.getOldDimensions(), new Point(offsetX, offsetY), InteractiveShape.SelectedPart.BOTTOMSIDE));
                        break;
                    }
                    case RESIZING_RIGHTSIDE: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvasOptions.getLastClick().x;
                        int offsetY = e.getY() - canvasOptions.getLastClick().y;
                        canvas.setDrawing(canvas.getSelectedDrawingIndex(), interactiveSelected.resize(canvasOptions.getOldDimensions(), new Point(offsetX, offsetY), InteractiveShape.SelectedPart.RIGHTSIDE));
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        getView().setCursor(Cursor.getDefaultCursor());
        InteractiveShape intercShape = checkLocforInteractiveShapes(e.getPoint());
        InteractiveShape.SelectedPart selected = null;
        if (intercShape != null) {

            selected = checkPartSelected(intercShape, e.getPoint());
            if (selected.equals(InteractiveShape.SelectedPart.WHOLE)) {
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                canvasOptions.setDrawingState(DrawingState.MOVING);
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShape.SelectedPart.RIGHTSIDE)) {
                canvasOptions.setDrawingState(DrawingState.RESIZING_RIGHTSIDE);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShape.SelectedPart.BOTTOMSIDE)) {
                canvasOptions.setDrawingState(DrawingState.RESIZING_BOTTOM);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            }
        } else {
            canvasOptions.setDrawingState(DrawingState.DRAWING);
        }
    }

    public InteractiveShape checkLocforInteractiveShapes(Point locPoint) {
        InteractiveShape intercShape = null;
        for (Integer key : canvas.getDrawings().keySet()) {
            DrawableI drawable = canvas.getDrawings().get(key);
            if (drawable instanceof InteractiveShape) {
                intercShape = (InteractiveShape) drawable;
                if (intercShape.contains(locPoint)) {
                    canvas.setDrawingSelected(key);
                    return intercShape;
                }
            }
            intercShape = null;
        }
        canvas.setDrawingSelected(null);
        return intercShape;
    }

    public InteractiveShape.SelectedPart checkPartSelected(InteractiveShape shape, Point p) {
        InteractiveShape.SelectedPart part = null;
        if (shape.bottomSideContains(p)) {
            part = InteractiveShape.SelectedPart.BOTTOMSIDE;
        } else if (shape.rightSideContains(p)) {
            part = InteractiveShape.SelectedPart.RIGHTSIDE;
        } else if (shape.contains(p)) {
            part = InteractiveShape.SelectedPart.WHOLE;
        }
        //   System.out.println("part" + part);
        return part;
    }

    @Override
    public void mouseReleased(MouseEvent e) { //To change body of

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        InteractiveShape intercShape = checkLocforInteractiveShapes(e.getPoint());
        if (intercShape != null) {
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                float amount = e.getWheelRotation() * 5f;
                intercShape.linearResizing(amount);
            }
        }
    }

}
