/**
 * 
 */
package usmb.info405.grp1.app.models;

import java.io.Serializable;



/**
 * classe utilise pour represente une categorie
 * @author humban
 */
 
public class Categorie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id de la categorie
	 */
	private Long id;
	

	/**
	 * nom de la categorie
	 */
	private String nom;
	/**
	 * @param id
	 * @param nom
	 */
	public Categorie(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}


	
	public Categorie() {
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
	
	public String toString() {
		return nom;
	}


}
