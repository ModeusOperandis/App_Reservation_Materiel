/**
 * @author: humban
 */
package com.usmb.grp1.info405api.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

/**
 * classe pour configurer notre app 
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // "prePostEnabled" a true pour utiliser : @PreAuthorize() au dessus de methode controller
//@AllArgsConstructor
//@NoArgsConstructor
public class SecurityConfig{
	
	@SuppressWarnings("unused")
	private UserDetailsServiceImpl userDetailServiceImpl;
	
	/**
	 * clee privee (dans application.properties)
	 */
	@Value("${jwt.secret}")
	private String secret;
	
	/**
	 * methode utilise pour encoder le mot de passe
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	/**
	 * DEPRECATED
	 * TODO: ajouter une methode 'UserDetailsImpl' pour aller chercher les infos dans la base cette fois
	 * @return
	 */
//	@Bean 
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		return new InMemoryUserDetailsManager(
//				User.withUsername("user1").password(passwordEncoder().encode("1234")).authorities("USER").build(),
//				User.withUsername("admin").password(passwordEncoder().encode("1234")).authorities("USER", "ADMIN").build()
//		);
//	}
	
	/**
	 * Premier filtre de securite, determine les droits a avoir pour acceder a une ressource
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.rememberMe();
		http.headers().frameOptions().disable();
		// affiche form si pas connecte
		//http.formLogin();
		//
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.userDetailsService(userDetailServiceImpl);
		//http.securityMatcher("/api/v1/**");
		//http.authorizeRequests().requestMatchers("/users").hasRole("USER");
		//http.authorizeRequests().requestMatchers("/roles").hasRole("ADMIN");
//		http.authorizeRequests().requestMatchers("/h2-console/**").permitAll();
		//http.authorizeHttpRequests(ar->ar.requestMatchers("/users").hasAuthority("SCOPE_ROLE_USER"));
		//http.authorizeHttpRequests(ar->ar.requestMatchers("/utilisateurs/**").hasAuthority("SCOPE_ROLE_ADMIN"));
		http.authorizeHttpRequests(ar->ar.requestMatchers("/appRoles/**").hasAuthority("SCOPE_ROLE_ADMIN"));
		http.authorizeHttpRequests(ar->ar.requestMatchers("/auth/login/**").permitAll());
		http.authorizeHttpRequests(ar->ar.requestMatchers("/auth/signup/**").permitAll());
		http.authorizeHttpRequests(ar->ar.requestMatchers("/h2-console/**").permitAll());
		http.authorizeHttpRequests(ar->ar.anyRequest().authenticated());
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		//http.oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()));
		//http.authorizeRequests().anyRequest().authenticated();
		return http.build();
	}
	
	
	
	
	/**
	 * Bean utilise pour encoder un JWT a envoye
	 * @return
	 */
	@Bean
	JwtEncoder jwtEncoder() {
		//String secret = "7ubqkv2giacd5gofulriy4qo70k9c4qsq757hkvhqdpos1gcmlj0fg5tr7e90t5z";
		return new NimbusJwtEncoder(new ImmutableSecret<>(secret.getBytes()));
	}
	
	/**
	 * Bean utilise pour decoder un JWT recu
	 * @return
	 */
	@Bean
	JwtDecoder jwtDecoder() {
		//String secret = "7ubqkv2giacd5gofulriy4qo70k9c4qsq757hkvhqdpos1gcmlj0fg5tr7e90t5z";
		SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "RSA");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}
	
	
	/**
	 * utilise 'UserDetailsServiceImpl'
	 * @param userDetailService
	 * @return
	 */
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		return new ProviderManager(daoAuthenticationProvider);
	}
}


























