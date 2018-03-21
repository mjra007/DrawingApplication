package uk.ac.gre.ma8521e.simpledrawer.GUI.Models;

import uk.ac.gre.ma8521e.simpledrawer.GUI.Controllers.DrawingState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.CopyPasteCutI;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.EntityType;

public class CanvasOptions {

    private EntityType currentEntityType;
    private float currentBrightness;
    private int currentThickness;
    private Color currentColor;
    private DrawingState state;
    private List<Point> currentPoints;
    private List<Point> savedPoints;
    private Dimension savedDimension;
    private CopyPasteCutI copied;
    private boolean cut;
    private Point lastClick;

    public CanvasOptions() {
        currentEntityType = EntityType.LINE;
        currentColor = Color.BLACK;
        currentThickness = 2;
        currentBrightness = 1;
    }

    public CopyPasteCutI getDrawableCopied() {
        return copied;
    }
    
    public Point getLastClick(){
        return this.lastClick;
    }
    
    public void addLastClick(Point p){
        this.lastClick=p;
    }

    public boolean hasCutBeenPerformed() {
        return cut;
    }

    public void setCopy(CopyPasteCutI cutpaste) {
        this.copied = cutpaste;
    }

    public void setCut(boolean b) {
        this.cut = b;
    }

    public void setDrawingState(DrawingState s) {
        this.state = s;
    }

    public Dimension getOldDimensions() {
        return this.savedDimension;
    }

    public void setSavedDimension(Dimension d) {
        this.savedDimension = d;
    }

    public DrawingState getState() {
        return this.state;
    }


    public void addMouseClick(Point point) {
        if (currentPoints == null) {
            currentPoints = new ArrayList<>();
        }
        currentPoints.add(point);
    }

    public void addMouseClicktoOldPoints(Point point) {
        if (savedPoints == null) {
            savedPoints = new ArrayList<>();
        }
        savedPoints.add(point);
    }

    public void resetClicks() {
        this.currentPoints = null;
    }

    /**
     * resets old mouse clicks
     */
    public void resetOldClicks() {
        this.savedPoints = null;
    }

    /**
     *
     * @return list of current clicks
     */
    public List<Point> getClicks() {
        return this.currentPoints;
    }

    /**
     *
     * @return old mouse clicks
     */
    public List<Point> getOldClicks() {
        return this.savedPoints;
    }

    public void setCurrentThickness(int currentThickness) {
        this.currentThickness = currentThickness;
    }

    public float getCurrentBrightness() {
        return this.currentBrightness;
    }

    public int getCurrentThickness() {
        return this.currentThickness;
    }

    public Color getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }


    public void setCurrentBrightness(float c) {
        this.currentBrightness = c;
    }
    public void setEntityTypeSelected(EntityType type) {
        this.currentEntityType = type;

    }

    public EntityType getEntityTypeSelected() {
        return this.currentEntityType;
    }
}
