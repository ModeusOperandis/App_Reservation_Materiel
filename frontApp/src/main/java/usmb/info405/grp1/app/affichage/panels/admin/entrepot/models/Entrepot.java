package usmb.info405.grp1.app.affichage.panels.admin.entrepot.models;

import java.io.Serializable;

import org.json.JSONObject;

import usmb.info405.grp1.app.models.Produit;


/**
 * Classe utilise pour representer un objet d'entrepot
 * @author Nzo
 */
public class Entrepot implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Produit produit;
	
	private boolean estDispo;
	

	public Entrepot(Long id, Produit produit, boolean estDispo) {
		super();
		this.id = id;
		this.produit = produit;
		this.estDispo = estDispo;
	}
	
	
	public static Entrepot parseJsonToEntrepot(JSONObject jsonObject) {
		Long id = jsonObject.getLong("id");
	    Produit produit = Produit.parseJsonToProduit(jsonObject.getJSONObject("produit"));
	    boolean estDispo = jsonObject.getBoolean("estDispo");

	    return new Entrepot(id, produit, estDispo);
	}
	
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the estDispo
	 */
	public boolean getEstDispo() {
		return estDispo;
	}

	/**
	 * @param estDispo the boolean to set
	 */
	public void setEstDispo(boolean estDispo) {
		this.estDispo = estDispo;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
