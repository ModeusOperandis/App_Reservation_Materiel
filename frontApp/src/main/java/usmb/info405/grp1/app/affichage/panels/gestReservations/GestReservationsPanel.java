package usmb.info405.grp1.app.affichage.panels.gestReservations;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.Scrollable;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.formdev.flatlaf.FlatClientProperties;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.Utilisateur;
import usmb.info405.grp1.app.affichage.panels.vosReservations.ReservationsFunctions;
import usmb.info405.grp1.app.models.Creneau;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.AbstractPanel;
import usmb.info405.grp1.app.utilitaire.DataApi;
import usmb.info405.grp1.app.utilitaire.ScrollablePanel;

/**
 * panel pour visualiser nos reservations
 */
public class GestReservationsPanel extends AbstractPanel  implements ReservationsFunctions{

	private static final long serialVersionUID = 1L;

	private ArrayList<Creneau> creneaux;

	private long idUser;
	
	///represente le panels avec les creneaux
	
	JPanel parentCreneauxPanel = new JPanel(); // r

	/// contructeur
	public GestReservationsPanel() {
		init();
		
	}
	
	@Override
	public void init() {
		setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		
		//id 
		idUser = (Integer) DataApi.getinstance().getUserInfo().get("id");
		
		//reservation
		parentCreneauxPanel.setLayout((LayoutManager) new BoxLayout(parentCreneauxPanel, BoxLayout.Y_AXIS));
		
		
		
		//title
		JLabel titleLabel = new JLabel("Gestion des réservations ");
		titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
		titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		titleLabel.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		add(titleLabel);
		
		add(Box.createRigidArea(new Dimension(0,25)));
		
		//Note
		/*
		JLabel NoteLabel = new JLabel("Cliquez sur une réservation pour plus de détails");
		NoteLabel.setFont(NoteLabel.getFont().deriveFont(11.0f));
		NoteLabel.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		add(NoteLabel);
		NoteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		

		
		add(Box.createRigidArea(new Dimension(0,25)));
		*/

		//Implémentation de la scrollBar
		parentCreneauxPanel.putClientProperty(FlatClientProperties.STYLE,"" +
				"arc:20;" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		JScrollPane resaScrollPane = new JScrollPane(parentCreneauxPanel);
		resaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resaScrollPane.setMaximumSize(new Dimension(1500,600));
		resaScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(resaScrollPane);
			
			
		
		
		updateRecapCreneaux();
		
	}

	/**
	 * renvoie les creneaux de l'utilisateur
	 */
	private ArrayList<Creneau> chargeCreneauxUtilisateur(long idUser) {
		ArrayList<Creneau> creneauxUtilisateurs = new ArrayList<Creneau>();
		ArrayList<Creneau> creneaux = recupererTousCreneaux();
		
		for (Creneau creneau : creneaux) {
			if (creneau.getUtilisateur().getId() == idUser) {
				creneauxUtilisateurs.add(creneau);
			}
		}
		
		
		return creneauxUtilisateurs;
	}
	
	
	
	/**
	 * renvoie une liste de tous les creneaux
	 * @return
	 */
	private ArrayList<Creneau> recupererTousCreneaux() {
		ArrayList<Creneau> creneaux = new ArrayList<Creneau>();
		JSONArray res = DataApi.getinstance().getAllCreneau();
		System.out.println("Appel API réservation");
		
		for (int i = 0; i < res.length(); i++) {
			JSONObject catJson = (JSONObject) res.get(i);
			//System.out.println(catJson);
			Creneau creneau = Creneau.parseJsonToCreneau(catJson);
			creneaux.add(creneau);
		}
		return creneaux;
	}
	
	
	/**
	 * ajoute les recap de creneau dans le panel
	 */
	public void updateRecapCreneaux() {
		parentCreneauxPanel.removeAll();
		revalidate();
		repaint();
		// recuperer les derniers creneaux
		creneaux = recupererTousCreneaux();
		
		for (int i = creneaux.size() - 1; i >=0; i--) {
			LocalDateTime todayDate = LocalDateTime.now();
			//l'on verifie que la date de fin est bien après la date du jour autement cela signifie que la réservation est terminee
			//if (todayDate.isBefore(creneau.getDateFin())) {
				parentCreneauxPanel.add(new ReservationRecapAdminPanel(this, creneaux.get(i)));
				parentCreneauxPanel.add(Box.createRigidArea(new Dimension(0,15)));
				
			//}
			
		}
		revalidate();
		repaint();
	}

}

























































