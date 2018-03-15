package GUI.Models;

import GUI.DrawingState;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import simpledrawer.shapes.DrawableEntity;

public class CanvasOptions {

    private DrawableEntity.EntityType currentEntityType;
    private float currentBrightness;
    private int currentRotation;
    private int currentThickness;
    private Color currentColor;
    private Color background;
    private DrawingState state;
    private List<Point> currentPoints;
    private List<Point> oldPoints;
    private boolean betterGraphics;

    public CanvasOptions() {
        currentEntityType = DrawableEntity.EntityType.LINE;
        currentColor = Color.BLACK;
        currentThickness = 2;
        currentRotation = 0;
        currentBrightness = 1;
    }

    public void setDrawingState(DrawingState s) {
        this.state = s;
    }

    
    public DrawingState getState() {
        return this.state;
    }

    public Color getBackground() {
        return this.background;
    }

    public void setBackground(Color c) {
        this.background = c;
    }

    public void addMouseClick(Point point) {
        if (currentPoints == null) {
            currentPoints = new ArrayList<>();
        }
        currentPoints.add(point);
    }

    public void addMouseClicktoOldPoints(Point point) {
        if (oldPoints == null) {
            oldPoints = new ArrayList<>();
        }
        oldPoints.add(point);
    }

    public void resetClicks() {
        this.currentPoints = null;
    }

    /**
     * resets old mouse clicks
     */
    public void resetOldClicks() {
        this.oldPoints = null;
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
        return this.oldPoints;
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

    public int getCurrentRotation() {
        return this.currentRotation;
    }

    public void setCurrentBrightness(float c) {
        this.currentBrightness = c;
    }

    public void setbetterGraphics(boolean bG) {
        this.betterGraphics = bG;
    }

    public boolean getbetterGraphics() {
        return this.betterGraphics;
    }

    /* The whole drawing area can be rotated left or right.
     * The amount passed in is the amount in degrees to rotate.
     * A negative number roates to the left and a positive number to
     * the right
     */
    public void rotate(int amount) {
        currentRotation += amount;
    }

    public void setEntityTypeSelected(DrawableEntity.EntityType type) {
        this.currentEntityType = type;

    }

    public DrawableEntity.EntityType getEntityTypeSelected() {
        return this.currentEntityType;
    }
}