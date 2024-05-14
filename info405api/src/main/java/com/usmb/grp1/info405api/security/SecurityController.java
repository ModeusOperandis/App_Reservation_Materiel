/**
 * @author: humban
 */
package com.usmb.grp1.info405api.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.Utilisateur;
import com.usmb.grp1.info405api.security.service.AccountService;

/**
 * controller pour la partie Auth, voir ses infos, se connecter
 */
@RestController
@RequestMapping("/auth")
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtEncoder jwtEncoder;
	@Autowired
	private AccountService accountService;
	
	/**
	 * methode pour recuperer info de l'utilisateur connecte
	 * @param authentication
	 * @return
	 */
	@GetMapping("/profile")
	public Authentication authentication(Authentication authentication) {
		return authentication;
	}
	
	/**
	 * methode pour recuperer info de l'utilisateur
	 * va auth le user, si connecte alors appel de cette methode avec en arg ses UserDetails
	 * @param username
	 * @param password
	 * @return Objet Utilisateur avec les donnees 
	 */
	@GetMapping("/utilisateur")
	//@PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
	public Utilisateur getUserInfo(Authentication authentication) {
		// recupere le username de l'utilisateur
		String username = authentication.getName();
		// renvoie l'utilisateur associe au username
		return accountService.loadUserByUsername(username);
	}
	
	/**
	 * methode lors de l'inscription
	 * @param appUser
	 * @return
	 */
	@PostMapping("/signup")
	public Map<String, String> signup(@RequestBody Utilisateur appUser) {
		String username = appUser.getUsername();
		String password = appUser.getPassword();
		// ajout d'un nouvel utilisateur dans la base
		accountService.addNewUser(appUser); 
		return login(username, password);
	}
	
	/**
	 * methode lors de la connexion
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public Map<String, String> login(String username, String password) {
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		// recupere les roles de l'utilisateur separes par espace
		String scope = authentication.getAuthorities().stream().map(a-> a.getAuthority()).collect(Collectors.joining(" "));
		Instant instant = Instant.now(); // date système
		// creattion token JWT
		JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
				.issuedAt(instant)
				.expiresAt(instant.plus(72, ChronoUnit.DAYS))
				.subject(username)
				// peut ajouter 'issuer' adresse http du serveur
				.claim("scope", scope)
				.build();
		
		JwtEncoderParameters jwtEncoderParameters = 
				JwtEncoderParameters.from(
						JwsHeader.with(MacAlgorithm.HS512).build(),
						jwtClaimsSet
				);
		String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
		return Map.of("access-token", jwt);
	}
	
}
