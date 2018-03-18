package simpledrawer.shapes.Container;

import simpledrawer.shapes.DrawableEntity;

public interface ContainerI {

    public default Container contain() {
        if (this instanceof DrawableEntity) {
            return new Container((DrawableEntity) this);
        }
        return null;
    }
}
