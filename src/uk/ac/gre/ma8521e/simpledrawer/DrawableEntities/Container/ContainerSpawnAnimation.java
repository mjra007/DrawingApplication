/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container;

import drawingpanel.DrawingPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container.Container;

/**
 *
 * @author micae
 */
public class ContainerSpawnAnimation extends Thread {

    private final Container CONTAINER;
    private final DrawingPanel CANVAS;

    public ContainerSpawnAnimation(Container cont, DrawingPanel dp) {
        CONTAINER = cont;
        CANVAS = dp;
    }

    @Override
    public void run() {
        int oldWidth = CONTAINER.getWidth();
        int oldHeight = CONTAINER.getHeight();
        int initialWidth = CONTAINER.getWidth() / 2;
        int initialHeight = CONTAINER.getHeight() / 2;
        CONTAINER.setHeight(initialHeight);
        CONTAINER.setWidth(initialWidth);
        while (initialWidth <= oldWidth || initialHeight <= oldHeight) {
            if (initialHeight <= oldHeight) {
                initialHeight++;
                CONTAINER.setHeight(initialHeight);
            }
            if (initialWidth <= oldWidth) {
                initialWidth++;
                CONTAINER.setWidth(initialWidth);
            }

            CANVAS.refresh();
            try {
                sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(ContainerSpawnAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
