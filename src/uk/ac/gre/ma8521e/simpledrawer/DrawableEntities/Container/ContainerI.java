package uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.Container;

import uk.ac.gre.ma8521e.simpledrawer.DrawableEntities.DrawableEntity;

public interface ContainerI {

    public default Container contain() {
        if (this instanceof DrawableEntity) {
            return new Container((DrawableEntity) this);
        }
        return null;
    }
}
