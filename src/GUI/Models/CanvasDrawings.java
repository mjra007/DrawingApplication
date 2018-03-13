package GUI.Models;

/**
 * EntitiesModel is the model that provides the entities being drawn on the
 * drawing panel and also stores the last 100 changes. Rolling back to previous
 * versions of the drawing is not an implemented feature as of yet
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import simpledrawer.DrawableI;

public class CanvasDrawings {

    private DrawableI selected;
    private List<DrawableI> drawingsList;
    // Saves the last 100 changes
    private HashMap<Integer, List<DrawableI>> drawingsHistory;
    private Integer indexSelect;

    public CanvasDrawings() {
        this.drawingsList = new ArrayList<DrawableI>();
        this.drawingsHistory = new HashMap<>();
    }

    /**
     * @return drawingsList to be drawn
     */
    public List<DrawableI> getDrawings() {
        return this.drawingsList;
    }

    /**
     * @param entity to add to currentList
     */
    public void addDrawing(DrawableI entity) {
        if (getDrawings() == null) {
            this.drawingsList = new ArrayList<DrawableI>();
        }
        saveHistory();
        getDrawings().add(entity);
    }

    /**
     * @return entityListHistory
     */
    public HashMap<Integer, List<DrawableI>> getHistory() {
        return drawingsHistory;
    }

    /**
     * @param et the entity you want to make selected
     */
    public void setSelected(DrawableI et, Integer i) {
        this.selected = et;
        this.setIndexSelect(i);
    }

    private void setIndexSelect(Integer index) {
        this.indexSelect = index;
    }

    @Deprecated
    public int getIndexSelect() {
        return this.indexSelect;
    }

    /**
     * @return the entity that it is currently selected
     */
    public DrawableI getSelected() {
        return this.selected;
    }

    public void removeSelected() {
        saveHistory();
        this.getDrawings().remove(selected);
    }

    public void saveHistory() {
        int size = this.drawingsHistory.size();
        this.drawingsHistory.put(size+1, drawingsList);
    }

    public void resetList() {
        getDrawings().clear();
    }
}
