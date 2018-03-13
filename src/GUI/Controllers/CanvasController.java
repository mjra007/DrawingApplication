package GUI.Controllers;

import GUI.DrawingState;
import GUI.Models.CanvasDrawings;
import GUI.Models.OptionsModel;
import GUI.View;
import GUI.Views.Canvas;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import javax.swing.SwingUtilities;
import simpledrawer.DrawableI;
import simpledrawer.InteractiveShape;
import simpledrawer.Utils;
import simpledrawer.shapes.Container;
import simpledrawer.shapes.ContainerI;
import simpledrawer.shapes.Entity;
import simpledrawer.shapes.Shape;
import simpledrawer.shapes.ShapeFactory;

public class CanvasController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private View view;
    private CanvasDrawings entitiesModel;
    private OptionsModel guiOptions;

    private Point xy;

    public CanvasController(CanvasDrawings m, OptionsModel guiOptions) {
        entitiesModel = m;
        this.guiOptions = guiOptions;
    }

    public void addView(View view) {
        this.view = view;
        setupListener();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && checkLocforDrawables(xy) != null) {
            Canvas canvas = (Canvas) view;
            canvas.getRightMenu().show(canvas, e.getX(), e.getY());
        }

    }

    public Canvas getView() {
        return (Canvas) view;
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
    }

    private void delteButton(ActionEvent evt) {
       entitiesModel.removeSelected();
        getView().refresh();
    }

    private void fillShape(ActionEvent evt) {
        Container container = (Container) this.entitiesModel.getSelected();
        Shape shape = (Shape) container.getContained();
        shape.setFilled(true);
        shape.setFilledColor(guiOptions.getCurrentColor());
        getView().refresh();
    }

    public List<Point> getClicks() {
        return guiOptions.getClicks();
    }

    public Color getCurrentColor() {
        return guiOptions.getCurrentColor();
    }

    public List<DrawableI> getDrawingList() {
        return entitiesModel.getDrawings();
    }

    public int getCurrentRotation() {
        return guiOptions.getCurrentRotation();
    }

    public boolean isBetterGraphicsSelected() {
        return guiOptions.getbetterGraphics();
    }

    public int getThickness() {
        return guiOptions.getCurrentThickness();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("" + guiOptions.getState());
        xy = e.getPoint();
        if (guiOptions.getState().equals(DrawingState.MOVING)) {
            guiOptions.resetOldClicks();
            DrawableI drawable = this.entitiesModel.getSelected();
            if (drawable instanceof Container) {
                Container container = (Container) drawable;
                if (container.getContained() instanceof Entity) {
                    Entity entity = (Entity) container.getContained();
                    guiOptions.addMouseClicktoOldPoints(entity.getStructuralPoints().get(0));
                }
            }
            view.refresh();
        }
        if (guiOptions.getState().equals(DrawingState.DRAWING)) {
            if (guiOptions.getClicks() == null) {
                //as it is let s add the current point clicked to the current points list
                guiOptions.addMouseClick(e.getPoint());
                //in case it is not the first point
            } else if (guiOptions.getClicks() != null) {
                //add point to the list
                guiOptions.addMouseClick(e.getPoint());
                //and check wehther we meet the amount of required points for the shape selected
                //   System.out.print("" + this.guiOptions.getEntityTypeSelected());
                if (guiOptions.getClicks().size() == 2) {
                    List<Point> reorganizedCoords = Utils.getReorganizedCoords(guiOptions.getClicks().get(0), guiOptions.getClicks().get(1));
                    int width = reorganizedCoords.get(1).x - reorganizedCoords.get(0).x;
                    int height = reorganizedCoords.get(1).y - reorganizedCoords.get(0).y;
                    Entity entity = ShapeFactory.createShape(reorganizedCoords.get(0), width, height, guiOptions.getCurrentColor(), guiOptions.getCurrentThickness(), guiOptions.getEntityTypeSelected());
                    //adding container to the list of containers to be drawn
                    if (entity instanceof ContainerI) {
                        ContainerI entityContainerI = (ContainerI) entity;
                        Container container = new Container(entityContainerI);
                        entitiesModel.getDrawings().add(container);

                    } else {
                        entitiesModel.getDrawings().add(entity);
                    }
                    //reseting the cpoints selected
                    guiOptions.resetClicks();
                }
                view.refresh();
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("" + guiOptions.getState());
        if (DrawingState.MOVING.equals(guiOptions.getState())) {

            DrawableI drawable = entitiesModel.getSelected();
            if (drawable instanceof InteractiveShape) {
                InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                //figuring out the offset of our first click and the last done
                int offsetX = e.getX() - xy.x;
                int offsetY = e.getY() - xy.y;
                entitiesModel.getDrawings().add(entitiesModel.getIndexSelect(), interactiveSelected.updateLocation(guiOptions.getOldClicks().get(0), new Point(offsetX, offsetY)));
                view.refresh();
            }
        } else if (DrawingState.RESIZING.equals(guiOptions.getState())) {
            /*  InteractiveShape interactiveSelected = (InteractiveShape) entitiesModel.getDrawings().get(entitiesModel.getIndexSelect());
            //figuring out the offset of our first click and the last done
            int offsetX = e.getX() + currentPoints.get(currentPoints.size()-1).x;
            int offsetY = e.getY() + currentPoints.get(currentPoints.size()-1).y;
            entitiesModel.getDrawings().add(entitiesModel.getIndexSelect(), interactiveSelected.resize(oldPoints, new Point(offsetX, offsetY)));
            //shapes.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX,offsetY)));
            myView.refresh();*/
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //   System.out.println("" + guiOptions.getState());
        InteractiveShape intercShape = checkLocforDrawables(e.getPoint());
        if (intercShape == null) {
            guiOptions.setDrawingState(DrawingState.DRAWING);
        } else {
            guiOptions.setDrawingState(DrawingState.MOVING);
        }
    }

    public InteractiveShape checkLocforDrawables(Point locPoint) {
        InteractiveShape intercShape = null;
        for (int i = 0; i < entitiesModel.getDrawings().size(); i++) {
            DrawableI drawable = entitiesModel.getDrawings().get(i);
            if (drawable instanceof InteractiveShape) {
                intercShape = (InteractiveShape) drawable;
                if (intercShape.contains(locPoint)) {
                    Container container = (Container) drawable;
                    entitiesModel.setSelected(container, i);
                    return intercShape;
                }
            }
            intercShape = null;
        }
        entitiesModel.setSelected(null, null);
        return intercShape;
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

        InteractiveShape intercShape = checkLocforDrawables(e.getPoint());
        if (intercShape != null) {
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                float amount = e.getWheelRotation() * 5f;
                intercShape.resize(amount);
                view.refresh();
            }
        }
    }

}
