/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Creneau;
import com.usmb.grp1.info405api.model.ProduitEntrepot;
import com.usmb.grp1.info405api.repository.CreneauRepository;

/**
 * 
 */
@Service
public class CreneauServiceImpl implements CreneauService {
	
	@Autowired
	private CreneauRepository cRepository;
	
	@Override
	public List<Creneau> getCreneaux() {
		return cRepository.findAll();
	}

	@Override
	public Creneau saveCreneau(Creneau creneau) {
		return cRepository.save(creneau);
	}

	@Override
	public Creneau getSingleCreneau(Long id) {
		Optional<Creneau> cOptional = cRepository.findById(id);
		if (cOptional.isPresent()) { // si Creneau existe
			return cOptional.get();
		}
		// si Creneau existe pas alors on genere une exception
		throw new RuntimeException("Creneau is not found for the id:"+id);
	}

	@Override
	public void deleteCreneau(Long id) {
		cRepository.deleteById(id);

	}

	@Override
	public Creneau updateCreneau(Creneau creneau) {
		return cRepository.save(creneau);
	}
	
	// FONCTIONS CUSTOM

	@Override
	public List<Creneau> loadByUtilisateur(Long id) {
		return cRepository.findByUtilisateur_id(id);
	}

	@Override
	public List<Creneau> loadByProduit(Long id) {
		return cRepository.findByProduitEntrepots_produit_id(id);
	}

	@Override
	public List<Creneau> loadByProduitEntrepot(Long id) {
		return cRepository.findByProduitEntrepots_id(id);
	}

	@Override
	public List<ProduitEntrepot> loadProduitEntrepotByDate(LocalDateTime dateStart, LocalDateTime dateEnd) {
		return cRepository.findProduitEntrepotHorsDates(dateStart, dateEnd);
	}

}
