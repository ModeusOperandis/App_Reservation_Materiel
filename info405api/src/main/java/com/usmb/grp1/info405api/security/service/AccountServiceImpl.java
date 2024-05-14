/**
 * @author: humban
 */
package com.usmb.grp1.info405api.security.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usmb.grp1.info405api.model.AppRole;
import com.usmb.grp1.info405api.model.Utilisateur;
import com.usmb.grp1.info405api.repository.AppRoleRepository;
import com.usmb.grp1.info405api.repository.UtilisateurRepository;

/**
 * 
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private UtilisateurRepository appUserRepository;
	private AppRoleRepository appRoleRepository;
	
	private PasswordEncoder passwordEncoder;



	public AccountServiceImpl(UtilisateurRepository appUserRepository, AppRoleRepository appRoleRepository
			,PasswordEncoder passwordEncoder
			) {
		super();
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Utilisateur addNewUser(Utilisateur appUser) {
		String pw= appUser.getPassword();
		appUser.setPassword(passwordEncoder.encode(pw));
		return appUserRepository.save(appUser);
	}

	@Override
	public AppRole addNewRole(AppRole appRole) {
		// TODO Auto-generated method stub
		return appRoleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		Utilisateur appUser= appUserRepository.findByUsername(username);
		AppRole appRole= appRoleRepository.findByRoleName(roleName);
		appUser.getAppRoles().add(appRole);
	}

	@Override
	public Utilisateur loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<Utilisateur> listUser() {
		return appUserRepository.findAll();
	}

}