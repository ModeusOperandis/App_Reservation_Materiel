/**
 * @author: humban
 */
package com.usmb.grp1.info405api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.Creneau;
import com.usmb.grp1.info405api.model.ProduitEntrepot;
import com.usmb.grp1.info405api.service.CreneauService;

import jakarta.validation.Valid;

/**
 * controller de l'api pour les creneaux
 */
@RestController
@RequestMapping("/creneaux")
public class CreneauController {
	
	@Autowired
	private CreneauService cService;
	
	@GetMapping()
	public List<Creneau> getAllCreneaux() {
		return cService.getCreneaux();
	}
	
	@PostMapping()
	public Creneau saveCreneau(@Valid @RequestBody Creneau creneau) {
		return cService.saveCreneau(creneau);
	}
	
	@GetMapping("/{id}")
	public Creneau getCreneau(@PathVariable("id") Long id) {
		return cService.getSingleCreneau(id);
	}
	
	@DeleteMapping
	public void deleteCreneau(@RequestParam("id") Long id) {
		cService.deleteCreneau(id);
	}
	
	@PutMapping("/{id}")
	public Creneau updateCreneau(@PathVariable("id") Long id, @RequestBody Creneau creneau) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		creneau.setId(id);
		return cService.updateCreneau(creneau);
	}
	
	// METHODES CUSTOM POUR API
	// pour recuperer les donnees en fonction d'un element, l'url se divise: url/creneaux/elementRecherche
	
	// creneaux d'un utilisateur 
	@GetMapping("/utilisateur/{id}")
	public List<Creneau> getCreneauUtilisateur(@PathVariable("id") Long id) {
		return cService.loadByUtilisateur(id);
	}
	
	// produits d'un produit 
	@GetMapping("/produit/{id}")
	public List<Creneau> getCreneauProduit(@PathVariable("id") Long id) {
		return cService.loadByProduit(id);
	}
	
	// produits d'un produit 
	@GetMapping("/entrepot/{id}")
	public List<Creneau> getCreneauProduitEntrepot(@PathVariable("id") Long id) {
		return cService.loadByProduitEntrepot(id);
	}
	
	// liste de produit de l'entrepot qui ne sont pas pris entre deux dates
	@GetMapping("/dates")
	public List<ProduitEntrepot> getAllProduitEntrepotHorsDate(@RequestParam("dateDebut") LocalDateTime dateStart, @RequestParam("dateFin") LocalDateTime dateEnd) {
		return cService.loadProduitEntrepotByDate(dateStart, dateEnd);
	}
	
}




















