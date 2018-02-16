/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import simpledrawer.shapes.SLine;
import simpledrawer.shapes.SOval;
import simpledrawer.shapes.SRectangle;
import simpledrawer.shapes.STriangle;

/**
 *
 * @author ma8521e
 */
public class ContainerFactory {

    public static Container createEntity(List<Point> list, Color c, int t, Entity.EntityType et) {
        Container container = null;
        switch (et.toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(list, c, t, et);
                container = new Container(rect);
                break;
            case "OVAL":
                SOval oval = new SOval(list, c, t, et);
                container = new Container(oval);
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(list, c, t, et);
                container = new Container(triangle);
                break;
            case "LINE":
                SLine line = new SLine(list, c, t, et);
                container = new Container(line);
                break;
            default:
                break;
        }
                        container.Select();

        return container;
    }

    public static int getRequiredPoints(List<Point> list, Color c, int t, Entity.EntityType et) {
        int points = 0;
        switch (et.toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(list, c, t, et);
                points = rect.getPointsRequired();
                break;
            case "OVAL":
                SOval oval = new SOval(list, c, t, et);
                points = oval.getPointsRequired();
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(list, c, t, et);
                points = triangle.getPointsRequired();
                break;
            case "LINE":
                SLine line = new SLine(list, c, t, et);
                points = line.getPointsRequired();
                break;
            default:
                break;
        }
        return points;
    }
}
