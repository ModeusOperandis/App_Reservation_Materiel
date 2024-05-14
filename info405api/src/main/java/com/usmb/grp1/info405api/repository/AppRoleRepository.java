/**
 * @author: humban
 */
package com.usmb.grp1.info405api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmb.grp1.info405api.model.AppRole;


/**
 * 
 */
@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	AppRole findByRoleName(String roleName);
}
