/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import GUI.DrawingState;
import GUI.Models.EntitiesModel;
import GUI.Models.OptionsModel;
import GUI.View;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import simpledrawer.DrawableI;
import simpledrawer.InteractiveShape;
import simpledrawer.shapes.Container;
import simpledrawer.shapes.ContainerFactory;

/**
 *
 * @author ma8521e
 */
public class CanvasController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private View view;
    private EntitiesModel entitiesModel;
    private OptionsModel guiOptions;

    private Point xy;

    public CanvasController(EntitiesModel m, OptionsModel guiOptions) {
        entitiesModel = m;
        this.guiOptions = guiOptions;
    }

    public void addView(View view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public List<Point> getClicks() {
        return guiOptions.getClicks();
    }

    public Color getCurrentColor() {
        return guiOptions.getCurrentColor();
    }

    public List<DrawableI> getDrawingList() {
        return entitiesModel.getEntityList();
    }

    public int getCurrentRotation() {
        return guiOptions.getCurrentRotation();
    }
   public int getThickness() {
        return guiOptions.getCurrentThickness();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("" + guiOptions.getState());

        xy = e.getPoint();
        if (guiOptions.getState().equals(DrawingState.MOVING)) {
            guiOptions.resetOldClicks();
            DrawableI drawable = this.entitiesModel.getSelected();
            if (drawable instanceof InteractiveShape) {
                InteractiveShape intercShape = (InteractiveShape) drawable;
                for (Point coords : intercShape.getStructuralPoints()) {
                    guiOptions.addMouseClicktoOldPoints(coords);
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
                System.out.print("" + this.guiOptions.getEntityTypeSelected());
                if (guiOptions.getClicks().size() == ContainerFactory.getRequiredPoints(guiOptions.getClicks(), guiOptions.getCurrentColor(), guiOptions.getCurrentThickness(), guiOptions.getEntityTypeSelected())) {
                    //Cool, we met the requirement of points, now we can call the factory and get the Container with tghe shape easily
                    // dont you love design patterns :p
                    Container container = ContainerFactory.createEntity(guiOptions.getClicks(), guiOptions.getCurrentColor(), guiOptions.getCurrentThickness(), guiOptions.getEntityTypeSelected());
                    //adding container to the list of containers to be drawn
                    entitiesModel.getEntityList().add(container);
                    //reseting the cpoints selected
                    guiOptions.resetClicks();
                }
                view.refresh();
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("" + guiOptions.getState());
        if (DrawingState.MOVING.equals(guiOptions.getState())) {

            DrawableI drawable = entitiesModel.getSelected();
            if (drawable instanceof InteractiveShape) {
                InteractiveShape interactiveSelected = (InteractiveShape) drawable;
                //figuring out the offset of our first click and the last done
                int offsetX = e.getX() - xy.x;
                int offsetY = e.getY() - xy.y;
                entitiesModel.getEntityList().add(entitiesModel.getIndexSelect(), interactiveSelected.updateLocation(guiOptions.getOldClicks(), new Point(offsetX, offsetY)));
                view.refresh();
            }
        } else if (DrawingState.RESIZING.equals(guiOptions.getState())) {
            /*  InteractiveShape interactiveSelected = (InteractiveShape) entitiesModel.getEntityList().get(entitiesModel.getIndexSelect());
            //figuring out the offset of our first click and the last done
            int offsetX = e.getX() + currentPoints.get(currentPoints.size()-1).x;
            int offsetY = e.getY() + currentPoints.get(currentPoints.size()-1).y;
            entitiesModel.getEntityList().add(entitiesModel.getIndexSelect(), interactiveSelected.resize(oldPoints, new Point(offsetX, offsetY)));
            //shapes.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX,offsetY)));
            myView.refresh();*/
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("" + guiOptions.getState());
        InteractiveShape intercShape = checkLocforDrawables(e.getPoint());
        if (intercShape == null) {
            guiOptions.setDrawingState(DrawingState.DRAWING);
        } else {
            guiOptions.setDrawingState(DrawingState.MOVING);
        }
    }

    public InteractiveShape checkLocforDrawables(Point locPoint) {
        InteractiveShape intercShape = null;
        for (int i = 0; i < entitiesModel.getEntityList().size(); i++) {
            DrawableI drawable = entitiesModel.getEntityList().get(i);
            if (drawable instanceof InteractiveShape) {
                intercShape = (InteractiveShape) drawable;
                if (intercShape.contains(locPoint)) {
                   Container container = (Container) drawable;
                   container.getContained().Select();
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
