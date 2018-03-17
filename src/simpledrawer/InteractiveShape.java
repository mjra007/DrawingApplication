package simpledrawer;

import java.awt.Dimension;
import simpledrawer.shapes.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 * The classes implementing this interface will be able to me modified much
 * easier by the user. This interface will allow us to know when user has
 * clicked a shape If Shape is overlaping another rshape etc.
 *
 */
public interface InteractiveShape {

    /*Checks whether the shape contains a certain coordinate 
    * @return true if it does   and false if not
    * @param point to be checked
     */
    public enum SelectedPart {
        WHOLE, CORNERS, RIGHTSIDE, LEFTSIDE, BOTTOMSIDE, TOPSIDE;
    }

    /**
     * Checks if the shape if p is inside the object where you are calling this
     * method
     *
     * @param p point that needs to be checked
     * @return true if point is indeed inside the shape
     */
    public boolean contains(Point p);

    /**
     * Checks if the shape if p is inside the object where you are calling this
     * method
     *
     * @param p point that needs to be checked
     * @return true if point is indeed inside the shape
     */
    public boolean rightSideContains(Point p);

    public boolean bottomSideContains(Point p);

    public boolean cornersContain(Point p);

    public Container updateLocation(Point old, Point origin);

    public Container resize(Dimension oldDimension, Point offset, InteractiveShape.SelectedPart part);

    public Container linearResizing(float amount);

    public Container rotate(double rotation,Point xy);

    public List<Point> getStructuralPoints();

    public double getRotation();

    public void setRotation(Double rotation);
}
