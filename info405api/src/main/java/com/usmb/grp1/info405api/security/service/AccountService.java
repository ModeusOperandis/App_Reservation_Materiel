/**
 * @author: humban
 */
package com.usmb.grp1.info405api.security.service;

import java.util.List;

import com.usmb.grp1.info405api.model.AppRole;
import com.usmb.grp1.info405api.model.Utilisateur;


/**
 * Cette interface possede les methodes utilisees dans l'application
 * 
 */
public interface AccountService {
	Utilisateur addNewUser(Utilisateur appUser);
	AppRole addNewRole(AppRole appRole);
	void addRoleToUser(String username, String roleName);
	Utilisateur loadUserByUsername(String username);
	List<Utilisateur> listUser();
}
