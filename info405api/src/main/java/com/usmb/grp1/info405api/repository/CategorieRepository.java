/**
 * 
 */
package com.usmb.grp1.info405api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmb.grp1.info405api.model.Categorie;

/**
 * interface utilisée pour faire un lien avec la db pour les categories
 * @author humban
 */
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
