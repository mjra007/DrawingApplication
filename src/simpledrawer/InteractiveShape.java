/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author micael
 * The classes implementing this interface will be able
 * to me modified much easier by the user.
 * This interface will allow us to know when user has clicked a shape
 * If Shape is overlaping another rshape etc.
 * 
 */
public interface InteractiveShape {
    
    /*Checks whether the shape contains a certain coordinate 
    * @return true if it does   and false if not
    * @param point to be checked
    */
    public boolean contains(Point p);
    
    public Container translate(Point offset);
    
}
