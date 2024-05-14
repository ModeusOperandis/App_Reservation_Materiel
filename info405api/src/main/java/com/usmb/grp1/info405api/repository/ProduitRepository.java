/**
 * 
 */
package com.usmb.grp1.info405api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmb.grp1.info405api.model.Produit;

/**
 * interface pour notre repository produit
 * but repository: faire communication avec la base de données
 * c'est ici qu'on va écrire nos requetes sql
 * @author humban
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
