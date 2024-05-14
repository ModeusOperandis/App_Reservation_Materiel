/**
 * 
 */
package usmb.info405.grp1.app.affichage.panels.reservations.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.models.Categorie;
import usmb.info405.grp1.app.models.Panier;
import usmb.info405.grp1.app.models.Produit;
import usmb.info405.grp1.app.utilitaire.DataApi;

/**
 * Classe utilisee pour representer le model pour la selection de l'utilisateur pour un produit
 * @author humban
 */
public class ProduitUtilisateurReservation {
	/**
	 * PCS 'transient' -> pas inclut lors serialisation
	 */
	private transient static PropertyChangeSupport pcs;
	
	private int produitNombre;
	
	private int maxProduitEntrepot;
	
	private ArrayList<Entrepot> produitsEntrepot;

	private Produit produit;
	
	private Panier panier;
	
	
	
	public ProduitUtilisateurReservation(Produit produit, Panier panier) {
		this.panier = panier;
		pcs = new PropertyChangeSupport(this);
		this.produitsEntrepot = recupererProduitEntrepotPourProduit(produit);
		setMaxProduitEntrepot(this.produitsEntrepot.size());
		setProduitNombre(0);
	}
	
	private ArrayList<Entrepot> recupererProduitEntrepotPourProduit(Produit produit) {
		
		ArrayList<Entrepot> resProduitEntrepot = new ArrayList<Entrepot>();
		JSONArray res = DataApi.getinstance().getProduitsEntrepotPourProduit(produit.getId());
		
		for (int i = 0; i < res.length(); i++ ){
			JSONObject resJson = (JSONObject) res.get(i);
			Entrepot entrepot = new Entrepot(Long.parseLong(resJson.get("id").toString()),Produit.parseJsonToProduit((JSONObject)resJson.get("produit"))
					, Boolean.parseBoolean(resJson.get("estDispo").toString()));
			
			if (entrepot.getEstDispo()) {
				resProduitEntrepot.add(entrepot);
			}
		}
		
		return resProduitEntrepot;
	}
	
	
	public void ajouterProduitsEntrepotAuPanier() {
		for (int i = 0; i < produitNombre; i++) {
			panier.getCreneau().addEntrepot(produitsEntrepot.get(i));
		}
	}
	
	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	// --------------------------
	
	/**
	 * @return the produitNombre
	 */
	public int getProduitNombre() {
		return produitNombre;
	}

	/**
	 * @param produitNombre the produitNombre to set
	 */
	public void setProduitNombre(int produitNombre) {
		if (produitNombre >= 0 && produitNombre <= getMaxProduitEntrepot()) {
			pcs.firePropertyChange("setProduitNombre", this.produitNombre, produitNombre);
			this.produitNombre = produitNombre;
		}

	}

	/**
	 * @return the maxProduitEntrepot
	 */
	public int getMaxProduitEntrepot() {
		return maxProduitEntrepot;
	}

	/**
	 * @param maxProduitEntrepot the maxProduitEntrepot to set
	 */
	public void setMaxProduitEntrepot(int maxProduitEntrepot) {
		pcs.firePropertyChange("setMaxProduitEntrepot", this.maxProduitEntrepot, maxProduitEntrepot);
		this.maxProduitEntrepot = maxProduitEntrepot;
	}

	/**
	 * @return the produitsEntrepot
	 */
	public ArrayList<Entrepot> getProduitsEntrepot() {
		return produitsEntrepot;
	}

	/**
	 * @param produitsEntrepot the produitsEntrepot to set
	 */
	public void setProduitsEntrepot(ArrayList<Entrepot> produitsEntrepot) {
		this.produitsEntrepot = produitsEntrepot;
	}

	/**
	 * @return the produit
	 */
	public Produit getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	/**
	 * @return the panier
	 */
	public Panier getPanier() {
		return panier;
	}

	/**
	 * @param panier the panier to set
	 */
	public void setPanier(Panier panier) {
		this.panier = panier;
	}

}
