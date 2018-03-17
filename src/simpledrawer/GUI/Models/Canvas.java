package GUI.Models;

/**
 * EntitiesModel is the model that provides the entities being drawn on the
 * drawing panel and also stores the last 100 changes. Rolling back to previous
 * versions of the drawing is not an implemented feature as of yet
 */
import com.rits.cloning.Cloner;
import java.awt.Point;
import java.util.HashMap;
import simpledrawer.CopyPasteCutI;
import simpledrawer.DrawableI;
import simpledrawer.shapes.Container;

public class Canvas {

    private CanvasOptions options;
    private HashMap<Integer, DrawableI> drawings;
    // Saves the last 100 changes
    private HashMap<Integer, HashMap<Integer, DrawableI>> drawingsHistory;
    private Integer indexSelect;
    private int counter = 0;
    private CopyPasteCutI copied;
    private boolean cut;

    public Canvas(CanvasOptions op) {
        this.options = op;
        this.drawings = new HashMap<>();
        this.drawingsHistory = new HashMap<>();
    }

    public CopyPasteCutI getCopied() {
        return copied;
    }

    private boolean getCut() {
        return cut;
    }

    private void setCut(boolean b) {
        this.cut = b;
    }

    public void copy() {
        DrawableI drawable = getDrawings().get(getIndexSelect());
        if (drawable instanceof CopyPasteCutI) {
            CopyPasteCutI copyIitem = (CopyPasteCutI) drawable;
            copied = (CopyPasteCutI) copyIitem.clone();
        }
    }

    public void pasteCopy(Point p) {
        CopyPasteCutI copyIitem = new Cloner().deepClone(copied);
        copyIitem.setOrigin(p);
        this.addDrawing((DrawableI) copyIitem);
        if (getCut()) {
            this.copied = null;
            cut = false;
        }

    }

    public void cut() {
        DrawableI drawable = getDrawings().get(getIndexSelect());
        if (drawable instanceof CopyPasteCutI) {
            CopyPasteCutI copyIitem = (CopyPasteCutI) drawable;
            copied = (CopyPasteCutI) copyIitem.clone();
        }
        setCut(true);
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
