/**
 * @author: humban
 */
package com.usmb.grp1.info405api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmb.grp1.info405api.model.ProduitEntrepot;

/**
 * 
 */
@Repository
public interface ProduitEntrepotRepository extends JpaRepository<ProduitEntrepot, Long> {
	// recupere produits dispo
	List<ProduitEntrepot> findByEstDispo(Boolean estDispo);
	// recupere les produits 'Produit' 
	List<ProduitEntrepot> findByProduit_id(Long id);
	// recupere les produits 'Produit' dispo
	List<ProduitEntrepot> findByProduit_idAndEstDispoTrue(Long id);
	// recupere les produits d'une categorie 
	List<ProduitEntrepot> findByProduit_Categorie_id(Long id);
	// recupere les produits d'une categorie dispo
	List<ProduitEntrepot> findByEstDispoTrueAndProduit_Categorie_id(Long id);
	// recupere les produits d'une Marque 
	List<ProduitEntrepot> findByProduit_Marque_id(Long id);
	// recupere les produits d'une Marque dispo
	List<ProduitEntrepot> findByEstDispoTrueAndProduit_Marque_id(Long id);
}
