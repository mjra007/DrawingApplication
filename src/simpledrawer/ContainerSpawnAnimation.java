/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledrawer;

import drawingpanel.DrawingPanel;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpledrawer.shapes.Container.Container;

/**
 *
 * @author micae
 */
public class ContainerSpawnAnimation extends Thread {

    private Container container;
    private DrawingPanel canvas;

    public ContainerSpawnAnimation(Container cont, DrawingPanel dp) {
        container = cont;
        canvas = dp;
    }

   

    @Override
    public void run() {
        int old = container.getContained().getWidth();
        int increaseAmount = container.getContained().getWidth() / 2;
       
        System.out.println("old :"+old);
        System.out.println("new: "+increaseAmount);
        container.getContained().setHeight(container.getContained().getHeight()/ 2);
        container.getContained().setWidth(increaseAmount);
   
        while (container.getContained().getWidth() <= old) {
            container.linearResizing(2);
            canvas.refresh();
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ContainerSpawnAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
