/**
 * 
 */
package com.usmb.grp1.info405api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmb.grp1.info405api.model.Utilisateur;

/**
 * 
 * @author humban
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Utilisateur findByUsername(String username);
}
