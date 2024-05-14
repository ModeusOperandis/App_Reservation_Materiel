/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;

import com.usmb.grp1.info405api.model.Marque;

/**
 * @author humban
 */
public interface MarqueService {
	List<Marque> getMarques();
	
	Marque saveMarque(Marque marque);

	Marque getSingleMarque(Long id);
	
	void deleteMarque(Long id);
	
	Marque updateMarque(Marque marque);
}