package usmb.info405.grp1.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.affichage.panels.admin.entrepot.EntrepotPanel;
import usmb.info405.grp1.app.affichage.panels.admin.produits.ProduitPanel;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.UtilisateurPanel;
import usmb.info405.grp1.app.affichage.panels.reservations.ReservationPanel;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.AppRole;
import usmb.info405.grp1.app.affichage.panels.gestReservations.GestReservationsPanel;
import usmb.info405.grp1.app.affichage.panels.utilisateur.ProfilePanel;
import usmb.info405.grp1.app.affichage.panels.vosReservations.VosReservationsPanel;
import usmb.info405.grp1.app.utilitaire.AbstractPanel;
import usmb.info405.grp1.app.utilitaire.DataApi;

//Panel structurel qui va faire le switch entre tout les autres panels de l'application 
//TO-DO check avec les autres si on le laisse là ou on le passe dans menu
public class ContenantPane extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	private GestReservationsPanel gestReservationsPanel;
	private VosReservationsPanel vosReservations;
	private ReservationPanel reservations;
	private ProfilePanel profil;
	private UtilisateurPanel gestUtilisateur;
	private ProduitPanel gestProduit;
	private EntrepotPanel gestEntrepot;
	private String role = UnderframePanel.getRoleFromUserInfo();
	
	public ContenantPane() {
		
		init();
		//setBackground(Color.pink);
		setLayout(new FlowLayout()); //ne fonctionne qu'avec ce layout, un peu mystérieux
	}
	@Override
	//Generation differenciee en fonciton des roles
	public void init() {
			/*
		 	Box box = new Box(BoxLayout.Y_AXIS);
	        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	        box.add(Box.createVerticalGlue());
	        box.add(new ProfilePanel());
	        box.add(Box.createVerticalGlue());
	        add(box);
	        */
		
		if (this.role.equals("ADMIN")) {
			
			this.profil = new ProfilePanel();
			this.gestEntrepot = new EntrepotPanel();
			this.gestUtilisateur = new UtilisateurPanel();
			this.gestProduit = new ProduitPanel();
			this.reservations = new ReservationPanel();
			this.vosReservations = new VosReservationsPanel();
			this.gestReservationsPanel = new GestReservationsPanel();
			
			add(profil);
			add(gestUtilisateur);
			add(gestProduit);
			add(gestEntrepot);
			add(vosReservations);
			add(reservations);
			add(gestReservationsPanel);
			
			
		} else {
			this.profil = new ProfilePanel();
			this.reservations = new ReservationPanel();
			this.vosReservations = new VosReservationsPanel();
			add(profil);
			add(vosReservations);
			add(reservations);
		}
		
		hideAllPanels();
		

	}
	//choisi les panels uniquement concerner par l'user
	public void choosePanelUser(String choix) {
		hideAllPanels();
		switch (choix) {
		case "profil": {
			
			profil.setVisible(true);
			break;
		
		}
		case "vosReservations": {
			remove(vosReservations);
			vosReservations = new VosReservationsPanel();
			add(vosReservations);
			vosReservations.setVisible(true);
			break;
		}
		case "reservations":{
			reservations.setVisible(true);
			break;
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choix);
		}
		
		
		
		
	}
	
	//L'objectif de la fonction est de choisir le pannel à mettre dans la frame
		//In : String envoyer par le listener sur les boutons du menu
		public void choosePanel(String choix) {
			hideAllPanels();
			//L'on commence par vérifier que notre choix n'est pas celui qui est déjà selectionner
			
			if (this.role.equals("USER")) {
				choosePanelUser(choix);
				
			} else {
				switch (choix) {
				
				case "profil": {
					profil.setVisible(true);
					break;
				}
				case "gestReservations": {
					gestReservationsPanel.setVisible(true);
					gestReservationsPanel.updateRecapCreneaux();
					break;
				}
				
				case "gestUtilisateur":{
					gestUtilisateur.setVisible(true);
					break;
				}
				
				case "gestProduit":{
					remove(gestProduit);
					gestProduit = new ProduitPanel();
					add(gestProduit);
					gestProduit.setVisible(true);
					break;
				}
				
				case "gestEntrepot":{
					gestEntrepot.setVisible(true);
					break;
				}
				case "vosReservations": {
					//recharchement pour le dynamisme
					remove(vosReservations);
					vosReservations = new VosReservationsPanel();
					add(vosReservations);
					vosReservations.setVisible(true);
					break;
				}
				case "reservations":{
					reservations.setVisible(true);
					break;
					
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + choix);
					
				}
				
			}
			
			
			revalidate();
			repaint();
			
			
		}
		
	public void hideAllPanelsUser() {
		profil.setVisible(false);
		reservations.setVisible(false);
		vosReservations.setVisible(false);
	}
	
		//objectif est de cacher tout les panels
	private void hideAllPanels() {
		if (this.role.equals("USER")) {
			hideAllPanelsUser();
			
		} else {
			profil.setVisible(false);
			gestUtilisateur.setVisible(false);
			gestProduit.setVisible(false);
			gestEntrepot.setVisible(false);
			vosReservations.setVisible(false);
			reservations.setVisible(false);
			gestReservationsPanel.setVisible(false);
		}
		
	}

	

}
