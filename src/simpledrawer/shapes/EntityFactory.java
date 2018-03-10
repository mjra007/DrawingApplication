/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author ma8521e
 */
public class EntityFactory {

    public static Entity createEntity(Point origin, int width, int height, Color c, int t, int pR, Entity.EntityType et) {
        Entity entity = null;
        switch (et.toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle(origin, width, height, c, t, pR, et);
                entity = rect;
                break;
            case "OVAL":
                SOval oval = new SOval(origin, width, height, c, t, pR, et);
                entity = oval;
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle(origin, width, height, c, t, pR, et);
                entity = triangle;
                break;
            case "LINE":
                SLine line = new SLine(origin, width, height, c, t, pR, et);
                entity = line;
                break;
            default:
                break;
        }
        return entity;
    }

    public static int getRequiredPoints(Entity.EntityType et) {
        int points = 0;
        switch (et.toString()) {
            case "RECTANGLE":
                SRectangle rect = new SRectangle();
                points = rect.getPointsRequired();
                break;
            case "OVAL":
                SOval oval = new SOval();
                points = oval.getPointsRequired();
                break;
            case "TRIANGLE":
                STriangle triangle = new STriangle();
                points = triangle.getPointsRequired();
                break;
            case "LINE":
                SLine line = new SLine();
                points = line.getPointsRequired();
                break;
            default:
                break;
        }
        return points;
    }
}
