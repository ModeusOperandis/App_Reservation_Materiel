/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Produit;
import com.usmb.grp1.info405api.repository.ProduitRepository;

/**
 * @author humban
 */
@Service // dit que c'est une classe utilise pour les services
public class ProduitServiceImpl implements ProduitService {
	
	@Autowired
	private ProduitRepository pRepository; 
	
	@Override 
	public List<Produit> getProduits() {
		return pRepository.findAll();
	}

	@Override
	public Produit saveProduit(Produit produit) {
		return pRepository.save(produit);
	}

	@Override
	public Produit getSingleProduit(Long id) {
		Optional<Produit> produit = pRepository.findById(id);
		if (produit.isPresent()) { // si le produit existe
			return produit.get();
		}
		// si le produit existe pas alors on genere une exception
		throw new RuntimeException("Produit is not found for the id:"+id);
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO: voir si on ne devrait pas retourner un bool 
		// pour indiquer si il a ete supprime de la base
		pRepository.deleteById(id); 
	}

	@Override
	public Produit updateProduit(Produit produit) {
		// la difference entre cette methode et celle de 'saveProduit'
		// est qu'ici le "produit" contient l'attribut "id"
		return pRepository.save(produit);
	}
	
	
}
