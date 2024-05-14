/**
 * @author: humban
 */
package com.usmb.grp1.info405api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usmb.grp1.info405api.model.Creneau;
import com.usmb.grp1.info405api.model.ProduitEntrepot;

/**
 * 
 */
@Repository
public interface CreneauRepository extends JpaRepository<Creneau, Long> {
	// recupere les creneaux de l'utilisateur 
	List<Creneau> findByUtilisateur_id(Long id);

	// recupere les creneaux d'un produit
	List<Creneau> findByProduitEntrepots_produit_id(Long id);
	
	// recupere les creneaux d'un produit de l'entrepot
	List<Creneau> findByProduitEntrepots_id(Long id);
	
	// recupere les produits de l'entrepots qui ne sont pas entre les deux dates 
	// NOTE: pour recuperer tt les produits de l'entrepot et pas juste ceux dispo, enlever
	// 		 "p.estDispo=True AND"
    @Query("SELECT DISTINCT p FROM ProduitEntrepot p WHERE p.estDispo=True AND p NOT IN " +
            "(SELECT DISTINCT c.produitEntrepots FROM Creneau c WHERE " +
            "(c.dateDebut <= :dEnd1 AND c.dateFin >= :dStart1))")
	List<ProduitEntrepot> findProduitEntrepotHorsDates(@Param("dStart1")LocalDateTime dStart1,@Param("dEnd1")LocalDateTime dEnd1);
	
}

