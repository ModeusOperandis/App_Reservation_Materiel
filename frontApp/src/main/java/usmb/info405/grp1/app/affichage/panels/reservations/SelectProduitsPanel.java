package usmb.info405.grp1.app.affichage.panels.reservations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONArray;
import org.json.JSONObject;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.affichage.panels.reservations.models.ProduitUtilisateurReservation;
import usmb.info405.grp1.app.models.Categorie;
import usmb.info405.grp1.app.models.Panier;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.DataApi;

/**
 * @author humban Represente le fentre de l'etape 2 dans la reservation
 */
public class SelectProduitsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Panier panier;
	private ArrayList<ProduitUtilisateurReservation> produitReservationModel;
	
	// utilisé pour ajouter les elements dedans et avoir une scroll bar
	private JPanel mainPanel;

	/**
	 * Create the panel.
	 */
	public SelectProduitsPanel(Panier panier) {
		setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		mainPanel = new JPanel();
		// super(new GridLayout());
		mainPanel.setLayout((LayoutManager) new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// ajout de la scroll bars
		JScrollPane resaScrollPane = new JScrollPane(mainPanel);
		resaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resaScrollPane.setMaximumSize(new Dimension(1500,500));
		resaScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(resaScrollPane);
		// --
		produitReservationModel = new ArrayList<ProduitUtilisateurReservation>();
		this.panier = panier;
		reInit();
	}

	/**
	 * retire tous les elements et ajoute avec de nouvelles donnees
	 */
	public void reInit() {
		mainPanel.removeAll();
		//afficherCategorie();
		revalidate();
		repaint();
	}

	public void afficherCategorie() {
		mainPanel.removeAll();
		JPanel panelParent = new JPanel();
		panelParent.setLayout(new BoxLayout(panelParent, BoxLayout.Y_AXIS));
		ArrayList<Categorie> categories = recupererCategorie();
		for (Categorie categorie : categories) {
			JLabel label = new JLabel(categorie.getNom());
			Font font = label.getFont();
			label.setFont(new Font(font.getFontName(), Font.BOLD | Font.ITALIC, font.getSize()));
			panelParent.add(label);
			ArrayList<Entrepot> prodEntrepotDispo = getProduitsEntrepotDispoEntreDate(panier.getCreneau().getDateDebut(), panier.getCreneau().getDateFin());
			afficherProduitEntrepotParCategorie(categorie.getId(), panelParent, prodEntrepotDispo);
		}
		mainPanel.add(panelParent);
	}

	private void afficherProduitEntrepotParCategorie(Long idCat, JPanel panelParent, ArrayList<Entrepot> produitsEntrepotDispo) {

		ArrayList<Entrepot> produitsEntrepotCat = getproduitEntrepotUniquesParCategorie(produitsEntrepotDispo,idCat);
		// grille representant les produits
		// | nom | ReservationProduitPanel |
		JPanel produitsGrid = new JPanel(new GridLayout(produitsEntrepotCat.size(), 2));
		for (Entrepot produit : produitsEntrepotCat) {
			// si produit pas encore affiche
			// if () {
			ProduitUtilisateurReservation produitUtilisateurModel = new ProduitUtilisateurReservation(
					produit.getProduit(), panier);
			produitReservationModel.add(produitUtilisateurModel);
			ReservationProduitPanel reservationProduitPanel = new ReservationProduitPanel(produitUtilisateurModel);
			reservationProduitPanel.setBackground(Color.blue);
			produitsGrid.add(new JLabel(produit.getProduit().getNom()));
			produitsGrid.add(reservationProduitPanel);
			// }
		}
		
		if (produitsEntrepotCat.size() == 0) {
			panelParent.add(new JLabel("Pas de produit"));
		}

		panelParent.add(produitsGrid);
	}

	/**
	 * ajoute pour tous les models en attributs les produits au panier
	 */
	public void addAllProduitEnterpotToPanier() {
		panier.getCreneau().setProduitsEntrepot(new ArrayList<Entrepot>());
		for (ProduitUtilisateurReservation model : produitReservationModel) {
			model.ajouterProduitsEntrepotAuPanier();
		}
	}

	/**
	 * fais une requete a la base pour recuperer l'ensemble des categories
	 */
	private ArrayList<Categorie> recupererCategorie() {
		DataApi ad = DataApi.getinstance();
		ArrayList<Categorie> categories = new ArrayList<Categorie>();
		JSONArray res = ad.getAllCategories();
		for (int i = 0; i < res.length(); i++) {
			JSONObject catJson = (JSONObject) res.get(i);
			Categorie categorie = new Categorie(Long.parseLong(catJson.get("id").toString()),
					catJson.get("nom").toString());
			categories.add(categorie);
		}
		return categories;
	}
	
	// -------
	/**
	 * renvoie la liste des produits de l'entrepots qui sont dispo dans l'entrepot et dispo entre les deux dates
	 * @param dateDebut
	 * @param dateFin
	 * @return ArrayList<Entrepot> produits Entrepot dispo
	 */
	private ArrayList<Entrepot> getProduitsEntrepotDispoEntreDate(LocalDateTime dateDebut, LocalDateTime dateFin) {
		JSONArray res = DataApi.getinstance().getAllProduitEntrepotEntreDates(dateDebut, dateFin);
		ArrayList<Entrepot> entrepot = new ArrayList<Entrepot>();
		
		for (int i = 0; i < res.length(); i++) {
			JSONObject catJson = (JSONObject) res.get(i);
			System.out.println(catJson.get("produit"));
			Entrepot prod = new Entrepot(Long.parseLong(catJson.get("id").toString()),
					Produit.parseJsonToProduit((JSONObject) catJson.get("produit")),
					Boolean.parseBoolean(catJson.get("estDispo").toString()));
			/*
			if (prod.getEstDispo() && !panier.produitEstDansListe(prod.getProduit(), produitsDejaAffiches)) {
				entrepot.add(prod);
				produitsDejaAffiches.add(prod.getProduit());
			}
			*/
			entrepot.add(prod);
		}
		return entrepot;
	}
	
	/**
	 * renvoie les produits entrepot de maniere unique qui font parti d'une categorie depuis une liste de produits entrepot
	 * Utilise pour recuperer 
	 * @param produits
	 * @param idCat
	 * @return
	 */
	private ArrayList<Entrepot>	getproduitEntrepotUniquesParCategorie(ArrayList<Entrepot> produits, Long idCat) {
		ArrayList<Entrepot> res = new ArrayList<Entrepot>();
		ArrayList<Produit> produitsDejaAjoute = new ArrayList<Produit>();
		for (Entrepot prod : produits) {
			if (prod.getProduit().getCategorie().getId() == idCat && !panier.produitEstDansListe(prod.getProduit(), produitsDejaAjoute)) {
				res.add(prod);
				produitsDejaAjoute.add(prod.getProduit());
			}
		}
		return res;
	}
	
	
	/**
	 * renvoie le nombre de produits entrepot associe a l'id dans la liste de produits
	 * @param produits
	 * @param idProdEntrepot
	 * @return
	 */
	private int getNbProduitEntrepot(ArrayList<Entrepot> produits, Long idProdEntrepot) {
		int res = 0 ;
		
		for (Entrepot prodEntr : produits) {
			if (prodEntr.getId() == idProdEntrepot) {
				res ++;
			}
		}
		return res;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// --------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * fais une requete a la base pour recuperer l'ensemble des produits de
	 * l'entepot en fct de d'une categorie
	 */
	/*private ArrayList<Entrepot> recupererProduitEntrepotParCategorie(Long idCat) {
		// pour recuperer les produit de maniere unique
		ArrayList<Produit> produitsDejaAffiches = new ArrayList<Produit>();
		DataApi ad = DataApi.getinstance();
		ArrayList<Entrepot> entrepot = new ArrayList<Entrepot>();		
		JSONArray res = ad.getProduitsEntrepotPourCategorie(idCat);
		for (int i = 0; i < res.length(); i++) {
			JSONObject catJson = (JSONObject) res.get(i);
			//System.out.println(catJson.get("produit"));
			Entrepot prod = new Entrepot(Long.parseLong(catJson.get("id").toString()),
					Produit.parseJsonToProduit((JSONObject) catJson.get("produit")),
					Boolean.parseBoolean(catJson.get("estDispo").toString()));
			if (prod.getEstDispo() && !panier.produitEstDansListe(prod.getProduit(), produitsDejaAffiches)) {
				entrepot.add(prod);
				produitsDejaAffiches.add(prod.getProduit());
			}

		}
		return entrepot;
	}
	*/
}
