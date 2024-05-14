/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;

import com.usmb.grp1.info405api.model.AppRole;

/**
 * @author humban
 */
public interface AppRoleService {
	List<AppRole> getAppRoles();
	
	AppRole saveAppRole(AppRole appRole);
	
	AppRole getSingleAppRole(Long id);
	
	void deleteAppRole(Long id);
	
	AppRole updateAppRole(AppRole appRole);
	
}
