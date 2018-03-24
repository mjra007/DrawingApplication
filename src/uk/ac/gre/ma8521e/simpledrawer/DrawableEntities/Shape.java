/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities;

import java.awt.Point;
import uk.ac.gre.ma8521e.simpledrawer.Utils;

/**
 *
 * @author micae
 */
public abstract class Shape extends DrawableEntity {

    public Shape(DrawableEntity dE) {
        super(dE);
        super.setStructuralPoints(Utils.getReorganizedCoords(dE.getStructuralPoints().get(0), dE.getStructuralPoints().get(1)));
        super.setWidth(super.getStructuralPoints().get(1).x - super.getStructuralPoints().get(0).x);
        super.setHeight( super.getStructuralPoints().get(1).y - super.getStructuralPoints().get(0).y);
        }

    /**
     * @param newWidth
     *
     * set a new width for this shape
     */
    @Override
    public void setWidth(int newWidth) {
        getStructuralPoints().set(1, new Point(getOrigin().x + newWidth, getOrigin().y));
        super.setWidth(newWidth);
    }

    /**
     *
     * @param newHeight
     *
     * set a new height for this shape
     */
    @Override
    public void setHeight(int newHeight) {
        getStructuralPoints().set(1, new Point(getOrigin().x, getOrigin().y + newHeight));
        super.setHeight(newHeight);
    }

}
