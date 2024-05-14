/**
 * 
 */
package usmb.info405.grp1.app.affichage.panels.reservations;

import java.awt.Label;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usmb.info405.grp1.app.models.Creneau;
import usmb.info405.grp1.app.models.Panier;
import usmb.info405.grp1.app.models.Produit;

/**
 * 
 */
public class ReservationValiderPanel extends JPanel {

	private Panier panier;

	/**
	 * @param panier
	 */
	public ReservationValiderPanel(Panier panier) {
		super();
		this.panier = panier;
		setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		// init();
	}

	public void reinit() {
		removeAll();
		JLabel titleLabel = new JLabel("Recap de votre réservation");
		titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
		add(titleLabel);
		afficherRecapDate();
		JPanel parentProduitPanel = new JPanel();
		parentProduitPanel.setLayout((LayoutManager) new BoxLayout(parentProduitPanel, BoxLayout.Y_AXIS));
		afficherRecapProduits(parentProduitPanel);
		parentProduitPanel.add(Box.createVerticalGlue());
		add(parentProduitPanel);
	}

	private void afficherRecapDate() {
		JPanel panelDate = new JPanel();
		panelDate.add(new JLabel("De:     "));
		Creneau creneau = panier.getCreneau();
		panelDate.add(new JLabel(creneau.formatDate(creneau.getDateDebut())));
		panelDate.add(new JLabel("A:     "));
		panelDate.add(new JLabel(creneau.formatDate(creneau.getDateFin())));
		add(panelDate);
	}

	private void afficherRecapProduits(JPanel parentPanel) {

		ArrayList<Produit> produits = panier.getUniqueProduitsListeFromCreneau();
		for (Produit prod : produits) {
			Integer quantite = panier.getProduitEntrepotNumber(prod);
			parentPanel.add(new ProduitRecapPanel(quantite, prod));
		}

	}

}
