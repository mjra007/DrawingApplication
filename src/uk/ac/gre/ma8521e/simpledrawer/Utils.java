package uk.ac.gre.ma8521e.simpledrawer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    /*setting the rectangle with this method prevents the endX to be lower than
        the originX and the endY to be lower than the originY. Sometimes users
        can select the end point first.
     */
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
