package usmb.info405.grp1.app.affichage.panels.reservations;

import javax.swing.JPanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.models.Produit;

/**
 * represente une ligne du recap pour un produit 
 */
public class ProduitRecapPanel extends JPanel {
	
	private int produitNb;
	private Produit produit;

	/**
	 * @param produitNb
	 * @param produit
	 */
	public ProduitRecapPanel(int produitNb, Produit produit) {
		super();
		this.produitNb = produitNb;
		this.produit = produit;
		setLayout(new MigLayout("", "[64px][][][][]", "[15px]"));
		
		JLabel lblProduit = new JLabel(produit.getNom());
		add(lblProduit, "cell 0 0,alignx left,aligny top");
		
		JLabel lblQuantit = new JLabel("Quantité:");
		add(lblQuantit, "cell 3 0");
		
		JLabel label = new JLabel(String.valueOf(produitNb));
		add(label, "cell 4 0");
	}

	private static final long serialVersionUID = 1L;

	

}
