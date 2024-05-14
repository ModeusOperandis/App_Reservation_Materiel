/**
 * 
 */
package usmb.info405.grp1.app.affichage.panels.admin.utilisateurs.models;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * @author humban
 * classe utilise pour avoir les roles de l'utilisateur dans l'application
 */
public class AppRole<JSONObject> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 * @param roleName
	 */
	public AppRole(Long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private Long id;
	private String roleName;
}
