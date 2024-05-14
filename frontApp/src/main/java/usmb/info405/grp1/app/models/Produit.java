package usmb.info405.grp1.app.models;

import java.io.Serializable;
import java.math.BigDecimal;

import org.json.JSONObject;


/**
 * Classe utilise pour representer un produit
 * @author humban
 */
public class Produit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nom;
	
	private String description;
	
	private String refConsructeur;
	
	private BigDecimal caution;
	
	private Marque marque;
	
	private Categorie categorie;
	
	private Image image;
	
	/**
	 * @param id
	 * @param nom
	 * @param description
	 * @param refConsructeur
	 * @param caution
	 * @param marque
	 * @param categorie
	 * @param image
	 */
	public Produit(Long id, String nom, String description, String refConsructeur, BigDecimal caution, Marque marque, Categorie categorie, Image image) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.refConsructeur = refConsructeur;
		this.caution = caution;
		this.marque = marque;
		this.categorie = categorie;
		this.image = image;
	}

	public Produit() {
		// TODO Auto-generated constructor stub
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the refConsructeur
	 */
	public String getRefConsructeur() {
		return refConsructeur;
	}

	/**
	 * @param refConsructeur the refConsructeur to set
	 */
	public void setRefConsructeur(String refConsructeur) {
		this.refConsructeur = refConsructeur;
	}
	
	/**
	 * @return the caution
	 */
	public BigDecimal getCaution() {
		return caution;
	}

	/**
	 * @param caution the caution to set
	 */
	public void setCaution(BigDecimal caution) {
		this.caution = caution;
	}

	/**
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return nom;
	}

	
	public static Produit parseJsonToProduit(JSONObject jsonObject) {
        Produit produit = new Produit();

        produit.setId(jsonObject.getLong("id"));
        produit.setNom(jsonObject.getString("nom"));
        produit.setDescription(jsonObject.getString("description"));
        produit.setRefConsructeur(jsonObject.getString("refConstructeur"));
        produit.setCaution(new BigDecimal(jsonObject.getFloat("caution")));

        JSONObject marqueObject = jsonObject.getJSONObject("marque");
        Marque marque = new Marque();
        marque.setNom(marqueObject.getString("nom"));
        marque.setId(marqueObject.getLong("id"));
        produit.setMarque(marque);

        JSONObject categorieObject = jsonObject.getJSONObject("categorie");
        Categorie categorie = new Categorie();
        categorie.setNom(categorieObject.getString("nom"));
        categorie.setId(categorieObject.getLong("id"));
        produit.setCategorie(categorie);

        JSONObject imageObject = jsonObject.getJSONObject("image");
        Image image = new Image();
        image.setNom(imageObject.getString("nom"));
        image.setId(imageObject.getLong("id"));
        produit.setImage(image);

        return produit;
    }
}
