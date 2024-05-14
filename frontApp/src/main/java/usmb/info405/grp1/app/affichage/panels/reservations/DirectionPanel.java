package usmb.info405.grp1.app.affichage.panels.reservations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.affichage.optionPanes.PopUpErrorJOptionPane;
import usmb.info405.grp1.app.models.Panier;

/**
 * @author humban
 * 
 * Classe utilise pour avoir un representation de deux boutons avec 2 fleches "<-" "->" pour changer le panel a afficher
 * 
 * Il existe 3 combinaisons:
 * 1.				->
 * 2.		<- 		->
 * 3.		<-		valider			
 */
public class DirectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton flecheGauche;
	private JButton flecheDroite;
	private JButton valider;
	private Panier panier;
	
	private ReservationPanel reservationPanel;
	/**
	 * Create the panel.
	 */
	public DirectionPanel(ReservationPanel reservationPanel, Panier panier ) {
		this.panier = panier;
		this.reservationPanel = reservationPanel;
		setLayout(new MigLayout("", "[49px][49px][][][][][][][][][][][]", "[25px][]"));
		
		flecheGauche= new JButton("Précédent");
		add(flecheGauche, "cell 0 1,alignx left,aligny top");
		flecheGauche.addActionListener(new flecheGaucheListener());
		
		flecheDroite = new JButton("Suivant");
		add(flecheDroite, "cell 24 1,right center,aligny top");
		flecheDroite.addActionListener(new flecheDroiteListener());
		
		valider = new JButton("Valider");
		add(valider, "cell 24 1,alignx right,aligny top");
		valider.addActionListener(new validerListener());
		
		HideAllButtons();
		afficherCombinaison(1);

	}
	
	final class flecheGaucheListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			reservationPanel.affichePrecPanel();
		}
	}
	
	final class flecheDroiteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			reservationPanel.afficheSuivPanel();
		}
	}
	
	// TODO: A modifier pour envoyer les donnees ???
	final class validerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//reservationPanel.afficheSuivPanel();
			System.out.println("Le creneau va etre envoye");
			try {
				panier.envoyerCreneau();
				JOptionPane.showMessageDialog(getComponentPopupMenu(),"Réservation envoyée! ");
			} catch (Exception e1) {
				new PopUpErrorJOptionPane(e1.getMessage(), 1);
			}
			
		}
	}
	
	
	
	
	/**
	 * Il existe 3 combinaisons:
	 * 1.				->
	 * 2.		<- 		->
	 * 3.		<-		valider	
	 * @param numComb
	 */
	public void afficherCombinaison(int numComb) {
		HideAllButtons();
		
		switch (numComb) {
		case 1: {
			flecheDroite.setVisible(true);
			break;
		}
		case 2: {
			flecheDroite.setVisible(true);
			flecheGauche.setVisible(true);
			break;
		}
		case 3: {
			flecheGauche.setVisible(true);
			valider.setVisible(true);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + numComb);
			
		}
		
		revalidate();
		repaint();
		
		
	}
	
	private void HideAllButtons() {
		flecheGauche.setVisible(false);
		flecheDroite.setVisible(false);
		valider.setVisible(false);
		revalidate();
		repaint();
	}
	
	
}











