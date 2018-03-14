package GUI.Models;

/**
 * EntitiesModel is the model that provides the entities being drawn on the
 * drawing panel and also stores the last 100 changes. Rolling back to previous
 * versions of the drawing is not an implemented feature as of yet
 */
import com.rits.cloning.Cloner;
import java.awt.Point;
import java.util.HashMap;
import simpledrawer.DrawableI;
import simpledrawer.shapes.Container;

public class Canvas {

    private CanvasOptions options;
    private HashMap<Integer, DrawableI> drawings;
    // Saves the last 100 changes
    private HashMap<Integer, HashMap<Integer, DrawableI>> drawingsHistory;
    private Integer indexSelect;
    private int counter = 0;
    private DrawableI copied;
    private DrawableI cut;

    public Canvas(CanvasOptions op) {
        this.options = op;
        this.drawings = new HashMap<>();
        this.drawingsHistory = new HashMap<>();
    }

    public DrawableI getCopied() {
        return copied;
    }

    public DrawableI getCut() {
        return cut;
    }

    public void copy() {
        copied = (DrawableI) getDrawings().get(getIndexSelect()).clone();
    }

    public void pasteCopy(Point p) {
        if (copied instanceof Container) {
            System.out.println("pasteCopy");
            Container container = (Container) copied;
            Container containerCloned = new Cloner().deepClone(container);
            containerCloned.getContained().setOrigin(p);
            this.addDrawing(containerCloned);
        }
    }

    public void cut() {
        copied = getDrawings().get(getIndexSelect());
        removeSelected();
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
        getDrawings().put(counter, entity);
        counter++;
    }

    /**
     * @return entityListHistory
     */
    public HashMap<Integer, HashMap<Integer, DrawableI>> getHistory() {
        return drawingsHistory;
    }

    public int getIndexSelect() {
        return this.indexSelect;
    }

    /**
     * @return the entity that it is currently selected
     */
    public DrawableI getSelected() {
        return this.drawings.get(this.indexSelect);
    }

    public void removeSelected() {
        saveHistory();
        this.getDrawings().remove(getIndexSelect());
    }

    public void saveHistory() {
        int size = this.drawingsHistory.size();
        this.drawingsHistory.put(size + 1, drawings);
    }

    public void resetList() {
        getDrawings().clear();
    }

    public void setSelected(Integer key) {
        this.indexSelect = key;
    }
}
