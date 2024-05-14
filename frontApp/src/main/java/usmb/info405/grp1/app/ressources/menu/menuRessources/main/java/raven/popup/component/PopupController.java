package usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.popup.component;

public abstract class PopupController {

    private boolean consume;

    public boolean getConsume() {
        return consume;
    }

    public void consume() {
        consume = true;
    }

    public abstract void closePopup();
}
