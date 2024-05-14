/**
 * 
 */
package usmb.info405.grp1.app.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import usmb.info405.grp1.app.affichage.panels.admin.entrepot.models.Entrepot;
import usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models.Utilisateur;

/**
 * @author humban
 */
public class Creneau implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Utilisateur utilisateur;
	private ArrayList<Entrepot> produitsEntrepot = new ArrayList<Entrepot>();
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	
	
	public Creneau() {
		super();
	}


	public Creneau(Long id, Utilisateur utilisateur, ArrayList<Entrepot> produitsEntrepot, LocalDateTime dateDebut,
			LocalDateTime dateFin) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.produitsEntrepot = produitsEntrepot;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public void addEntrepot(Entrepot entrepot) {
		produitsEntrepot.add(entrepot);
	}
	
	public String formatDate(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	
	public static Creneau parseJsonToCreneau(JSONObject jsonObject) {
	    Long id = jsonObject.getLong("id");
	    Utilisateur utilisateur = Utilisateur.parseJsonToUtilisateur(jsonObject.getJSONObject("utilisateur"));
	    LocalDateTime dateDebut = LocalDateTime.parse(jsonObject.getString("dateDebut"));
	    LocalDateTime dateFin = LocalDateTime.parse(jsonObject.getString("dateFin"));

	    JSONArray produitsEntrepotArray = jsonObject.getJSONArray("produitEntrepots");
	    ArrayList<Entrepot> produitsEntrepot = new ArrayList<>();
	    for (int i = 0; i < produitsEntrepotArray.length(); i++) {
	        JSONObject entrepotObject = produitsEntrepotArray.getJSONObject(i);
	        produitsEntrepot.add(Entrepot.parseJsonToEntrepot(entrepotObject));
	    }

	    return new Creneau(id, utilisateur, produitsEntrepot, dateDebut, dateFin);
	}

	
	/**
	 * TODO: TO DELETE
	 * renvoie un hashmap avec un Produit et le nombre de produit
	 * @return
	 */
	public Map<Produit, Integer> getHashMapProduitFromCreneau() {
		Map<Produit, Integer> compteurProduits = new HashMap<>();
		for (Entrepot prodEntrepot : getProduitsEntrepot()) {
			Produit produit = prodEntrepot.getProduit();
			 if (compteurProduits.containsKey(produit)) {
	                compteurProduits.put(produit, compteurProduits.get(produit) + 1);
	            } else {
	                // Sinon, ajoute le produit au dictionnaire avec un compteur initialisé à 1
	                compteurProduits.put(produit, 1);
	            }
		}
		
		return compteurProduits;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public ArrayList<Entrepot> getProduitsEntrepot() {
		return produitsEntrepot;
	}
	public void setProduitsEntrepot(ArrayList<Entrepot> produitsEntrepot) {
		this.produitsEntrepot = produitsEntrepot;
	}
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDateTime getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}
	

}
