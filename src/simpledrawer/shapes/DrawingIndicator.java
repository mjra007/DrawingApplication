/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer.shapes;

import drawingpanel.DrawableI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author ma8521e
 */
public class DrawingIndicator implements DrawableI {

    Point point;

    public DrawingIndicator(Point p) {
        this.point = p;
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillOval(point.x, point.y, 3, 3);
    }

}
