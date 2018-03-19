/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import drawingpanel.DrawingPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpledrawer.shapes.Container.Container;

/**
 *
 * @author micae
 */
public class ContainerSpawnAnimation extends Thread {

    private final Container container;
    private final DrawingPanel canvas;

    public ContainerSpawnAnimation(Container cont, DrawingPanel dp) {
        container = cont;
        canvas = dp;
    }

    @Override
    public void run() {
        int oldWidth = container.getWidth();
        int oldHeight = container.getHeight();
        int initialWidth = container.getWidth() / 2;
        int initialHeight = container.getHeight() / 2;
        container.setHeight(initialHeight);
        container.setWidth(initialWidth);
        while (initialWidth <= oldWidth || initialHeight <= oldHeight) {
            if (initialHeight <= oldHeight) {
                initialHeight++;
                container.setHeight(initialHeight);
            }
            if (initialWidth <= oldWidth) {
                initialWidth++;
                container.setWidth(initialWidth);
            }

            canvas.refresh();
            try {
                sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(ContainerSpawnAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
