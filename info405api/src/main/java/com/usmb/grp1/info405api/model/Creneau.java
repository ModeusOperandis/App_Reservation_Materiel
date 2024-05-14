/**
 * @author: humban
 */
package com.usmb.grp1.info405api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * classe representant un creneau
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="creneau")
@AllArgsConstructor
@NoArgsConstructor
public class Creneau {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * utilisateur createur du creneau
	 */
	@ManyToOne
	@JoinColumn(name="createur_id")
	@NotNull(message = "L'utilisateur createur ne doit pas être null")
	private Utilisateur utilisateur;
	
	/**
	 * represente la date du debut de l'emprunt
	 */
	@NotNull(message = "La date de debut ne doit pas être null")
	private LocalDateTime dateDebut;
	
	/**
	 * represente la date de fin de l'emprunt
	 */
	@NotNull(message = "La date de fin ne doit pas être null")
	private LocalDateTime dateFin;
	
	/**
	 * Fetch Lazy: si on charge objet user charge uniquement si on a besoin
	 * ici on veut les roles des users donc EAGER
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<ProduitEntrepot> produitEntrepots = new ArrayList<ProduitEntrepot>();
	
	
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
