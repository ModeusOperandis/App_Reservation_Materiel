package usmb.info405.grp1.app.affichage.panels.admin.produits;

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
import usmb.info405.grp1.app.models.Marque;
import usmb.info405.grp1.app.utilitaire.DataApi;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JButton;

/**
 * Classe pour ajout utilisateur
 * @author humban
 */
public class AjoutProduitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNom;
	private JTextField txtDesc;
	private JTextField txtRefConstruct;
	private JTextField txtCaution;

	/**
	 * Create the panel.
	 */

	public AjoutProduitPanel(ProduitPanel produitPanel) {
		setLayout(new BorderLayout(0, 0));
		
		// panel principal
		
		JPanel panel_ajout_produit_main = new JPanel();
		add(panel_ajout_produit_main, BorderLayout.CENTER);
		panel_ajout_produit_main.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAjoutDunProduit = new JLabel("Ajout d'un Produit");
		lblAjoutDunProduit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAjoutDunProduit.setHorizontalAlignment(SwingConstants.CENTER);
		panel_ajout_produit_main.add(lblAjoutDunProduit, BorderLayout.NORTH);
		
		JPanel panelFormAjout = new JPanel();
		panel_ajout_produit_main.add(panelFormAjout, BorderLayout.CENTER);
		panelFormAjout.setLayout(new GridLayout(0, 1, 0, 0));
		
		// form nom
		
		JPanel FormNom = new JPanel();
		panelFormAjout.add(FormNom);
		
		JLabel lblNom = new JLabel("Nom:");
		FormNom.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setPreferredSize(new Dimension(200, 30));
		FormNom.add(txtNom);
		
		// form général pour les marques, images et catégories
		
		JPanel FormSelection = new JPanel();
		panelFormAjout.add(FormSelection);
		
		// form marques
		
		
		ArrayList<Marque> marques = this.getMarques();
		Vector<Marque> listeAffichageMarques = new Vector<Marque>();
		
		for(Marque marque: marques) {
			listeAffichageMarques.addElement(marque);
		}
		
		JList<Marque> listMarques = new JList<Marque>(listeAffichageMarques);
		listMarques.setSelectedIndex(0);
		
		JScrollPane listeAvecAscenseurMarque = new JScrollPane(listMarques);
		FormSelection.add(listeAvecAscenseurMarque);
		
		// form categories
		
		ArrayList<Categorie> categories = this.getCategories();
		Vector<Categorie> listeAffichageCategories = new Vector<Categorie>();
		
		for(Categorie categorie: categories) {
			listeAffichageCategories.addElement(categorie);
		}
		
		JList<Categorie> listCategories = new JList<Categorie>(listeAffichageCategories);
		listCategories.setSelectedIndex(0);
		
		JScrollPane listeAvecAscenseurCategorie = new JScrollPane(listCategories);
		FormSelection.add(listeAvecAscenseurCategorie);
		
		// form description
		
		JPanel FormDesc = new JPanel();
		panelFormAjout.add(FormDesc);
		
		JLabel lblDesc = new JLabel("Description:");
		FormDesc.add(lblDesc);
		
		txtDesc = new JTextField();
		txtDesc.setPreferredSize(new Dimension(200, 30));
		FormDesc.add(txtDesc);
		
		// form ref constructeur
		
		JPanel FormRefConstruct = new JPanel();
		panelFormAjout.add(FormRefConstruct);
		
		JLabel lblRefConstruct = new JLabel("Reference constructeur:");
		FormRefConstruct.add(lblRefConstruct);
		
		txtRefConstruct = new JTextField();
		txtRefConstruct.setPreferredSize(new Dimension(200, 30));
		FormRefConstruct.add(txtRefConstruct);
		
		// form caution
		
		JPanel FormCaution = new JPanel();
		panelFormAjout.add(FormCaution);
		
		JLabel lblCaution = new JLabel("Caution:");
		FormCaution.add(lblCaution);
		
		txtCaution = new JTextField();
		txtCaution.setText("0");
		txtCaution.setPreferredSize(new Dimension(100, 30));
		FormCaution.add(txtCaution);
		

		// bouton pour ajouter
		
		JButton btnAjouter = new JButton("Ajouter");
		panel_ajout_produit_main.add(btnAjouter, BorderLayout.SOUTH);
		btnAjouter.addActionListener(e -> {
			
		    // Obtention des informations saisies par l'utilisateur
			
		    String nom = txtNom.getText();
		    String desc = txtDesc.getText();
		    String refConstruct = txtRefConstruct.getText();
		    Double caution = Double.valueOf(txtCaution.getText());
		    Long marque = listMarques.getSelectedValue().getId();
		    String image = "lien-vers-image"; // valeur par défaut tant que les images ne sont pas gérées
		    Long categorie = listCategories.getSelectedValue().getId();
		 
		    
		    
		    // requete POST vers API
		    
		    System.out.println("Produit ajouté à la base");
			DataApi ad = DataApi.getinstance();
			//ad.connecteUser("admin","1234");
			ad.addProduit(image, refConstruct, categorie, desc, nom, caution, marque);
		    
		    Window window = SwingUtilities.getWindowAncestor(btnAjouter); // Récupérer la fenêtre parente du bouton
		    if (window instanceof JDialog) {
		        JDialog dialog = (JDialog) window;
		        dialog.dispose(); // Fermer le JDialog actuel
		    }
		    
		    produitPanel.refreshPanel();
		});
	}
	
	/**
	 * recupere les marques dans la db et renvoie une liste
	 * @return
	 */
	public ArrayList<Marque> getMarques() {
		DataApi ad = DataApi.getinstance();
		//ad.connecteUser("admin","1234");
		ArrayList<Marque> marques = new ArrayList<Marque>();
		JSONArray resRequete = ad.getAllMarques();
		for(int i = 0; i < resRequete.length(); i++) {
			try {
				JSONObject marque = (JSONObject) resRequete.get(i);
				
				Marque m = new Marque(
				Long.parseLong(marque.get("id").toString()), 
				marque.get("nom").toString());
				
				marques.add(m);	
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return marques;
	}
	
	/**
	 * recupere les catégories dans la db et renvoie une liste
	 * @return
	 */
	public ArrayList<Categorie> getCategories() {
		DataApi ad = DataApi.getinstance();
		//ad.connecteUser("admin","1234");
		ArrayList<Categorie> categories = new ArrayList<Categorie>();
		JSONArray resRequete = ad.getAllCategories();
		for(int i = 0; i < resRequete.length(); i++) {
			try {
				JSONObject categorie = (JSONObject) resRequete.get(i);
				
				Categorie c = new Categorie(
				Long.parseLong(categorie.get("id").toString()), 
				categorie.get("nom").toString());
				
				categories.add(c);	
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categories;
	}
	
}
