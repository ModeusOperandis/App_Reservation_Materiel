package usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.popup.component;

import usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.popup.GlassPanePopup;

import javax.swing.*;

public class GlassPaneChild extends JPanel {

    protected PopupController controller;
    protected PopupCallbackAction callbackAction;

    public int getRoundBorder() {
        return 0;
    }

    public void onPush() {

    }

    public void onPop() {

    }

    public void popupShow() {
    }

    protected PopupController createController() {
        return new PopupController() {

            @Override
            public void closePopup() {
                GlassPanePopup.closePopup(GlassPaneChild.this);
            }
        };
    }
}
