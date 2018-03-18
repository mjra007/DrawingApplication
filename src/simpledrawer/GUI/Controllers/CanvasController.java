package simpledrawer.GUI.Controllers;

import simpledrawer.GUI.DrawingState;
import simpledrawer.GUI.Models.Canvas;
import simpledrawer.GUI.Views.CanvasView;
import simpledrawer.GUI.Views.RightMenuView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.SwingUtilities;
import simpledrawer.DrawableI;
import simpledrawer.InteractiveShape;
import simpledrawer.Utils;
import simpledrawer.shapes.Container.Container;
import simpledrawer.shapes.Container.ContainerI;
import simpledrawer.shapes.DrawableEntity;
import simpledrawer.shapes.DrawableEntity.EntityType;
import simpledrawer.shapes.Shape;
import simpledrawer.shapes.ShapeFactory;

public class CanvasController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private CanvasView view;
    private Canvas canvas;
    private RightMenuView rightMenu;

    public CanvasController(Canvas m) {
        canvas = m;
    }

    public void addView(CanvasView view, RightMenuView rightView) {
        this.view = view;
        this.rightMenu = rightView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //  canvas.getSettings().addMouseClick(e.getPoint());

    }

    private boolean isRightClick(MouseEvent e) {
        return SwingUtilities.isRightMouseButton(e);
    }

    public CanvasView getView() {
        return (CanvasView) view;
    }

    public List<Point> getClicks() {
        return canvas.getSettings().getClicks();
    }

    public Color getCurrentColor() {
        return canvas.getSettings().getCurrentColor();
    }

    public HashMap<Integer, DrawableI> getDrawingList() {
        return canvas.getDrawings();
    }

    public Color getBackgroundColor() {
        return canvas.getSettings().getBackground();
    }

    public int getCurrentRotation() {
        return canvas.getSettings().getCurrentRotation();
    }

    public boolean isBetterGraphicsSelected() {
        return canvas.getSettings().getbetterGraphics();
    }

    public int getThickness() {
        return canvas.getSettings().getCurrentThickness();
    }

    public void showRightClickMenu(Point p) {
        rightMenu.buildPopupMenu().show(getView(), p.x, p.y);
        rightMenu.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //saving the last click
        canvas.getSettings().addLastClick(e.getPoint());
        /**
         * if the this mouse click was right click let s call the right click
         * popup menu
         */
        if (isRightClick(e)) {
            showRightClickMenu(e.getPoint());
        }

        //if it is left click we might want to save some details
        if (!isRightClick(e)) {
            if (canvas.getSettings().getState().equals(DrawingState.MOVING)) {
                canvas.getSettings().resetOldClicks();
                DrawableI drawable = this.canvas.getSelectedDrawing();
                if (drawable instanceof Container) {
                    Container container = (Container) drawable;
                    if (container.getContained() instanceof DrawableEntity) {
                        DrawableEntity entity = (DrawableEntity) container.getContained();
                        canvas.getSettings().addMouseClicktoOldPoints(entity.getStructuralPoints().get(0));
                    }
                }
            }
            if (canvas.getSelectedDrawing() instanceof Container) {
                Container container = (Container) canvas.getSelectedDrawing();
                canvas.getSettings().setSavedDimension(new Dimension(container.getWidth(), container.getHeight()));
            }
            if (canvas.getSettings().getState().equals(DrawingState.DRAWING)) {
                if (canvas.getSettings().getClicks() == null) {
                    //as it is let s add the current point clicked to the current points list
                    canvas.getSettings().addMouseClick(e.getPoint());
                    //in case it is not the first point
                } else if (canvas.getSettings().getClicks() != null) {
                    //add point to the list
                    canvas.getSettings().addMouseClick(e.getPoint());
                    //and check wehther we meet the amount of required points for the shape selected
                    //   System.out.print("" + this.canvas.getSettings().getEntityTypeSelected());
                    System.out.println("size" + canvas.getSettings().getClicks().size());
                    if (canvas.getSettings().getClicks().size() == 2) {
                        List<Point> reorganizedCoords = Utils.getReorganizedCoords(canvas.getSettings().getClicks().get(0), canvas.getSettings().getClicks().get(1));
                        int width = reorganizedCoords.get(1).x - reorganizedCoords.get(0).x;
                        int height = reorganizedCoords.get(1).y - reorganizedCoords.get(0).y;

                        DrawableEntity entity = ShapeFactory.createShape(
                                new Shape.Builder()
                                .setOrigin(reorganizedCoords.get(0))
                                .setWidth(width)
                                .setHeight(height)
                                .setColor(canvas.getSettings().getCurrentColor())
                                .setBorderThickness(canvas.getSettings().getCurrentThickness())
                                .setType(canvas.getSettings().getEntityTypeSelected())
                                .build());
                        DrawableEntity entityTriangle = ShapeFactory.createShape(
                                new Shape.Builder()
                                .setOrigin(reorganizedCoords.get(0))
                                .setWidth(width)
                                .setHeight(height)
                                .setColor(canvas.getSettings().getCurrentColor())
                                .setBorderThickness(canvas.getSettings().getCurrentThickness())
                                .setType(EntityType.TRIANGLE)
                                .build());
                        
                        if (entity instanceof ContainerI) {
                            ContainerI entityContainerI = (ContainerI) entity;
                            ContainerI entityTriangleI = (ContainerI) entityTriangle;
                            Container containerTriangle = entityTriangleI.contain(entityTriangleI);
                            Container container = entityContainerI.contain(entityContainerI);
                            container.contain(containerTriangle);
                           // System.out.println(""+container.getContained().toString());
                            canvas.addDrawing(container);
                        
                        } else {
                            canvas.addDrawing(entity);
                        }
                        //reseting the cpoints selected
                        canvas.getSettings().resetClicks();
                    }
                }
            }
            view.refresh();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("" + canvas.getSettings().getState());

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (null != canvas.getSettings().getState()) {
                switch (canvas.getSettings().getState()) {
                    case MOVING: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        if (drawable instanceof InteractiveShape) {
                            InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                            //figuring out the offset of our first click and the last done
                            int offsetX = e.getX() - canvas.getSettings().getLastClick().x;
                            int offsetY = e.getY() - canvas.getSettings().getLastClick().y;
                            canvas.getDrawings().put(canvas.getSelectedDrawingIndex(), interactiveSelected.updateLocation(canvas.getSettings().getOldClicks().get(0), new Point(offsetX, offsetY)));
                            view.refresh();
                        }
                        break;
                    }
                    case RESIZING_BOTTOM: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvas.getSettings().getLastClick().x;
                        int offsetY = e.getY() - canvas.getSettings().getLastClick().y;
                        canvas.getDrawings().put(canvas.getSelectedDrawingIndex(), interactiveSelected.resize(canvas.getSettings().getOldDimensions(), new Point(offsetX, offsetY), InteractiveShape.SelectedPart.BOTTOMSIDE));
                        view.refresh();
                        break;
                    }
                    case RESIZING_RIGHTSIDE: {
                        DrawableI drawable = canvas.getSelectedDrawing();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - canvas.getSettings().getLastClick().x;
                        int offsetY = e.getY() - canvas.getSettings().getLastClick().y;
                        canvas.getDrawings().put(canvas.getSelectedDrawingIndex(), interactiveSelected.resize(canvas.getSettings().getOldDimensions(), new Point(offsetX, offsetY), InteractiveShape.SelectedPart.RIGHTSIDE));
                        view.refresh();
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
                canvas.getSettings().setDrawingState(DrawingState.MOVING);
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShape.SelectedPart.RIGHTSIDE)) {
                canvas.getSettings().setDrawingState(DrawingState.RESIZING_RIGHTSIDE);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShape.SelectedPart.BOTTOMSIDE)) {
                canvas.getSettings().setDrawingState(DrawingState.RESIZING_BOTTOM);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            }
        } else {
            canvas.getSettings().setDrawingState(DrawingState.DRAWING);
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
        System.out.println("part" + part);
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
        int x = e.getX();
        int y = e.getY();

        InteractiveShape intercShape = checkLocforInteractiveShapes(e.getPoint());
        if (intercShape != null) {
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                float amount = e.getWheelRotation() * 5f;
                intercShape.linearResizing(amount);
                view.refresh();
            }
        }
    }

}
