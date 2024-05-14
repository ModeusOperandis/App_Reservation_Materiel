package usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe utilise pour representer un produit
 * @author nzo
 */
public class Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String username;
	
	private ArrayList<AppRole> appRoles;
	
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param username
	 * @param appRoles
	 */
	public Utilisateur(Long id, String nom, String prenom, String email, String username, ArrayList<AppRole> appRoles) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.username = username;
		this.appRoles = appRoles;
	}
	
	public Utilisateur(Long id) {
		super();
		this.id = id;
	}
	
	
	/**
	 * renvoie string pour afficher les roles de l'utilisateurs
	 * @return
	 */
	public String rolesToString() {
		String res = "";
		for (AppRole appRole : appRoles) {
			res = res + appRole.getRoleName()+" ";
		}
		return res;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the appRoles
	 */
	public ArrayList<AppRole> getAppRoles() {
		return appRoles;
	}

	/**
	 * @param appRoles the appRoles to set
	 */
	public void setAppRoles(ArrayList<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static Utilisateur parseJsonToUtilisateur(JSONObject jsonObject) {
	    Long id = jsonObject.getLong("id");
	    String nom = jsonObject.getString("nom");
	    String prenom = jsonObject.getString("prenom");
	    String email = jsonObject.getString("email");
	    String username = jsonObject.getString("username");

	    JSONArray appRolesArray = jsonObject.getJSONArray("appRoles");
	    ArrayList<AppRole> appRoles = new ArrayList<>();
	    

	    return new Utilisateur(id, nom, prenom, email, username, appRoles);
	}



	
	
	
	

	

}
