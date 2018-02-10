/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ma8521e
 */
public class Utils {

    public static List<Point> getReorganizedCoords(Point point, Point point2) {
        
        int x,xend,y,yend;
        List<Point> listPoints= new ArrayList<Point>();
       
        x= point.x;
        xend= point2.x;
        y= point.y;
        yend= point2.y;
                          
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
       listPoints.add(new Point(x,y));
       listPoints.add(new Point(xend,yend));
        return listPoints;
    }

}
