/**
 * @author: humban
 */
package com.usmb.grp1.info405api.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Utilisateur;
import com.usmb.grp1.info405api.security.service.AccountService;

import lombok.AllArgsConstructor;

/**
 * 
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur appUser = accountService.loadUserByUsername(username);
		// TODO: add verif /= null (user existe pas) -> exception
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		appUser.getAppRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		String[] roles = appUser.getAppRoles().stream().map(u -> u.getRoleName()).toArray(String[]::new);
		UserDetails userDetails = User
				.withUsername(username)
				.password(appUser.getPassword())
				.roles(roles).build();
		return userDetails; // 'User' de spring security
	}

}
