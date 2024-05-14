/**
 * 
 */
package com.usmb.grp1.info405api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.Utilisateur;
import com.usmb.grp1.info405api.service.UtilisateurService;

import jakarta.validation.Valid;

/**
 * 
 * @author humban
 */
@RestController
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService uService;
	
	@GetMapping("/utilisateurs")
	//@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public List<Utilisateur> getUtilisateurs() {
		return uService.getUtilisateurs();
	}
	
	@PostMapping("/utilisateurs")
	public Utilisateur saveUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
		return uService.saveUtilisateur(utilisateur);
	}
	
	@GetMapping("/utilisateurs/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") Long id) {
		System.out.println(uService.getSingleUtilisateur(id));
		return uService.getSingleUtilisateur(id);
	}
	
	@DeleteMapping("/utilisateurs")
	public void deleteUtilisateur(@RequestParam("id") Long id) {
		uService.deleteUtilisateur(id);
	}
	
	@PutMapping("/utilisateurs/{id}")
	public Utilisateur updateUtilisateur(@PathVariable("id") Long id, @RequestBody Utilisateur utilisateur ) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		utilisateur.setId(id);
		return uService.updateUtilisateur(utilisateur);
	}
}
