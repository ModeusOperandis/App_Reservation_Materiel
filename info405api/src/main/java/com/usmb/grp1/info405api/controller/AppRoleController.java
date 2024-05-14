/**
 * @author: humban
 */
package com.usmb.grp1.info405api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.AppRole;
import com.usmb.grp1.info405api.service.AppRoleService;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
public class AppRoleController {
	
	@Autowired
	private AppRoleService arService;
	
	@GetMapping("/appRoles")
	//@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public List<AppRole> getAppRoles() {
		return arService.getAppRoles();
	}
	
	@PostMapping("/appRoles")
	public AppRole saveAppRole(@Valid @RequestBody AppRole appRole) {
		return arService.saveAppRole(appRole);
	}

	@GetMapping("/appRoles/{id}")
	public AppRole getAppRole(@PathVariable("id") Long id) {
		return arService.getSingleAppRole(id);
	}
	
	@DeleteMapping("/appRoles")
	public void deleteAppRole(@RequestParam("id") Long id) {
		arService.deleteAppRole(id);
	}
	
	@PutMapping("/appRoles/{id}")
	public AppRole updateAppRole(@PathVariable("id") Long id, @RequestBody AppRole appRole ) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		appRole.setId(id);
		return arService.updateAppRole(appRole);
	}
}
