/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Utilisateur;
import com.usmb.grp1.info405api.repository.UtilisateurRepository;

/**
 * 
 * @author humban
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurRepository uRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public List<Utilisateur> getUtilisateurs() {
		return uRepository.findAll();
	}


	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		return uRepository.save(utilisateur);
	}


	@Override
	public Utilisateur getSingleUtilisateur(Long id) {
		Optional<Utilisateur> utilisateur = uRepository.findById(id);
		if (utilisateur.isPresent()) { // si Utilisateur existe
			return utilisateur.get();
		}
		// si Utilisateur existe pas alors on genere une exception
		throw new RuntimeException("Utilisateur is not found for the id:"+id);
	}


	@Override
	public void deleteUtilisateur(Long id) {
		uRepository.deleteById(id);
	}

	/**
	 * Avant d'ajouter (modifier) l'Utilisateur en base, on recupere celui qui est déjà existant
	 * et pour tous les champs qui sont NULL on les remplaces par l'ancien utilisateur 
	 */
	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		//System.out.println(utilisateur);
		Utilisateur oldUtilisateur = getSingleUtilisateur(utilisateur.getId());
		remplacerChampsVide(utilisateur, oldUtilisateur);
		//System.out.println(utilisateur);
		return uRepository.save(utilisateur);
	}


	@Override
	public void remplacerChampsVide(Utilisateur utilisateur, Utilisateur oldUtilisateur) {
		if (utilisateur.getAppRoles().isEmpty()) {
			utilisateur.setAppRoles(oldUtilisateur.getAppRoles());
		}
		if (utilisateur.getEmail() == null) {
			utilisateur.setEmail(oldUtilisateur.getEmail());
		}
		if (utilisateur.getNom() == null) {
			utilisateur.setNom(oldUtilisateur.getNom());
		}
		
		// si le password est null alors on prend le hash qui est deja dans la db
		if (utilisateur.getPassword() == null) {
			utilisateur.setPassword(oldUtilisateur.getPassword());
		} else {
			// j'ajoute à la base le nouveau password en l'encodant
			utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		}
		
		
		if (utilisateur.getCreatedAt() == null) {
			utilisateur.setCreatedAt(oldUtilisateur.getCreatedAt());
		}
		if (utilisateur.getUpdatedAt() == null) {
			utilisateur.setUpdatedAt(oldUtilisateur.getUpdatedAt());
		}
	}

}





