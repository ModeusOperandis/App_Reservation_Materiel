package usmb.info405.grp1.app.affichage.panels.admin.produits;

import java.awt.BorderLayout;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import usmb.info405.grp1.app.models.Categorie;
import usmb.info405.grp1.app.models.Marque;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.DataApi;


public class ModifierProduitPanel extends JPanel {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    public ModifierProduitPanel(ProduitPanel produitPanel, Produit produit) {

    	super();
	
		setLayout(new BorderLayout());
		
		// panel principal
		
		JPanel panel_ajout_produit_main = new JPanel();
		add(panel_ajout_produit_main, BorderLayout.CENTER);
		panel_ajout_produit_main.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAjoutDunProduit = new JLabel("Modification");
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
		
		JTextField txtNom = new JTextField(produit.getNom());
		FormNom.add(txtNom);
		txtNom.setEditable(true);
		txtNom.setPreferredSize(new Dimension(100, 50));
		
		// form général pour les marques, images et catégories
		
		JPanel FormSelection = new JPanel();
		panelFormAjout.add(FormSelection);
		
		// form marques
		
		
		ArrayList<Marque> marques = this.getMarques();
		Vector<Marque> listeNomMarques = new Vector<Marque>();
		int selecteurMarque = 0;
		
		for(int i = 0; i < marques.size(); i++) {
			listeNomMarques.addElement(marques.get(i));
			
			if(marques.get(i).getNom().contains(produit.getMarque().getNom())) {
				selecteurMarque = i;
			}
		}
		
		JList<Marque> listMarques = new JList<Marque>(listeNomMarques);
		listMarques.setSelectedIndex(selecteurMarque);
		
		JScrollPane listeAvecAscenseurMarque = new JScrollPane(listMarques);
		FormSelection.add(listeAvecAscenseurMarque);
		
		// form categories
		
		
		ArrayList<Categorie> categories = this.getCategories();
		Vector<Categorie> listeNomCategories = new Vector<Categorie>();
		int selecteurCategorie = 0;
		
		for(int i = 0; i < categories.size(); i++) {
			listeNomCategories.addElement(categories.get(i));
			
			if(categories.get(i).getNom().contains(produit.getCategorie().getNom())) {
				selecteurCategorie = i;
			}
		}
		
		JList<Categorie> listCategories = new JList<Categorie>(listeNomCategories);
		listCategories.setSelectedIndex(selecteurCategorie);
		
		JScrollPane listeAvecAscenseurCategorie = new JScrollPane(listCategories);
		FormSelection.add(listeAvecAscenseurCategorie);
		

		
		// form description
		
		JPanel FormDesc = new JPanel();
		panelFormAjout.add(FormDesc);
		
		JLabel lblDesc = new JLabel("Description:");
		FormDesc.add(lblDesc);
		
		JTextField txtDesc = new JTextField();
		txtDesc.setText(produit.getDescription());
		txtDesc.setPreferredSize(new Dimension(200, 50));
		FormDesc.add(txtDesc);
		
		// form ref constructeur
		
		JPanel FormRefConstruct = new JPanel();
		panelFormAjout.add(FormRefConstruct);
		
		JLabel lblRefConstruct = new JLabel("Reference constructeur:");
		FormRefConstruct.add(lblRefConstruct);

		JTextField txtRefConstruct = new JTextField();
		txtRefConstruct.setText(produit.getRefConsructeur());
		txtRefConstruct.setPreferredSize(new Dimension(100, 50));
		FormRefConstruct.add(txtRefConstruct);
		
		// form caution
		
		JPanel FormCaution = new JPanel();
		panelFormAjout.add(FormCaution);
		
		JLabel lblCaution = new JLabel("Caution:");
		FormCaution.add(lblCaution);
		
		JTextField txtCaution = new JTextField(String.valueOf(produit.getCaution()));
		
		txtCaution.setEditable(true);
		txtCaution.setPreferredSize(new Dimension(100, 50));
		
		FormCaution.add(txtCaution);
		

		// bouton pour valider
		
		JButton btnModifier = new JButton("Valider");
		panel_ajout_produit_main.add(btnModifier, BorderLayout.SOUTH);
		
		btnModifier.addActionListener(e -> {
		    
			DataApi ad = DataApi.getinstance();
			ad.updateProduit(produit.getId(), produit.getImage().getNom(), txtRefConstruct.getText(), listCategories.getSelectedValue().getId(),
					txtDesc.getText(), txtNom.getText(), Double.valueOf(txtCaution.getText()), listMarques.getSelectedValue().getId());
			
		    System.out.println("Produit modifié");
		    
		    Window window = SwingUtilities.getWindowAncestor(btnModifier); // Récupérer la fenêtre parente du bouton
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
		DataApi ad =DataApi.getinstance();
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