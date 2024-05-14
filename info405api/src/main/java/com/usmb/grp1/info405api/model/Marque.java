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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author humban
 */
@Setter
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor // ajoute constructeur uniquement pour attribut @NonNull
@AllArgsConstructor
@ToString
@Entity
@Table(name="marque")
public class Marque {
	
	@Id
	//@NonNull // pour eviter d'avoir un null pointer exception et genere un constructeur avec cet attribut
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nom;
	
	
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
