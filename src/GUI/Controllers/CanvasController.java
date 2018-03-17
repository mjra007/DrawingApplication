package GUI.Controllers;

import GUI.DrawingState;
import GUI.Models.Canvas;
import GUI.Models.CanvasOptions;
import GUI.View;
import GUI.Views.CanvasView;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import simpledrawer.shapes.Container;
import simpledrawer.shapes.ContainerI;
import simpledrawer.shapes.DrawableEntity;
import simpledrawer.shapes.Shape;
import simpledrawer.shapes.ShapeFactory;

public class CanvasController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private View view;
    private Canvas canvas;
    private Point xy;

    public CanvasController(Canvas m) {
        canvas = m;
    }

    public void addView(View view) {
        this.view = view;
        setupListener();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && checkLocforInteractiveShapes(xy) != null) {
            getView().changeMenu(true);
            getView().getRightMenu().show(getView(), e.getX(), e.getY());
        } else if (SwingUtilities.isRightMouseButton(e)) {
            getView().changeMenu(false);
            getView().getRightMenu().show(getView(), e.getX(), e.getY());
        }
    }

    public CanvasView getView() {
        return (CanvasView) view;
    }

    public void setupListener() {
        getView().getFilled().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fillShape(evt);
            }
        });
        getView().getDelete().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                delteButton(evt);
            }
        });
        getView().getCopy().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                copyButton(evt);
            }
        });
        getView().getCut().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cutButton(evt);
            }
        });
        getView().getPaste().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pasteButton(evt);
            }
        });
    }

    private void cutButton(ActionEvent evt) {
        canvas.cut();
        getView().refresh();
    }

    private void pasteButton(ActionEvent evt) {
        if (canvas.getCopied() != null) {
            canvas.pasteCopy(xy);
            getView().refresh();
        }
    }

    private void copyButton(ActionEvent evt) {
        canvas.copy();
        getView().refresh();
    }

    private void delteButton(ActionEvent evt) {
        canvas.removeSelected();
        getView().refresh();
    }

    private void fillShape(ActionEvent evt) {
        Container container = (Container) this.canvas.getSelected();
        Shape shape = (Shape) container.getContained();
        shape.setFilled(true);
        shape.setFilledColor(canvas.getSettings().getCurrentColor());
        getView().refresh();
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

    public int getCurrentRotation() {
        return canvas.getSettings().getCurrentRotation();
    }

    public boolean isBetterGraphicsSelected() {
        return canvas.getSettings().getbetterGraphics();
    }

    public int getThickness() {
        return canvas.getSettings().getCurrentThickness();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("" + canvas.getSettings().getState());
        xy = e.getPoint();
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (canvas.getSettings().getState().equals(DrawingState.MOVING)) {
                canvas.getSettings().resetOldClicks();
                DrawableI drawable = this.canvas.getSelected();
                if (drawable instanceof Container) {
                    Container container = (Container) drawable;
                    if (container.getContained() instanceof DrawableEntity) {
                        DrawableEntity entity = (DrawableEntity) container.getContained();
                        canvas.getSettings().addMouseClicktoOldPoints(entity.getStructuralPoints().get(0));
                    }
                }
                view.refresh();
            }
            if (canvas.getSelected() instanceof Container) {
                Container container = (Container) canvas.getSelected();
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
                    if (canvas.getSettings().getClicks().size() == 2) {
                        List<Point> reorganizedCoords = Utils.getReorganizedCoords(canvas.getSettings().getClicks().get(0), canvas.getSettings().getClicks().get(1));
                        int width = reorganizedCoords.get(1).x - reorganizedCoords.get(0).x;
                        int height = reorganizedCoords.get(1).y - reorganizedCoords.get(0).y;
                        DrawableEntity entity = ShapeFactory.createShape(reorganizedCoords.get(0), width, height, canvas.getSettings().getCurrentColor(), canvas.getSettings().getCurrentThickness(), canvas.getSettings().getEntityTypeSelected());
                        //adding container to the list of containers to be drawn
                        if (entity instanceof ContainerI) {
                            ContainerI entityContainerI = (ContainerI) entity;
                            Container container = new Container(entityContainerI);
                            canvas.addDrawing(container);

                        } else {
                            canvas.addDrawing(entity);
                        }
                        //reseting the cpoints selected
                        canvas.getSettings().resetClicks();
                    }
                    view.refresh();
                }
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("" + canvas.getSettings().getState());
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (null != canvas.getSettings().getState()) {
                switch (canvas.getSettings().getState()) {
                    case MOVING: {
                        DrawableI drawable = canvas.getSelected();
                        if (drawable instanceof InteractiveShape) {
                            InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                            //figuring out the offset of our first click and the last done
                            int offsetX = e.getX() - xy.x;
                            int offsetY = e.getY() - xy.y;
                            canvas.getDrawings().put(canvas.getIndexSelect(), interactiveSelected.updateLocation(canvas.getSettings().getOldClicks().get(0), new Point(offsetX, offsetY)));
                            view.refresh();
                        }
                        break;
                    }
                    case RESIZING_BOTTOM: {
                        DrawableI drawable = canvas.getSelected();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - xy.x;
                        int offsetY = e.getY() - xy.y;
                        canvas.getDrawings().put(canvas.getIndexSelect(), interactiveSelected.resize(canvas.getSettings().getOldDimensions(), new Point(offsetX, offsetY), InteractiveShape.SelectedPart.BOTTOMSIDE));
                        view.refresh();
                        break;
                    }
                    case RESIZING_RIGHTSIDE: {
                        DrawableI drawable = canvas.getSelected();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                        //figuring out the offset of our first click and the last done
                        int offsetX = e.getX() - xy.x;
                        int offsetY = e.getY() - xy.y;
                        canvas.getDrawings().put(canvas.getIndexSelect(), interactiveSelected.resize(canvas.getSettings().getOldDimensions(), new Point(offsetX, offsetY), InteractiveShape.SelectedPart.RIGHTSIDE));
                        view.refresh();
                        break;
                    }
                    case ROTATE: {
                        double rotation;
                        DrawableI drawable = canvas.getSelected();
                        InteractiveShape interactiveSelected = (InteractiveShape) drawable;

                        Integer width = null;
                        Integer height = null;
                        Container container = (Container) drawable;
                        width = container.getWidth() - 1;
                        height = container.getHeight() - 1;

                        if (xy != null) {

                            int x = width / 2;
                            int y = height / 2;

                            int deltaX = xy.x - x;
                            int deltaY = xy.y - y;

                            rotation = -Math.atan2(deltaX, deltaY);

                            rotation = Math.toDegrees(rotation) + 180;
                            interactiveSelected.setRotation(rotation);
                        }
                        getView().refresh();
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
            } else if (checkPartSelected(intercShape, e.getPoint()).equals(InteractiveShape.SelectedPart.CORNERS)) {
                canvas.getSettings().setDrawingState(DrawingState.ROTATE);
                getView().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
                    canvas.setSelected(key);
                    return intercShape;
                }
            }
            intercShape = null;
        }
        canvas.setSelected(null);
        return intercShape;
    }

    public InteractiveShape.SelectedPart checkPartSelected(InteractiveShape shape, Point p) {
        InteractiveShape.SelectedPart part = null;
        if (shape.cornersContain(p)) {
            part = InteractiveShape.SelectedPart.CORNERS;
        } else if (shape.bottomSideContains(p)) {
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
