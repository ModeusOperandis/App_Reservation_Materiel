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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dans spring security, il y a deja classe "Role" on met "AppRole" pour pas se melanger
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;
	@NotNull(message ="rolName ne doit pas etre null")
	private String roleName;
	
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