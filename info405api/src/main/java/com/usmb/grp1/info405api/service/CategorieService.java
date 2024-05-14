/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;


import com.usmb.grp1.info405api.model.Categorie;

/**
 * interface pour notre service sur les categories
 * @author humban
 */

public interface CategorieService {
	List<Categorie> getCategories();
	
	Categorie saveCategorie(Categorie categorie);
	
	Categorie getSingleCategorie(Long id);
	
	void deleteCategorie(Long id);
	
	Categorie updateCategorie(Categorie categorie);
}
