/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Categorie;
import com.usmb.grp1.info405api.repository.CategorieRepository;

/**
 * impl de l'interface pour le service des catégories (logique métier)
 * @author humban
 */
@Service
public class CategorieServiceImpl implements CategorieService {
	
	@Autowired
	private CategorieRepository cRepository;

	@Override
	public List<Categorie> getCategories() {
		return cRepository.findAll();
	}

	@Override
	public Categorie saveCategorie(Categorie categorie) {
		return cRepository.save(categorie);
	}

	@Override
	public Categorie getSingleCategorie(Long id) {
		Optional<Categorie> categorie = cRepository.findById(id);
		if (categorie.isPresent()) { // si categorie existe
			return categorie.get();
		}
		// si categorie existe pas alors on genere une exception
		throw new RuntimeException("Catégorie is not found for the id:"+id);
	}

	@Override
	public void deleteCategorie(Long id) {
		cRepository.deleteById(id);
	}

	@Override
	public Categorie updateCategorie(Categorie categorie) {
		return cRepository.save(categorie);
	}

}
