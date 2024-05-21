package usmb.info405.grp1.app.affichage.panels.admin.entrepot;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;
import usmb.info405.grp1.app.affichage.panels.admin.entrepot.listeners.ModifierDispoListener;
import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.affichage.panels.admin.produits.ModifierProduitPanel;
import usmb.info405.grp1.app.models.Categorie;
import usmb.info405.grp1.app.models.Image;
import usmb.info405.grp1.app.models.Marque;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.DataApi;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class EntrepotPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// liste des utilisateurs
	public static ArrayList<Entrepot> objets = new ArrayList<Entrepot>();
	JButton button = new JButton();
	
	/**
	 * Create the panel.
	 */
	public EntrepotPanel() {
		init();
	}
	
	public void init() {
		setLayout(new MigLayout("fill,insets 20","[center]","[center]"));
		
		objets = getProduits();
		
		//Panel entrepot liste prod + Bouton en dessous
		JPanel entrepot = new JPanel();
		
		entrepot.setLayout(new MigLayout("wrap,fillx,insets 35 45 30 45","fill,600:300"));
		entrepot.putClientProperty(FlatClientProperties.STYLE,"" +
				"arc:20;" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		//entrepot.setLayout(new BoxLayout(entrepot, BoxLayout.Y_AXIS));
		
		// Panel liste produit Scrollable
		JScrollPane scrollPane = this.affichageObjets(objets);
		entrepot.add(scrollPane);	
		
		// Bouton ajouter produit
		JButton addProd = this.ajouterObjetButton();
		addProd.setAlignmentX(Component.CENTER_ALIGNMENT);
		entrepot.add(addProd);
		
		add(entrepot);
		
	}
	
	public JScrollPane affichageObjets(ArrayList<Entrepot> objets) {
		// panel principal stockant toutes les informations
		
		JPanel principal = new JPanel();
		principal.putClientProperty(FlatClientProperties.STYLE,"" +
				"arc:20;" +
				"[light]background:darken(@background,3%);" +
				"[dark]background:lighten(@background,3%)");
		
		principal.setLayout(new GridLayout(0,1));

		
		// boucle sur les produits de la liste
		for (Entrepot objet: objets) {
			
			// panel par prod
			JPanel infoProd = new JPanel();
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			infoProd.setLayout(new GridBagLayout());
			
			gbc.weightx = 0.0001; // % pour colonne gauche (id prod ?)
			gbc.insets = new Insets(1,2,1,2); //padding externe Left/Right
		
			
			// ajoute info produit dans listProd en grid
	   
			JTextField lblId = new JTextField(String.valueOf(objet.getId()));
			lblId.setHorizontalAlignment(SwingConstants.CENTER);
			lblId.setEditable(false);
			infoProd.add(lblId, gbc);
			
			gbc.weightx = 0.995; // % colonne mid (nom prod)
			
			//mettre padding interne au bouton LR only
			gbc.ipadx = 50; //nb pixel
			
			JTextField lblNom = new JTextField(objet.getProduit().getNom());
			lblNom.setHorizontalAlignment(SwingConstants.CENTER);
			lblNom.setEditable(false);
			infoProd.add(lblNom, gbc);

			// bouton dispo
			gbc.weightx = 0.0025; // % colonne droite (boutton)
			gbc.ipadx = 0; //reset du padding
			
			JButton btnDispo = new JButton(dispo(objet.getEstDispo()));
			btnDispo.addActionListener(new ModifierDispoListener(objet, this));
			infoProd.add(btnDispo, gbc);
			
			//Ajout panel Prod à list Prod
			principal.add(infoProd);
		}	
		
		JScrollPane scrollPane = new JScrollPane(principal);
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
	
	
	public JButton ajouterObjetButton() {
	
        JButton btnAjouterObjet = new JButton("Ajouter Produit");
        btnAjouterObjet.addActionListener(e -> {
        	
        	AjoutEntrepotPanel ajoutEntrepotPanel = new AjoutEntrepotPanel(this);
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(ajoutEntrepotPanel);
            //dialog.pack(); // prend size auto
            dialog.setSize(500, 600);
            dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(ajoutEntrepotPanel));
            dialog.setVisible(true);
        });
        return btnAjouterObjet;
	}
	
	public String dispo(boolean estDispo) {
		String res = "";
		
		if (estDispo) {
			res = " Disponible  ";
		} else {
			res = "Indisponible";
		}
		return res;
	}

	
	public ArrayList<Entrepot> getProduits() {
		DataApi ad = DataApi.getinstance();
		//ad.connecteUser("admin","1234");
		ArrayList<Entrepot> produits = new ArrayList<Entrepot>();
		JSONArray resRequete = ad.getProduitsEntrepot();
		
		for(int i = 0; i < resRequete.length(); i++) {
			
			try {
				JSONObject entrepotJson = (JSONObject) resRequete.get(i);
				
				JSONObject produitJson = (JSONObject) entrepotJson.get("produit");
				
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
				
				Entrepot entrepot = new Entrepot(Long.parseLong(entrepotJson.get("id").toString()), produit, Boolean.valueOf(entrepotJson.get("estDispo").toString()));
				
				produits.add(entrepot);	
				
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
