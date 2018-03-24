package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities;
 
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.SLine;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.SOval;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.SRectangle;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Shapes.STriangle;



public class DrawableFactory {
 
    public static DrawableEntity createDrawable(DrawableEntity drawableEntity) {
        DrawableEntity drawable = null;
        switch (drawableEntity.getEntityType().toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(drawableEntity);
                drawable = rect;
                break;
            case "OVAL":
                SOval oval = new SOval(drawableEntity);
                drawable = oval;
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(drawableEntity);
                drawable = triangle;
                break;
            case "LINE":
                SLine line = new SLine(drawableEntity);
                drawable = line;
                break;
            default:
                break;
        }
        return drawable;
    }
 
    
}