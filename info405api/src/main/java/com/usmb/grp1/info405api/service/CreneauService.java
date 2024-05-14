/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import java.time.LocalDateTime;
import java.util.List;

import com.usmb.grp1.info405api.model.Creneau;
import com.usmb.grp1.info405api.model.ProduitEntrepot;

/**
 * 
 */
public interface CreneauService {
	List<Creneau> getCreneaux();
	
	Creneau saveCreneau(Creneau creneau);
	
	Creneau getSingleCreneau(Long id);
	
	void deleteCreneau(Long id);
	
	Creneau updateCreneau(Creneau creneau);
	
	// FONCTIONS SPECIFIQUES A L'API
	
	// recupere les creneaux de l'utilisateur 
	List<Creneau> loadByUtilisateur(Long id);
	
	// recupere les creneaux d'un produit
	List<Creneau> loadByProduit(Long id);
	
	// recupere les creneaux d'un produit de l'entrepot
	List<Creneau> loadByProduitEntrepot(Long id);
	
	// recupere les produits dans l'entrepot qui ne sont pas dans un creneau 
	List<ProduitEntrepot> loadProduitEntrepotByDate(LocalDateTime dateStart, LocalDateTime dateEnd);
}
