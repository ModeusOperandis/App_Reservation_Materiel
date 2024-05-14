package usmb.info405.grp1.app.affichage.panels.admin.entrepot;

import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import usmb.info405.grp1.app.models.Categorie;
import usmb.info405.grp1.app.models.Image;
import usmb.info405.grp1.app.models.Marque;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.DataApi;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JButton;

/**
 * Classe pour ajout utilisateur
 * @author Nzo
 */
public class AjoutEntrepotPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */

	public AjoutEntrepotPanel(EntrepotPanel entrepotPanel) {
		setLayout(new BorderLayout(0, 0));
		
		// panel principal
		
		JPanel panel_ajout_produit_main = new JPanel();
		add(panel_ajout_produit_main, BorderLayout.CENTER);
		panel_ajout_produit_main.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAjoutDunProduit = new JLabel("Produit - Quantité");
		lblAjoutDunProduit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAjoutDunProduit.setHorizontalAlignment(SwingConstants.CENTER);
		panel_ajout_produit_main.add(lblAjoutDunProduit, BorderLayout.NORTH);
		
		JPanel panelFormAjout = new JPanel();
		panel_ajout_produit_main.add(panelFormAjout, BorderLayout.CENTER);
		panelFormAjout.setLayout(new GridLayout(0, 1, 0, 0));
		
		// form produit
		
		
		ArrayList<Produit> produits = this.getProduits();
		Vector<Produit> listeNomProduits = new Vector<Produit>();
		
		for(Produit produit: produits) {
			listeNomProduits.addElement(produit);
		}
		
		JList<Produit> listProduits = new JList<Produit>(listeNomProduits);
		listProduits.setSelectedIndex(0);
		
		JScrollPane listeAvecAscenseurMarque = new JScrollPane(listProduits);
		panel_ajout_produit_main.add(listeAvecAscenseurMarque);
		
		// form pour quantite
		
		JPanel FormQuantite = new JPanel();
		panel_ajout_produit_main.add(FormQuantite, BorderLayout.EAST);
		
		JTextField txtCaution = new JTextField();
		txtCaution.setText("0");
		txtCaution.setPreferredSize(new Dimension(100, 30));
		FormQuantite.add(txtCaution);
		

		// bouton pour ajouter
		
		JButton btnAjouter = new JButton("Ajouter");
		panel_ajout_produit_main.add(btnAjouter, BorderLayout.SOUTH);
		btnAjouter.addActionListener(e -> {
		    
		    System.out.println("Produit ajouté à la base");
			DataApi ad = DataApi.getinstance();
			
			Long idProduit = listProduits.getSelectedValue().getId();
			int quantite = Integer.valueOf(txtCaution.getText());
			
			for(int i = 0; i < quantite; i++) {
				ad.addProduitEntrepot(idProduit);
			}
		    
		    Window window = SwingUtilities.getWindowAncestor(btnAjouter); // Récupérer la fenêtre parente du bouton
		    if (window instanceof JDialog) {
		        JDialog dialog = (JDialog) window;
		        dialog.dispose(); // Fermer le JDialog actuel
		    }
		    entrepotPanel.refreshPanel();
		});

	}
	
	public ArrayList<Produit> getProduits() {
		DataApi ad = DataApi.getinstance();
		ArrayList<Produit> produits = new ArrayList<Produit>();
		JSONArray resRequete = ad.getAllProduits();
		for(int i = 0; i < resRequete.length(); i++) {
			try {
				JSONObject produitJson = (JSONObject) resRequete.get(i);
				
				JSONObject imageJson = (JSONObject) produitJson.get("image");
				Image image = new Image(Long.parseLong(imageJson.get("id").toString()), imageJson.get("nom").toString());
				
				JSONObject marqueJson = (JSONObject) produitJson.get("marque");
				Marque marque = new Marque(Long.parseLong(marqueJson.get("id").toString()), marqueJson.get("nom").toString());
				
				JSONObject categorieJson = (JSONObject) produitJson.get("categorie");
				Categorie categorie = new Categorie(Long.parseLong(categorieJson.get("id").toString()), categorieJson.get("nom").toString());
				
				
				Produit produit = new Produit(
				Long.parseLong(produitJson.get("id").toString()), 
				produitJson.get("nom").toString(), 
				produitJson.get("description").toString(), 
				produitJson.get("refConstructeur").toString(), 
				(BigDecimal) produitJson.get("caution"), 
				marque, categorie, image);
				
				produits.add(produit);	
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return produits;
	}
	
}
