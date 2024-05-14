/**
 * 
 */
package usmb.info405.grp1.app.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.utilitaire.DataApi;

/**
 * represente le panier de l'utilistateur en cours
 */
public class Panier {
	/**
	 * represente les donnees de son panier
	 */
	private Creneau creneau;
	
	private Boolean estEnvoye;
	
	public Panier() {
		creneau = new Creneau();
		estEnvoye = false;
	}
	
	
	/**
	 * renvoie une liste de produits unique depuis une liste de produitEntrepot
	 * @return
	 */
	public ArrayList<Produit> getUniqueProduitsListeFromCreneau() {
		ArrayList<Produit> res = new ArrayList<Produit>();
		for (Entrepot prodEntrepot : creneau.getProduitsEntrepot()) {
			Produit prod = prodEntrepot.getProduit();
			if (! produitEstDansListe(prod, res)) {
				res.add(prod);
			}
		}
		
		return res;
	}
	
	/** 
	 * renvoie le nombre de produits entrepot associe au produit donne qui sont dans le creneau 
	 */
	public int getProduitEntrepotNumber(Produit prodARecup) {
		int res = 0;
		for (Entrepot prodEntrepot : creneau.getProduitsEntrepot()) {
			Produit prod = prodEntrepot.getProduit();
			if (prodARecup.getId() == prod.getId()) {
				res++;
			}
		}
		
		
		return res;
	}
	
	
	/**
	 * renvoie si le produit donne est dans la liste
	 */
	public boolean produitEstDansListe(Produit p, ArrayList<Produit> liste) {
		boolean res = false;
		int i = 0;
		int liste_size = liste.size();
		while (!res && i < liste_size) {
			if (p.getId() == liste.get(i).getId()) {
				res = true;
			}
			i++;
		}
		
		return res;
	}
	
	
	public void envoyerCreneau() throws Exception{
		DataApi dInstance = DataApi.getinstance();
		long userId = (Integer) dInstance.getUserInfo().get("id");
		JSONArray produitsEntrepotIds = getProduitsEntrepotId(getCreneau().getProduitsEntrepot());
		Boolean res = dInstance.saveCreneau(userId, getCreneau().getDateDebut(), getCreneau().getDateFin(), produitsEntrepotIds);
		//System.out.println(res);
		if (! res) {
			throw new Exception("ERREUR lors de l'envoi de la reservation");
		}
	}
	
	
	private JSONArray getProduitsEntrepotId(ArrayList<Entrepot> produits) {
		JSONArray jsonArray = new JSONArray();

        // Parcourez la liste des produits et ajoutez chaque ID au tableau JSON
        for (Entrepot produit : produits) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", produit.getId());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
	}
	
	// ---------------
	
	
	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public Boolean getEstEnvoye() {
		return estEnvoye;
	}

	public void setEstEnvoye(Boolean estEnvoye) {
		this.estEnvoye = estEnvoye;
	}
}
