package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Point;

public class EntityFactory {

    public static Entity createEntity(Point origin, int width, int height, Color c, int t, Entity.EntityType et) {
        Entity entity = null;
        switch (et.toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(origin, width, height, c, t, et);
                entity = rect;
                break;
            case "OVAL":
                SOval oval = new SOval(origin, width, height, c, t, et);
                entity = oval;
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(origin, width, height, c, t, et);
                entity = triangle;
                break;
            case "LINE":
                SLine line = new SLine(origin, width, height, c, t, et);
                entity = line;
                break;
            default:
                break;
        }
        return entity;
    }

    
}
