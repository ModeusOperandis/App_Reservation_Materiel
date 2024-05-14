/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import java.util.List;

import com.usmb.grp1.info405api.model.ProduitEntrepot;

/**
 * 
 */
public interface ProduitEntrepotService {
	List<ProduitEntrepot> getProduitEntrepots();
	
	ProduitEntrepot saveProduitEntrepot(ProduitEntrepot produitEntrepot);
	
	ProduitEntrepot getSingleProduitEntrepot(Long id);
	
	void deleteProduitEntrepot(Long id);
	
	ProduitEntrepot updateProduitEntrepot(ProduitEntrepot produitEntrepot);
	
	// FONCTIONS SPECIFIQUES A L'API
	
	// recupere liste de tt les produits de l'entrepot qui sont dispo
	List<ProduitEntrepot> getProduitEntrepotsDispo();
	
	// recuperer la liste des produits dans l'entrepot pour un produit donne
	List<ProduitEntrepot> loadByProduit(Long id);
	
	// recupere liste des produits pour produit donne qui sont disponibles
	List<ProduitEntrepot> loadByProduitDispo(Long id);
	
	// recupere liste des produits par Categorie
	List<ProduitEntrepot> loadByCategorie(Long id);
	
	// recupere liste des produits par Categorie qui sont dispo
	List<ProduitEntrepot> loadByCategorieDispo(Long id);
	
	// recupere liste des produits par Marque
	List<ProduitEntrepot> loadByMarque(Long id);
	
	// recupere liste des produits par Marque qui sont dispo
	List<ProduitEntrepot> loadByMarqueDispo(Long id);
	
	// TODO: ajouter methodes pour voir dispo entre 2 dates (voir avec creneaux)
}










