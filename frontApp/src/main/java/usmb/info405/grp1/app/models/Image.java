package usmb.info405.grp1.app.models;

import java.io.Serializable;


/**
 * classe utilise pour representer une marque
 * @author humban
 */

public class Image implements Serializable{
	/**
	 * @param id
	 * @param nom
	 */
	public Image(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	public Image() {
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

	private static final long serialVersionUID = 1L;

	/**
	 * id de la marque
	 */
	private Long id;
	
	/**
	 * nom de la marque
	 */
	private String nom;

}
