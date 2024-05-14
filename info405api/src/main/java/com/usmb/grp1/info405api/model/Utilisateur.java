/**
 * 
 */
package com.usmb.grp1.info405api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author humban
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="utilisateur")
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@NotNull(message = "Nom ne doit pas être null")
	private String nom;
	//@NotNull(message = "Prenom ne doit pas être null")
	private String prenom;
	@Email(message="Merci d'entrer une adresse email valide")
	private String email;
	//@NotNull(message = "username ne doit pas être null")
	private String username;
	
	
	// permet d'uniquement ecrire la donnees pas de le consulter
	// comme "JsonIgnore"
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	//@NotNull(message = "password ne doit pas être null")
	private String password;
	
	
	/**
	 * Fetch Lazy: si on charge objet user charge uniquement si on a besoin
	 * ici on veut les roles des users donc EAGER
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> appRoles = new ArrayList<AppRole>();
	
	
	// ajout colonne pour avoir derniere fois ou ca ete modifie
	@CreationTimestamp
	@Column(name="created_at", nullable = false, updatable = false)
	@JsonIgnore
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	@JsonIgnore
	private Date updatedAt;
	
}

