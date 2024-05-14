/**
 * @author: humban
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * classe utilisee pour representer chaque produits dans le stock
 */
@Setter
@Getter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProduitEntrepot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// TODO: voir si @NotNull
	private Boolean estDispo = true;
	
	@ManyToOne
	@JoinColumn(name="produit_id")
	private Produit produit;
	
	
//	@ManyToMany(fetch = FetchType.LAZY)
//    private List<Creneau> creneaux = new ArrayList<>();
	
	
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
