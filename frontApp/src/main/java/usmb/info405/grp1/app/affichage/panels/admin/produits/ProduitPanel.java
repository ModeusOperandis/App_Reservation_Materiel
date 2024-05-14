package usmb.info405.grp1.app.affichage.panels.admin.produits;

import javax.swing.JPanel;



import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.models.Categorie;
import usmb.info405.grp1.app.models.Image;
import usmb.info405.grp1.app.models.Marque;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.DataApi;
import usmb.info405.grp1.app.utilitaire.ScrollablePanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ProduitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	//private ScrollablePanel listePanel;
	
	// liste des utilisateurs
	public ArrayList<Produit> produits = new ArrayList<Produit>();
	
	/**
	 * Create the panel.
	 */
	public ProduitPanel() {
		init();
		revalidate();
		repaint();

	}
	
	public void init() {
		produits = getProduits();
		setLayout(new MigLayout("wrap,insets 20","[center]","[center]"));
		
		//Ajout du titre
		JLabel titleLabel = new JLabel("Références des produits");
		titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		add(titleLabel);
		
		//Gap
		add(Box.createRigidArea(new Dimension(0,5)));
		
		//Ajout de la Description
		JLabel NoteLabel = new JLabel("Liste des références ajoutables dans l'entrepôt");
		NoteLabel.setFont(NoteLabel.getFont().deriveFont(11.0f));
		NoteLabel.putClientProperty(FlatClientProperties.STYLE,"" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		add(NoteLabel);
		NoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		add(Box.createRigidArea(new Dimension(0,50)));
		
		//Creation du Scrollable Panel
		JScrollPane resaScrollPane = createListPanel();
		add(resaScrollPane);
		
		add(Box.createRigidArea(new Dimension(0,10)));
		
		//Creation du panel stockant les boutons d'ajout
		JPanel ajouts = new JPanel();
		add(ajouts);
		
		ajouts.add(ajouterProduitButton());
		ajouts.add(ajouterMarqueButton());
		ajouts.add(ajouterCategorieButton());
		
	}
	
	//Composant de Panel de la liste
	private JScrollPane createListPanel() {
		JPanel afficheProd = new JPanel();
		afficheProd.setLayout(new GridLayout(0,1));
		afficheProd.add(infoColonne());
		
		for (Produit produit: produits) {
			afficheProd.add(new ProduitDetailPanel(this, produit));
		}
		
		JScrollPane scrollPane = new JScrollPane(afficheProd);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//retrait background + Bordure
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		
		
		scrollPane.setMaximumSize(new Dimension(900, 500));
		
		//passe en flatlaf style
		scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,"" 
			+ "trackArc : 999;"
			+ "width: 5;"
			+ "thumbInsets: 0,0,0,0");
		
		
		return scrollPane;
	}
	
	private JPanel infoColonne() {
		JPanel colonnes = new JPanel(new GridLayout(0,5));
		
		JLabel nom = new JLabel("Nom");
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		colonnes.add(nom);
		
		JLabel marque = new JLabel("Marque");
		marque.setHorizontalAlignment(SwingConstants.CENTER);
		colonnes.add(marque);
		
		JLabel categorie = new JLabel("Catégorie");
		categorie.setHorizontalAlignment(SwingConstants.CENTER);
		colonnes.add(categorie);
		
		JLabel caution = new JLabel("Caution");
		caution.setHorizontalAlignment(SwingConstants.CENTER);
		colonnes.add(caution);
		
		JLabel modifier = new JLabel("");
		modifier.setHorizontalAlignment(SwingConstants.CENTER);
		colonnes.add(modifier);

		return colonnes;
	}

	public JButton ajouterProduitButton() {
		AjoutProduitPanel ajoutProduitPanel = new AjoutProduitPanel(this);
        JButton btnAjouterProduit = new JButton("Ajouter Produit");
        btnAjouterProduit.addActionListener(e -> {
        	
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(ajoutProduitPanel);
            //dialog.pack(); // prend size auto
            dialog.setSize(500, 600);
            dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(ajoutProduitPanel));
            dialog.setVisible(true);
        });
        return btnAjouterProduit;
	}
	
	public JButton ajouterMarqueButton() {
        JButton btnAjouterMarque = new JButton("Ajouter Marque");
        btnAjouterMarque.addActionListener(e -> {

        	String marque = JOptionPane.showInputDialog("Renseignez le nom de la marque");
        	if(!(marque == null)) {
        		JOptionPane.showMessageDialog(this, "Marque " + marque + " ajoutée.");
    		    
    		    // requete POST vers API
        		DataApi ad = DataApi.getinstance();
        		//ad.connecteUser("admin","1234");
        		ad.addMarque(marque);
        		
        	}
        	
        	
        });
        return btnAjouterMarque;
	}
	
	public JButton ajouterCategorieButton() {
        JButton btnAjouterCategorie = new JButton("Ajouter Catégorie");
        btnAjouterCategorie.addActionListener(e -> {
        	
        	String categorie = JOptionPane.showInputDialog("Renseignez le nom de la catégorie");
        	if (!(categorie == null)) {
        		JOptionPane.showMessageDialog(this, "Catégorie " + categorie + " ajoutée.");
    		    
    		    // requete POST vers API
        		DataApi ad = DataApi.getinstance();
        		//ad.connecteUser("admin","1234");
        		ad.addCategorie(categorie);
			} 
        	
		    
        });
        return btnAjouterCategorie;
	}
	
	public ArrayList<Produit> getProduits() {
		DataApi ad = DataApi.getinstance();
		//ad.connecteUser("admin","1234");
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
	
	public void refreshPanel() {
		removeAll();
		init();
		revalidate();
		repaint();
	}

}