package usmb.info405.grp1.app.affichage.panels.reservations;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import usmb.info405.grp1.app.affichage.optionPanes.PopUpErrorJOptionPane;
import usmb.info405.grp1.app.exceptions.InvalidDateException;
import usmb.info405.grp1.app.models.Panier;

public class ReservationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// represente le panier, le creneau de l'utilisateur
	Panier panier;
	
	// represente le panel qui contient les inputs des dates
	SelectCreneauxPanel etape1;
	
	SelectProduitsPanel etape2;
	
	ReservationValiderPanel etape3;
	
	// represente le panel qui contient l'affiage du panel de l'etape actuelle
	private JPanel currentPanelEtape;
	
	// panel pour les fleches de direction
	private DirectionPanel directionPanel;
	
	// pour savoir a quelle etape l'utilisateur est actuellment
	private int currEtape;

	/**
	 * Create the panel.
	 */
	public ReservationPanel() {
		setLayout(new BorderLayout());
		panier = new Panier();
		currEtape = 1;
		currentPanelEtape = new JPanel(new CardLayout());
		
		etape1 = new SelectCreneauxPanel(panier);

//		add(currentPanelEtape);
		// --
		etape2 = new SelectProduitsPanel(panier);
		//this.add(etape2);
		etape3 = new ReservationValiderPanel(panier);
		//this.add(etape3);
		// --
		
		currentPanelEtape.add(etape1, "etape1");
		currentPanelEtape.add(etape2, "etape2");
		currentPanelEtape.add(etape3, "etape3");
		
		add(currentPanelEtape, BorderLayout.CENTER);
		
		directionPanel = new DirectionPanel(this, panier);
		directionPanel.setVisible(true);
		add(directionPanel, BorderLayout.SOUTH);
		
		//HideAllPanels();
		//etape1.setVisible(true);
		choosePanel(1);
	}
	
	public void envoyerCreneau() {
		
	}
	
	
	/**
	 * affiche le panel de l'etape precedente
	 */
	public void affichePrecPanel() {
		try {
			updateModelAvecEtape();
			if (currEtape > 1) {
				currEtape--;
				choosePanel(currEtape);
			}
			
		} catch (Exception e) {
			new PopUpErrorJOptionPane(e.getMessage(), 0);
		}
		
	}
	
	/**
	 * affiche le panel de l'etape suivante
	 */
	public void afficheSuivPanel() {
		try {
			updateModelAvecEtape();
			if (currEtape <= 3) {
				currEtape++;
				choosePanel(currEtape);
			}
			
		} catch (Exception e) {
			new PopUpErrorJOptionPane(e.getMessage(), 0);
		}
	}
	
	/**
	 * update le modele en fonction de l'etape
	 * @throws Exception 
	 */
	public void updateModelAvecEtape() throws Exception {
		switch (currEtape) {
		
		case 1: {
			etape1.updatePanierAvecDate();
			//etape2.reInit();
			etape2.afficherCategorie();
			break;
		}
		
		case 2: {
			etape2.addAllProduitEnterpotToPanier();
			System.out.println("devrait enregistrer produit");
			break;
		}
		
		case 3: {
			System.out.println("devrait envoyer la commande");
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + currEtape);
			
		}
	}
	
	/**
	 * gere le choix de la prochaine direction, du prochain panel a afficher en fonction du numero de l'etape
	 * @param num_etape
	 */
	private void choosePanel(int num_etape) {
		HideAllPanels();
		CardLayout cardLayout = (CardLayout) currentPanelEtape.getLayout();
		
		switch (num_etape) {
		
		case 1: {
			etape1.setVisible(true);
			directionPanel.afficherCombinaison(1);
			cardLayout.show(currentPanelEtape, "etape1");
			break;
		}
		
		case 2: {
			etape2.setVisible(true);
			directionPanel.afficherCombinaison(2);
			cardLayout.show(currentPanelEtape, "etape2");
			break;
		}
		
		case 3: {
			etape3.setVisible(true);
			etape3.reinit();
			directionPanel.afficherCombinaison(3);
			cardLayout.show(currentPanelEtape, "etape3");
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + num_etape);
			
		}
		
		revalidate();
		repaint();
	}
	
	
	
	private void HideAllPanels() {
		etape1.setVisible(false);
		etape2.setVisible(false);
		etape3.setVisible(false);
		revalidate();
		repaint();
	}
	
	
	
}




































