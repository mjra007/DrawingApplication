package GUI.Models;

/**
 * EntitiesModel is the model that provides the entities being drawn on the
 * drawing panel and also stores the last 100 changes. Rolling back to previous
 * versions of the drawing is not an implemented feature as of yet
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import simpledrawer.shapes.Entity;
import simpledrawer.shapes.SOval;

public class EntitiesModel {

    private Entity selected;
    private List<Entity> entityList;
    //saves the last 100 changes
    private HashMap<Integer, List<Entity>> entityListHistory;
    private List<Entity> drawingPoints ;
    private Integer indexSelect;

    public EntitiesModel() {
        this.entityList = new ArrayList<Entity>();
        this.entityListHistory = new HashMap<>();
    }


    /**
     * @return entityList to be drawn
     */
    public List<Entity> getEntityList() {
        return this.entityList;
    }

    /**
     *
     * @param entity to add to currentList
     */
    public void addEntityToList(Entity entity) {
        if(getEntityList()==null){
           this.entityList=new ArrayList<Entity>();
        }
        getEntityList().add(entity);
    }

    /**
     * @return entityListHistory
     */
    public HashMap<Integer, List<Entity>> getHistory() {
        return entityListHistory;
    }

    /**
     *
     * @param et the entity you want to make selected
     */
    public void setSelected(Entity et, Integer i) {
        this.selected = et;
        this.setIndexSelect(i);
    }

    private void setIndexSelect(Integer index) {
        this.indexSelect = index;
    }

    public void addDrawingPoints(SOval oval){
        if(getDrawingPoints()==null){
           this.drawingPoints=new ArrayList<Entity>();
        }
        this.drawingPoints.add(oval);
    }
    
    public List<Entity> getDrawingPoints(){
        return this.drawingPoints;
    }
    
    @Deprecated
    public int getIndexSelect() {
        return this.indexSelect;
    }

    /**
     *
     * @return the entity that it is currently selected
     */
    public Entity getSelected() {
        return this.selected;
    }
}
