/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;


import com.usmb.grp1.info405api.model.Produit;

/**
 *  But: interface pour notre service
 * @author humban
 */

public interface ProduitService {
	List<Produit> getProduits(); // methode pour recuperer tout les produits
	
	Produit saveProduit(Produit produit); // methode pour sauvegarder un produit dans la base
	
	Produit getSingleProduit(Long id); // methode pour recuperer les informations d'un produit depuis son id
	
	void deleteProduit(Long id); // methode pour supprimer un produit de la base depuis son id
	
	Produit updateProduit(Produit produit); // methode pour update les info d'un produits dans la base
}
