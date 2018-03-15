/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import java.awt.Point;

/**
 *
 * @author micae
 */
public interface CopyPasteCutI {
    
    public Object clone();
    
    public void setOrigin(Point p);
    
}
