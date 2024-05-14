/**
 * 
 */
package com.usmb.grp1.info405api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.Produit;
import com.usmb.grp1.info405api.service.ProduitService;

import jakarta.validation.Valid;

/**
 * Classe utilisee pour le controleur de la table Produit
 * @author humban
 */

@RestController // le serveur va rediriger les requetes ici + fait le @ResponseBody (renvoie dans la requete des donnees)
public class ProduitController {
	
	@Autowired
	private ProduitService pService;
	
	@Value("${app.version}")
	private String appVersion;
	
	// recupere la version de l'application
	@GetMapping("/version")
	public String getAppVersion() {
		return "version:" + appVersion;
	}
	
	// recuperer tout les produits de la base
	//@RequestMapping(value="/produits", method = RequestMethod.GET)
	@GetMapping("/produits")
	public List<Produit> getProduits() {
		return pService.getProduits();
	}
	
	//recuperer les donnees d'un produit depuis son id
	@GetMapping("/produits/{id}")
	public Produit getProduit(@PathVariable("id") Long id) {
		return pService.getSingleProduit(id);
	}
	
	// ajouter un produit dans la base avec ses informations
	@PostMapping("/produits")
	public Produit saveProduit(@Valid @RequestBody Produit produit) {
		return pService.saveProduit(produit);
	}
	
	// modifier les donnees du produit dans la base
	@PutMapping("/produits/{id}")
	public Produit updateProduit(@PathVariable("id") Long id, @RequestBody Produit produit) {
		// on ajoute l'id au produit pour pouvoir realiser l'update et non un ajout
		produit.setId(id);
		return pService.updateProduit(produit);
	}
	
	// supprimer un produit de la base a l'aide de son id
	@DeleteMapping("/produits")
	public void deleteProduit(@RequestParam("id") Long id) {
		pService.deleteProduit(id);
	}
}
