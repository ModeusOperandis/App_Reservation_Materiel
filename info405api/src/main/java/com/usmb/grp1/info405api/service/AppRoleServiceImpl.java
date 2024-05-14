/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.AppRole;
import com.usmb.grp1.info405api.repository.AppRoleRepository;

/**
 * 
 */
@Service
public class AppRoleServiceImpl implements AppRoleService {
	
	@Autowired
	private AppRoleRepository aRRepository;
	
	@Override
	public List<AppRole> getAppRoles() {
		return aRRepository.findAll(); 
	}

	@Override
	public AppRole saveAppRole(AppRole appRole) {
		return aRRepository.save(appRole);
	}

	@Override
	public AppRole getSingleAppRole(Long id) {
		Optional<AppRole> appRole = aRRepository.findById(id);
		if (appRole.isPresent()) { // si role existe
			return appRole.get();
		}
		// si role existe pas alors on genere une exception
		throw new RuntimeException("AppRole is not found for the id:"+id);
	
	}

	@Override
	public void deleteAppRole(Long id) {
		aRRepository.deleteById(id);

	}

	@Override
	public AppRole updateAppRole(AppRole appRole) {
		return aRRepository.save(appRole);
	}

}
