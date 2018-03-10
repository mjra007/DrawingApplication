
package simpledrawer.shapes;

import java.awt.Point;

/**
 * Every class that implement this can be contained 
 * and use a container. The advatanges of using a container are many
 * as it allows you to resize, move and rotate your shape
 */
public interface ContainerI{
    
    public int getWidth();
    public int getHeight();
    public void setWidth(int newWidth);
    public void setHeight(int newHeight);
    public void setOrigin(Point newPoint);
    public Point getOrigin();
    public default Container contain(){
        return new Container(this);
    }
}
