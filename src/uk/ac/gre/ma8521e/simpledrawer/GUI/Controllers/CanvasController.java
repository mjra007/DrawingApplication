package uk.ac.gre.ma8521e.simpledrawer.GUI.Controllers;

import uk.ac.gre.ma8521e.simpledrawer.GUI.DrawingState;
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
import uk.ac.gre.ma8521e.simpledrawer.Utils;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.Container;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.ContainerI;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawingIndicator;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.Shape;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.InteractiveShapeI;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Controller;
import uk.ac.gre.ma8521e.simpledrawer.GUI.Model;
import uk.ac.gre.ma8521e.simpledrawer.GUI.View;

public class CanvasController implements Controller,MouseListener, MouseMotionListener, MouseWheelListener {

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
                if (drawable instanceof InteractiveShapeI) {
                    if (drawable instanceof Container) {
                        Container container = (Container) drawable;
                        canvasOptions.addMouseClicktoOldPoints(container.getContained().getStructuralPoints().get(0));

                    } else {
                        InteractiveShapeI interactiveShape = (InteractiveShapeI) drawable;
                        canvasOptions.addMouseClicktoOldPoints(interactiveShape.getStructuralPoints().get(0));
                    }
                }
            }
            if (canvasOptions.getState().equals(DrawingState.RESIZING_BOTTOM) || canvasOptions.getState().equals(DrawingState.RESIZING_RIGHTSIDE)) {
                if (canvas.getSelectedDrawing() instanceof InteractiveShapeI) {
                    InteractiveShapeI interactive = (Container) canvas.getSelectedDrawing();
                    canvasOptions.setSavedDimension(new Dimension(interactive.getWidth(), interactive.getHeight()));
                }
            }
            if (canvasOptions.getState().equals(DrawingState.DRAWING)) {
                if (canvasOptions.getClicks() == null) {
                    //as it is let s add the current point clicked to the current points list
                    canvasOptions.addMouseClick(e.getPoint());
                    canvas.addDrawing(new DrawingIndicator(e.getPoint()));
                    //in case it is not the first point
                } else if (canvasOptions.getClicks() != null) {
                    //add point to the list
                    canvasOptions.addMouseClick(e.getPoint());
                    canvas.addDrawing(new DrawingIndicator(e.getPoint()));
                    //and check wehther we meet the amount of required points for the shape selected
                    //   System.out.print("" + this.canvasOptions.getEntityTypeSelected());
                    System.out.println("size" + canvasOptions.getClicks().size());
                    if (canvasOptions.getClicks().size() == 2) {
                        List<Point> reorganizedCoords = Utils.getReorganizedCoords(canvasOptions.getClicks().get(0), canvasOptions.getClicks().get(1));
                        int width = reorganizedCoords.get(1).x - reorganizedCoords.get(0).x;
                        int height = reorganizedCoords.get(1).y - reorganizedCoords.get(0).y;

                        DrawableI drawing= new Shape.Builder()
                                        .setOrigin(reorganizedCoords.get(0))
                                        .setWidth(width)
                                        .setHeight(height)
                                        .setColor(canvasOptions.getCurrentColor())
                                        .setBorderThickness(canvasOptions.getCurrentThickness())
                                        .setType(canvasOptions.getEntityTypeSelected())
                                        .build();

                        if (drawing instanceof ContainerI) {
                            ContainerI entityContainerI = (ContainerI) drawing;
                            Container container = entityContainerI.contain(entityContainerI);
                            // System.out.println(""+container.getContained().toString());
                            ContainerSpawnAnimation t = new ContainerSpawnAnimation(container, canvas);
                            t.start();
                            canvas.addDrawing(container);

                        } else {
                            canvas.addDrawing(drawing);
                        }
                        //reseting the cpoints selected
                        canvasOptions.resetClicks(canvas.getDrawings());
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
                        InteractiveShapeI interactiveSelected = (InteractiveShapeI) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvasOptions.getLastClick().x;
                        int offsetY = e.getY() - canvasOptions.getLastClick().y;
                        canvas.setDrawing(canvas.getSelectedDrawingIndex(), interactiveSelected.updateLocation(canvasOptions.getOldClicks().get(0), new Point(offsetX, offsetY)));

                        break;
                    }
                    case RESIZING_BOTTOM: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        InteractiveShapeI interactiveSelected = (InteractiveShapeI) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvasOptions.getLastClick().x;
                        int offsetY = e.getY() - canvasOptions.getLastClick().y;
                        canvas.setDrawing(canvas.getSelectedDrawingIndex(), interactiveSelected.resize(canvasOptions.getOldDimensions(), new Point(offsetX, offsetY), InteractiveShapeI.SelectedPart.BOTTOMSIDE));
                        break;
                    }
                    case RESIZING_RIGHTSIDE: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        InteractiveShapeI interactiveSelected = (InteractiveShapeI) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvasOptions.getLastClick().x;
                        int offsetY = e.getY() - canvasOptions.getLastClick().y;
                        canvas.setDrawing(canvas.getSelectedDrawingIndex(), interactiveSelected.resize(canvasOptions.getOldDimensions(), new Point(offsetX, offsetY), InteractiveShapeI.SelectedPart.RIGHTSIDE));
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
        InteractiveShapeI intercShape = checkLocforInteractiveShapes(e.getPoint());
        InteractiveShapeI.SelectedPart selected = null;
        if (intercShape != null) {

            selected = checkPartSelected(intercShape, e.getPoint());
            if (selected.equals(InteractiveShapeI.SelectedPart.WHOLE)) {
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                canvasOptions.setDrawingState(DrawingState.MOVING);
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShapeI.SelectedPart.RIGHTSIDE)) {
                canvasOptions.setDrawingState(DrawingState.RESIZING_RIGHTSIDE);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShapeI.SelectedPart.BOTTOMSIDE)) {
                canvasOptions.setDrawingState(DrawingState.RESIZING_BOTTOM);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            }
        } else {
            canvasOptions.setDrawingState(DrawingState.DRAWING);
        }
    }

    public InteractiveShapeI checkLocforInteractiveShapes(Point locPoint) {
        InteractiveShapeI intercShape = null;
        for (Integer key : canvas.getDrawings().keySet()) {
            DrawableI drawable = canvas.getDrawings().get(key);
            if (drawable instanceof InteractiveShapeI) {
                intercShape = (InteractiveShapeI) drawable;
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

    public InteractiveShapeI.SelectedPart checkPartSelected(InteractiveShapeI shape, Point p) {
        InteractiveShapeI.SelectedPart part = null;
        if (shape.bottomSideContains(p)) {
            part = InteractiveShapeI.SelectedPart.BOTTOMSIDE;
        } else if (shape.rightSideContains(p)) {
            part = InteractiveShapeI.SelectedPart.RIGHTSIDE;
        } else if (shape.contains(p)) {
            part = InteractiveShapeI.SelectedPart.WHOLE;
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
        InteractiveShapeI intercShape = checkLocforInteractiveShapes(e.getPoint());
        if (intercShape != null) {
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                float amount = e.getWheelRotation() * 5f;
                intercShape.linearResizing(amount);
            }
        }
    }

    @Override
    public void addModel(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addView(View view) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
