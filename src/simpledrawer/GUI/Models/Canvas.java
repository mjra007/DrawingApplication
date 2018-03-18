package simpledrawer.GUI.Models;

/**
 * EntitiesModel is the model that provides the entities being drawn on the
 * drawing panel and also stores the last 100 changes. Rolling back to previous
 * versions of the drawing is not an implemented feature as of yet
 */
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import simpledrawer.DrawableI;

public class Canvas {

    private CanvasOptions options;
    private HashMap<Integer, DrawableI> drawings;
    // Saves the last 100 changes
    private HashMap<Integer, HashMap<Integer, DrawableI>> drawingsHistory;
    private Integer indexSelect;
    private int counter = 0;
    public static final String PROP_PUT = "put";
    public static final String PROP_REMOVE = "remove";

    private PropertyChangeSupport propertySupport;

    public Canvas(CanvasOptions op) {
        this.options = op;
        this.drawings = new HashMap<>();
        this.drawingsHistory = new HashMap<>();
        propertySupport = new PropertyChangeSupport(this);
    }

    public CanvasOptions getSettings() {
        return this.options;
    }

    /**
     * @return drawingsList to be drawn
     */
    public HashMap<Integer, DrawableI> getDrawings() {
        return this.drawings;
    }

    /**
     * @param entity to add to currentList
     */
    public void addDrawing(DrawableI entity) {
        if (getDrawings() == null) {
            this.drawings = new HashMap<Integer, DrawableI>();
        }
        saveHistory();
        Object old = getDrawings().put(counter, entity);
        propertySupport.firePropertyChange(PROP_PUT, old, entity);
        counter++;
    }

    /**
     * @return entityListHistory
     */
    public HashMap<Integer, HashMap<Integer, DrawableI>> getHistory() {
        return drawingsHistory;
    }

    public int getSelectedDrawingIndex() {
        return this.indexSelect;
    }

    /**
     * @return the entity that it is currently selected
     */
    public DrawableI getSelectedDrawing() {
        return this.drawings.get(this.indexSelect);
    }

    public void removeSelectedDrawing() {
        saveHistory();
        Object old = this.getDrawings().remove(getSelectedDrawingIndex());
        propertySupport.firePropertyChange(PROP_PUT, old,null);

    }

    public void saveHistory() {
        int size = this.drawingsHistory.size();
        this.drawingsHistory.put(size + 1, drawings);
    }

    public void resetDrawingList() {
        getDrawings().clear();
    }

    public void setDrawingSelected(Integer key) {
        this.indexSelect = key;
    }
}
