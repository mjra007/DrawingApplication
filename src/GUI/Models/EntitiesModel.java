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

public class EntitiesModel {

    private DrawableI selected;
    private List<DrawableI> entityList;
    //saves the last 100 changes
    private HashMap<Integer, List<DrawableI>> entityListHistory;
    private Integer indexSelect;

    public EntitiesModel() {
        this.entityList = new ArrayList<DrawableI>();
        this.entityListHistory = new HashMap<>();
    }


    /**
     * @return entityList to be drawn
     */
    public List<DrawableI> getEntityList() {
        return this.entityList;
    }

    /**
     *
     * @param entity to add to currentList
     */
    public void addEntityToList(DrawableI entity) {
        if(getEntityList()==null){
           this.entityList=new ArrayList<DrawableI>();
        }
        getEntityList().add(entity);
    }

    /**
     * @return entityListHistory
     */
    public HashMap<Integer, List<DrawableI>> getHistory() {
        return entityListHistory;
    }

    /**
     *
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
     *
     * @return the entity that it is currently selected
     */
    public DrawableI getSelected() {
        return this.selected;
    }
}
