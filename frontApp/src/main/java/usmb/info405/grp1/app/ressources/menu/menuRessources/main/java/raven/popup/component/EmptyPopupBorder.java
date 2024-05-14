package usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.popup.component;

import java.awt.*;

public class EmptyPopupBorder extends GlassPaneChild {

    public EmptyPopupBorder(Component component) {
        setLayout(new BorderLayout());
        add(component);
    }

    public EmptyPopupBorder(GlassPaneChild glassPaneChild){

    }
}
