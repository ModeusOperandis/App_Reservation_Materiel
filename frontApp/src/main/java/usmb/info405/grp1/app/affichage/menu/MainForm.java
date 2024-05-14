package usmb.info405.grp1.app.affichage.menu;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.ApplicationFrame;
import usmb.info405.grp1.app.ContenantPane;



//Il s'agit du fond principal de l'appli background à changer avec un accueil correspondant
public class MainForm extends JPanel {
	
	//private SystemMenu systemMenu;
	
	private ContenantPane contenantPanel;
	private ApplicationFrame frame;
	
	private static final long serialVersionUID = 1L;

	public MainForm(ContenantPane panel, ApplicationFrame frame) {
		this.frame = frame;
		this.contenantPanel = panel;

		init();
	}
	
	public void init() {
		setLayout(new MigLayout("fill", "[fill]", "[fill]"));
		
		SystemMenu systemMenu = new SystemMenu(this.contenantPanel, this.frame);
		
		add(systemMenu, "dock west, gap 6 6 6 6, width 280!");
		
	}
	
	
	
}

//Copyright (c) 2023 Raven Laing
