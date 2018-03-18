package simpledrawer.shapes.Container;

import simpledrawer.shapes.DrawableEntity;

public interface ContainerI {

    public default Container contain(ContainerI cI) {
        if (this instanceof DrawableEntity) {
            return new Container((DrawableEntity) cI);
        }
        return null;
    }
}
