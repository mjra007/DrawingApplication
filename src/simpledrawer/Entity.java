package simpledrawer;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micael
 */
public abstract class Entity implements DrawableI {

    // Type of shape e.g. line or oval
    private final EntityType type; // currently always SHAPE
    private final int pointsRequired;
    private int width;
    private int height;

    //These points are crucical points that the shapes depend on
    private List<Point> structuralPoints = new ArrayList<Point>();

    public Entity(List<Point> list,int pointsRequired, EntityType et) {
        structuralPoints = list;
        type = et;
        this.pointsRequired=pointsRequired;
        this.height = this.structuralPoints.get(1).y - this.getStructuralPoints().get(0).y;
        this.width = list.get(1).x - list.get(0).x;
        this.height = list.get(1).y - list.get(0).y;
    }

    public Entity() {
        this.type = EntityType.LINE;
        this.height = 5;
        this.width = 5;
        pointsRequired = 0;
    }

    /*
    All the current Entity Types that our program holds should be here
     */
    public static enum EntityType {
        LINE, OVAL, TRIANGLE, RECTANGLE;
    }

    public void setStructuralPoints(List<Point> list) {
        this.structuralPoints = list;
    }

    public List<Point> getStructuralPoints() {
        return this.structuralPoints;
    }

    public int getPointsRequired(){
        return pointsRequired;
    }
    
    public Entity.EntityType getEventType() {
        return this.type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static List<Point> getReorganizedCoords(Point point, Point point2) {

        int x, xend, y, yend;
        List<Point> listPoints = new ArrayList<Point>();

        x = point.x;
        xend = point2.x;
        y = point.y;
        yend = point2.y;

        if (xend < x) {
            int mem = x;
            x = xend;
            xend = mem;
        }
        if (yend < y) {
            int mem = y;
            y = yend;
            yend = mem;

        }
        listPoints.add(new Point(x, y));
        listPoints.add(new Point(xend, yend));
        return listPoints;
    }
}
