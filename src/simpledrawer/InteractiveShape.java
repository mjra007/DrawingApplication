package simpledrawer;

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
    public boolean contains(Point p);

    public boolean contains(Point p, Rectangle r);

    public Container updateLocation(Point old, Point origin);

    public Container resize(Point old, Point offset);

    public Container resize(float amount);

    public Container rotate(float amount);

    public List<Point> getStructuralPoints();
}
