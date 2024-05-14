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

import com.usmb.grp1.info405api.model.Categorie;
import com.usmb.grp1.info405api.service.CategorieService;

import jakarta.validation.Valid;

/**
 * classe pour le controller pour les catégories
 * @author humban
 */
@RestController // le serveur va rediriger les requetes ici + fait le @ResponseBody (renvoie dans la requete des donnees)
public class CategorieController {
	
	@Autowired
	private CategorieService cService;
	
	@GetMapping("/categories")
	public List<Categorie> getCategories() {
		return cService.getCategories();
	}
	
	@PostMapping("/categories")
	public Categorie saveCategorie(@Valid @RequestBody Categorie categorie) {
		return cService.saveCategorie(categorie);
	}
	
	@GetMapping("/categories/{id}")
	public Categorie getCategorie(@PathVariable("id") Long id) {
		return cService.getSingleCategorie(id);
	}
	
	@DeleteMapping("/categories")
	public void deleteCategorie(@RequestParam("id") Long id) {
		cService.deleteCategorie(id);
	}
	
	@PutMapping("/categories/{id}")
	public Categorie updateCategorie(@PathVariable("id") Long id, @RequestBody Categorie categorie) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		categorie.setId(id);
		return cService.updateCategorie(categorie);
	}
}
