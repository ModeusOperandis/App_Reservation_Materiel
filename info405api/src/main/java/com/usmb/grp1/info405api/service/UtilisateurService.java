/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;

import com.usmb.grp1.info405api.model.Utilisateur;

/**
 * 
 * @author humban
 */
public interface UtilisateurService {
	List<Utilisateur> getUtilisateurs();
	
	Utilisateur saveUtilisateur(Utilisateur utilisateur);
	
	Utilisateur getSingleUtilisateur(Long id);
	
	void deleteUtilisateur(Long id);

	Utilisateur updateUtilisateur(Utilisateur utilisateur);
	
	void remplacerChampsVide(Utilisateur utilisateur, Utilisateur oldUtilisateur);
}
