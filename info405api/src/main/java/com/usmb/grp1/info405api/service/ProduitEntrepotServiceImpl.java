/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.ProduitEntrepot;
import com.usmb.grp1.info405api.repository.ProduitEntrepotRepository;

/**
 * 
 */
@Service
public class ProduitEntrepotServiceImpl implements ProduitEntrepotService {
	
	@Autowired
	private ProduitEntrepotRepository peRepository;
	
	@Override
	public List<ProduitEntrepot> getProduitEntrepots() {
		return peRepository.findAll();
	}

	@Override
	public ProduitEntrepot saveProduitEntrepot(ProduitEntrepot produitEntrepot) {
		return peRepository.save(produitEntrepot);
	}

	@Override
	public ProduitEntrepot getSingleProduitEntrepot(Long id) {
		Optional<ProduitEntrepot> produitEntrepot = peRepository.findById(id);
		if (produitEntrepot.isPresent()) { // si produitEntrepot existe
			return produitEntrepot.get();
		}
		// si ProduitEntrepot existe pas alors on genere une exception
		throw new RuntimeException("ProduitEntrepot is not found for the id:"+id);
	}

	@Override
	public void deleteProduitEntrepot(Long id) {
		peRepository.deleteById(id);

	}

	@Override
	public ProduitEntrepot updateProduitEntrepot(ProduitEntrepot produitEntrepot) {
		return peRepository.save(produitEntrepot);
	}
	
	// FONCTIONS CUSTOM
	
	@Override
	public List<ProduitEntrepot> getProduitEntrepotsDispo() {
		return peRepository.findByEstDispo(true);
	}

	@Override
	public List<ProduitEntrepot> loadByProduit(Long id) {
		return peRepository.findByProduit_id(id);
	}

	@Override
	public List<ProduitEntrepot> loadByProduitDispo(Long id) {
		return peRepository.findByProduit_idAndEstDispoTrue(id);
		//return getProduitEntrepots();
	}

	@Override
	public List<ProduitEntrepot> loadByCategorie(Long id) {
		return peRepository.findByProduit_Categorie_id(id);
		//return getProduitEntrepots();
	}

	@Override
	public List<ProduitEntrepot> loadByCategorieDispo(Long id) {
		return peRepository.findByEstDispoTrueAndProduit_Categorie_id(id);
		//return getProduitEntrepots();
	}

	@Override
	public List<ProduitEntrepot> loadByMarque(Long id) {
		return peRepository.findByProduit_Marque_id(id);
	}

	@Override
	public List<ProduitEntrepot> loadByMarqueDispo(Long id) {
		return peRepository.findByEstDispoTrueAndProduit_Marque_id(id);
	}

}
