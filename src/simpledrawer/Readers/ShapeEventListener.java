package simpledrawer.Readers;

import simpledrawer.Shape;

/**
 * Interface implemented by classes that want to be notified of ShapeEvents
 * @author Gill Windall
 */
public interface ShapeEventListener {
    public void processShapeEvent(Object originator, Shape se);
    
}
