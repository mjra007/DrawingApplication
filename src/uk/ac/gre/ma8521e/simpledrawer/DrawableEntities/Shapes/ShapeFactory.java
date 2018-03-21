package simpledrawer.shapes;
 

public class ShapeFactory {
 
    public static Shape createShape(Shape shapeBuilder) {
        Shape shape = null;
        switch (shapeBuilder.getEntityType().toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(shapeBuilder);
                shape = rect;
                break;
            case "OVAL":
                SOval oval = new SOval(shapeBuilder);
                shape = oval;
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(shapeBuilder);
                shape = triangle;
                break;
            case "LINE":
                SLine line = new SLine(shapeBuilder);
                shape = line;
                break;
            default:
                break;
        }
        return shape;
    }
 
    
}