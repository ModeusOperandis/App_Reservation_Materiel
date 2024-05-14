
package usmb.info405.grp1.app.affichage.panels.admin.produits;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.AbstractPanel;

public class ProduitDetailPanel extends AbstractPanel {
	
	private static final long serialVersionUID = 1L;
	
	//Attributs
	private ProduitPanel produitPanel;
	private Produit produit;
	
	//Constructeur
	public ProduitDetailPanel(ProduitPanel produitPanel, Produit produit) {
		this.produitPanel = produitPanel;
		this.produit = produit;
		init();
	}
	
	@Override
	public void init() {
		setLayout(new BorderLayout());
		
		JPanel panelProd = new JPanel(new GridLayout(0,5,0,0));
		
		
		JLabel nom = new JLabel(produit.getNom());
		// récupère le nom du produit
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		panelProd.add(nom);
		
		JLabel marque = new JLabel(produit.getMarque().getNom());
		// récupère le nom de la marque
		marque.setHorizontalAlignment(SwingConstants.CENTER);
		panelProd.add(marque);
		
		JLabel categorie = new JLabel(produit.getCategorie().getNom());
		// récupère le nom de la catégorie
		categorie.setHorizontalAlignment(SwingConstants.CENTER);
		panelProd.add(categorie);
		
		JLabel caution = new JLabel(String.valueOf(produit.getCaution()));
		// récupère la caution du produit
		caution.setHorizontalAlignment(SwingConstants.CENTER);
		panelProd.add(caution);
		
		//Ajout du bonton Modifier
		
		JButton btnModifier = new JButton("Modifier");
		panelProd.add(btnModifier);
		
		btnModifier.addActionListener(e -> {
        	
            JDialog dialog = new JDialog();
            ModifierProduitPanel modifierProduitPanel = new ModifierProduitPanel(produitPanel, produit);
            dialog.getContentPane().add(modifierProduitPanel);
            //dialog.pack(); // prend size auto
            dialog.setSize(500, 600);
            dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(modifierProduitPanel));
            dialog.setVisible(true);
        });
		
		add(panelProd, BorderLayout.CENTER);
	}
	
	public void refreshPanel() {
		removeAll();
		init();
		revalidate();
		repaint();
	}
	
	
}
