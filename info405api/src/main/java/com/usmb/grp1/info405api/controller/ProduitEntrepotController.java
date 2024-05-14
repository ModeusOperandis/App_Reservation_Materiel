/**
 * @author: humban
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.ProduitEntrepot;
import com.usmb.grp1.info405api.service.ProduitEntrepotService;

import jakarta.validation.Valid;

/**
 * controller de l'api pour les produits dans l'entrepot
 */
@RestController
@RequestMapping("/entrepot")
public class ProduitEntrepotController {
	// TODO: ajout protection role sur post et put
	
	@Autowired
	private ProduitEntrepotService peService;
	
	@GetMapping()
	public List<ProduitEntrepot> getAllProduitEntrepots() {
		return peService.getProduitEntrepots();
	}
	
	@PostMapping()
	public ProduitEntrepot saveProduitEntrepot(@Valid @RequestBody ProduitEntrepot produitEntrepot) {
		return peService.saveProduitEntrepot(produitEntrepot);
	}
	
	@GetMapping("/{id}")
	public ProduitEntrepot getProduitEntrepot(@PathVariable("id") Long id) {
		return peService.getSingleProduitEntrepot(id);
	}
	
	@DeleteMapping
	public void deleteProduitEntrepot(@RequestParam("id") Long id) {
		peService.deleteProduitEntrepot(id);
	}
	
	@PutMapping("/{id}")
	public ProduitEntrepot updateProduitEntrepot(@PathVariable("id") Long id, @RequestBody ProduitEntrepot entrepot) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		entrepot.setId(id);
		return peService.updateProduitEntrepot(entrepot);
	}

	
	
	// METHODES CUSTOM POUR API
	// pour recuperer les donnees en fonction d'un element, l'url se divise: url/entrepot/elementRecherche
	
	/// recupere les produits dispo
	@GetMapping("/dispo")
	public List<ProduitEntrepot> getAllProduitEntrepotsDispo() {
		return peService.getProduitEntrepotsDispo();
	}
	
	// produits d'un produit 
	@GetMapping("/produit/{id}")
	public List<ProduitEntrepot> getProduitEntrepotProduit(@PathVariable("id") Long id) {
		return peService.loadByProduit(id);
	}
	
	// produits d'un produit dispo
	@GetMapping("/produit/dispo/{id}")
	public List<ProduitEntrepot> getProduitEntrepotProduitDispo(@PathVariable("id") Long id) {
		return peService.loadByProduit(id);
	}
	
	// produits d'une categorie 
	@GetMapping("/categorie/{id}")
	public List<ProduitEntrepot> getProduitEntrepotCategorie(@PathVariable("id") Long id) {
		return peService.loadByCategorie(id);
	}
	
	// produits d'une categorie dispo
	@GetMapping("/categorie/dispo/{id}")
	public List<ProduitEntrepot> getProduitEntrepotCategorieDispo(@PathVariable("id") Long id) {
		return peService.loadByCategorie(id);
	}
	
	// produits d'une marque 
	@GetMapping("/marque/{id}")
	public List<ProduitEntrepot> getProduitEntrepotMarque(@PathVariable("id") Long id) {
		return peService.loadByMarque(id);
	}
	
	// produits d'une marque dispo
	@GetMapping("/marque/dispo/{id}")
	public List<ProduitEntrepot> getProduitEntrepotMarqueDispo(@PathVariable("id") Long id) {
		return peService.loadByMarque(id);
	}
	
}





















