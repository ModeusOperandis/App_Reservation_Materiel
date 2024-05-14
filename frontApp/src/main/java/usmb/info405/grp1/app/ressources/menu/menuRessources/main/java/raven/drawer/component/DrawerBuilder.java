package usmb.info405.grp1.app.ressources.menu.menuRessources.main.java.raven.drawer.component;

import java.awt.*;

public interface DrawerBuilder {

    public void build(DrawerPanel drawerPanel);

    public Component getHeader();

    public Component getHeaderSeparator();

    public Component getMenu();

    public Component getFooter();

    public int getDrawerWidth();
}
