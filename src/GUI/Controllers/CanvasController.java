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
import java.util.ArrayList;
import java.util.List;
import simpledrawer.InteractiveShape;
import simpledrawer.shapes.Container;
import simpledrawer.shapes.ContainerFactory;
import simpledrawer.shapes.Entity;
import simpledrawer.shapes.SOval;

/**
 *
 * @author ma8521e
 */
public class CanvasController implements MouseListener, MouseMotionListener {

    private View view;
    private EntitiesModel entitiesModel;
    private OptionsModel guiOptions;

    public CanvasController( EntitiesModel m, OptionsModel guiOptions) {
        entitiesModel = m;
        this.guiOptions = guiOptions;
    }

    public void addView(View view){
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

    public List<Entity> getDrawingList() {
        return entitiesModel.getEntityList();
    }

    public int getCurrentRotation() {
        return guiOptions.getCurrentRotation();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (guiOptions.getState().equals(DrawingState.MOVING)) {
            guiOptions.addMouseClick(new Point(e.getPoint().x, e.getPoint().y));
            guiOptions.resetOldClicks();
            for (Point coords : entitiesModel.getSelected().getStructuralPoints()) {
                guiOptions.addMouseClicktoOldPoints(coords);
            }
            view.refresh();
        }

        if (guiOptions.getState().equals(DrawingState.RESIZING)) {
            //needs to be written
            view.refresh();
        }

        if (guiOptions.getState().equals(DrawingState.DRAWING)) {
            if (guiOptions.getClicks() == null) {
                System.out.println(("drawing null"));
                //as it is let s add the current point clicked to the current points list
                guiOptions.addMouseClick(e.getPoint());
                //in case it is not the first point
            } else if (guiOptions.getClicks() != null) {
                //add point to the list
                guiOptions.addMouseClick(e.getPoint());
                //and check wehther we meet the amount of required points for the shape selected
                if (guiOptions.getClicks().size() == ContainerFactory.getRequiredPoints(guiOptions.getClicks(), guiOptions.getCurrentColor(), guiOptions.getCurrentThickness(), guiOptions.getEntityTypeSelected())) {
                    //Cool, we met the requirement of points, now we can call the factory and get the Container with tghe shape easily
                    // dont you love design patterns :p
                    Container container = ContainerFactory.createEntity(guiOptions.getClicks(), guiOptions.getCurrentColor(), guiOptions.getCurrentThickness(), guiOptions.getEntityTypeSelected());
                    //adding container to the list of containers to be drawn
                    entitiesModel.getEntityList().add(container.getContained());
                    //reseting the cpoints selected
                    guiOptions.resetClicks();
                }
                view.refresh();
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //   System.out.println("pressing");

        if (DrawingState.MOVING.equals(guiOptions.getState())) {
            Entity entity = entitiesModel.getEntityList().get(entitiesModel.getIndexSelect());
            InteractiveShape interactiveSelected = (InteractiveShape) new Container(entity);
            //figuring out the offset of our first click and the last done
            int offsetX = e.getX() - guiOptions.getClicks().get(guiOptions.getClicks().size() - 1).x;
            int offsetY = e.getY() - guiOptions.getClicks().get(guiOptions.getClicks().size() - 1).y;
            entitiesModel.getEntityList().add(entitiesModel.getIndexSelect(), interactiveSelected.updateLocation(guiOptions.getOldClicks(), new Point(offsetX, offsetY)).getContained());
            //shapes.add(indexSelectedShape, selected.translate(translateCoords, new Point(offsetX,offsetY)));
            view.refresh();
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
        //        System.out.println("moving");
        Container container = checkLocforEntities(e.getPoint());
        if (container == null) {
            guiOptions.setDrawingState(DrawingState.DRAWING);
        } else if (container.getContained().isSelected()) {
            guiOptions.setDrawingState(DrawingState.MOVING);
            System.out.print("moving");
        } else if (container.getContained().areCornersSelected()) {
            guiOptions.setDrawingState(DrawingState.ROTATE);
            System.out.print("rotate");

        }
    }

    public Container checkLocforEntities(Point locPoint) {
        Container containedEntity = null;

        entitiesModel.setSelected(null, null);
        for (int i = 0; i < entitiesModel.getEntityList().size(); i++) {
            Entity entity = entitiesModel.getEntityList().get(i);
            containedEntity = new Container(entity);
            if (containedEntity.contains(locPoint)) {
                if (containedEntity.bottomLeftCornerContains(locPoint)
                        || containedEntity.bottomRightCornerContains(locPoint)
                        || containedEntity.topLeftCornerContains(locPoint)
                        || containedEntity.topRightCornerContains(locPoint)) {
                    entitiesModel.setSelected(entity, i);
                    guiOptions.setDrawingState(DrawingState.MOVING);
                    return containedEntity;
                }
                entitiesModel.setSelected(entity, i);
                
            }
        }
        return containedEntity;
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
}
