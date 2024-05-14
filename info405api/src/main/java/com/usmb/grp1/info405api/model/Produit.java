/**
 * 
 */
package com.usmb.grp1.info405api.model;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe utilise pour le model de produit
 * @author humban
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="produit")
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Nom ne doit pas être null")
	private String nom;
	
	@Column(name="description", length = 5000)
	private String description;
	
	//@JsonProperty("reference_constructeur") // renvoie cet attribut 
	@Column(name="ref_constructeur")
	private String refConstructeur;
	
	private Double caution = 0.0;
	
	@ManyToOne
	@JoinColumn(name="marque_id")
	private Marque marque;	
	
	
	@ManyToOne
	@JoinColumn(name="image_id")
	private Image image;
	
	@ManyToOne
	@JoinColumn(name="categorie_id")
	private Categorie categorie;
	
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



















