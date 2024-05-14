package usmb.info405.grp1.app.affichage.menu;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import usmb.info405.grp1.app.Application;
import usmb.info405.grp1.app.ApplicationFrame;
import usmb.info405.grp1.app.ContenantPane;
import usmb.info405.grp1.app.UnderframePanel;
import usmb.info405.grp1.app.utilitaire.AbstractPanel;

public class MenuPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private ContenantPane contenantPanel;
	private ApplicationFrame frame;

	private String role = UnderframePanel.getRoleFromUserInfo();
	
	public static JRadioButton dark;
	public static JRadioButton light;
	
	public MenuPanel(ContenantPane contenantPanel, ApplicationFrame f) {
		this.frame = f;
		this.contenantPanel = contenantPanel;
		init();
	}

	@Override
	public void init() {
		// Layout
		setLayout(new GridLayout(0, 1));

		// Ajout temporaire des boutons profil
		JButton cmdProfil = new JButton("profil");
		
		
		//Gestion des utilisateur admin
		JButton cmdGestUtilisateur = new JButton("Gerer user");
		
		
		//Gestion des produits admin
		JButton cmdGestProduit = new JButton("Gerer produits");
		
		
		//Gestion de l'entrepot admin
		JButton cmdGestEntrepot = new JButton("Gerer entrepot");
		
		//vos reservation
		JButton cmdButtonVosReservation = new JButton("Vos reservations");
		
		//reservation
		JButton cmdButtonReservation = new JButton("Reserver Materiel");
		
		
		
		
		if (role.equals("USER")) {
			//Ajout temporaire des boutons profil
			add(cmdProfil);
			cmdProfil.addActionListener(new cmdProfilListener());
			add(cmdButtonVosReservation);
			cmdButtonVosReservation.addActionListener(new cmdButtonVosReservationListener());
			add(cmdButtonReservation);
			cmdButtonReservation.addActionListener(new cmdReservationsListener());
			add(createThemeSelectPanel());
			
		} else {
			add(cmdProfil);
			cmdProfil.addActionListener(new cmdProfilListener());
			
			add(cmdGestUtilisateur);
			cmdGestUtilisateur.addActionListener(new cmdGestUtilisateurListener());
			
			add(cmdGestProduit);
			cmdGestProduit.addActionListener(new cmdGestProduitListener());
			
			add(cmdGestEntrepot);
			cmdGestEntrepot.addActionListener(new cmdGestEntrepotListener());
			
			add(cmdButtonVosReservation);
			cmdButtonVosReservation.addActionListener(new cmdButtonVosReservationListener());
			
			add(cmdButtonReservation);
			cmdButtonReservation.addActionListener(new cmdReservationsListener());
			
			add(createThemeSelectPanel());
			
			
			
			
		}
		
	}
	
	
	
	private Component createThemeSelectPanel() {
		JPanel ThemePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		
		JRadioButton LightButton = new JRadioButton("Light");
		LightButton.addActionListener(new cmdLightListener());
		LightButton.setSelected(true);
		MenuPanel.light = LightButton;
		ThemePanel.add(MenuPanel.light);
		
		JRadioButton DarkButton = new JRadioButton("Dark");
		DarkButton.addActionListener(new cmdDarkListener());
		MenuPanel.dark = DarkButton;
		ThemePanel.add(MenuPanel.dark);
		
		
		return ThemePanel;
		
	}
	
	//permet le switch des boutons des themes
	public static void selectTheme() {
		if (FlatLaf.isLafDark()) {
			MenuPanel.dark.setSelected(false);
			MenuPanel.light.setSelected(true);
			
		} else {
			MenuPanel.dark.setSelected(true);
			MenuPanel.light.setSelected(false);
			
		}
		
	}
	

	final class cmdReservationsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			contenantPanel.choosePanel("reservations");

		}
	}
	

	final class cmdButtonVosReservationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			contenantPanel.choosePanel("vosReservations");

		}
	}

	final class cmdLightListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.SwitchLightTheme();
			selectTheme();
			
			
		}
		
	}
	
	final class cmdDarkListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.SwitchDarkTheme();
			selectTheme();
			
		}
		

	}

	final class cmdProfilListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			contenantPanel.choosePanel("profil");

		}
	}

	final class cmdGestUtilisateurListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			contenantPanel.choosePanel("gestUtilisateur");

		}

	}

	final class cmdGestProduitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			contenantPanel.choosePanel("gestProduit");

		}

	}

	final class cmdGestEntrepotListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			contenantPanel.choosePanel("gestEntrepot");

		}

	}

	

}

