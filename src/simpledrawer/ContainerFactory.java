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
import simpledrawer.shapes.ShapeType;

/**
 *
 * @author ma8521e
 */
public class ContainerFactory {

    public static Container createEntity(List<Point> list, Color c, int t, ShapeType st) {

        List<Point> reorganizedPoints;
        int width_mod, height_mod;
        Container container = null;
        switch (st.toString()) {
            case "RECTANGLE":
                reorganizedPoints = Utils.getReorganizedCoords(list.get(0), list.get(1));
                width_mod = reorganizedPoints.get(1).x - reorganizedPoints.get(0).x;
                height_mod = reorganizedPoints.get(1).y - reorganizedPoints.get(0).y;
                SRectangle rect = new SRectangle(reorganizedPoints.get(0), width_mod, height_mod, c, t, st);
                container = new Container(rect, reorganizedPoints.get(0), width_mod, height_mod);
                break;
            case "OVAL":
                reorganizedPoints = Utils.getReorganizedCoords(list.get(0), list.get(1));
                width_mod = reorganizedPoints.get(1).x - reorganizedPoints.get(0).x;
                height_mod = reorganizedPoints.get(1).y - reorganizedPoints.get(0).y;
                SOval oval = new SOval(reorganizedPoints.get(0), width_mod, height_mod, c, t, st);
                container = new Container(oval, reorganizedPoints.get(0), width_mod, height_mod);
                break;
            case "TRIANGLE":
                width_mod = list.get(1).x - list.get(0).x;
                height_mod = list.get(1).y - list.get(0).y;
                STriangle triangle = new STriangle(list, width_mod, height_mod, c, t, st);
                container = new Container(triangle, list.get(0), width_mod, height_mod);
                break;
            case "line":
                width_mod = list.get(1).x - list.get(0).x;
                height_mod = list.get(1).y - list.get(0).y;
                SLine line = new SLine(list.get(0), list.get(1), width_mod, height_mod, c, t, st);
                container = new Container(line, list.get(0), width_mod, height_mod);
                break;
            default:
                break;
        }
        return container;
    }

}
