package simpledrawer.shapes;
 
import java.awt.Color;
import java.awt.Point;
 
public class ShapeFactory {
 
    public static Shape createShape(Point origin, int width, int height, Color c, int t, DrawableEntity.EntityType et) {
        Shape shape = null;
        switch (et.toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(origin, width, height, c, t, et);
                shape = rect;
                break;
            case "OVAL":
                SOval oval = new SOval(origin, width, height, c, t, et);
                shape = oval;
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(origin, width, height, c, t, et);
                shape = triangle;
                break;
            case "LINE":
                SLine line = new SLine(origin, width, height, c, t, et);
                shape = line;
                break;
            default:
                break;
        }
        return shape;
    }
 
    
}