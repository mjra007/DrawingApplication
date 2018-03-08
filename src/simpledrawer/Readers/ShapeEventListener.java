package simpledrawer.Readers;

import simpledrawer.DrawableI;
import simpledrawer.shapes.Container;

/**
 * Interface implemented by classes that want to be notified of ShapeEvents
 * @author Gill Windall
 */
public interface ShapeEventListener {
    public void processShapeEvent(Object originator, DrawableI se);
    
}
