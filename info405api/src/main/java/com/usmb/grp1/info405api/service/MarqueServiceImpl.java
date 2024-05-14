package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Marque;
import com.usmb.grp1.info405api.repository.MarqueRepository;

/**
 * @author humban
 */
@Service
public class MarqueServiceImpl implements MarqueService {
	
	@Autowired
	private MarqueRepository mRepository;
	
	@Override
	public List<Marque> getMarques() {
		return mRepository.findAll();
	}

	@Override
	public Marque saveMarque(Marque marque) {
		return mRepository.save(marque);
	}

	@Override
	public Marque getSingleMarque(Long id) {
		Optional<Marque> marque = mRepository.findById(id);
		if (marque.isPresent()) { // si marque existe
			return marque.get();
		}
		// si marque existe pas alors on genere une exception
		throw new RuntimeException("Marque is not found for the id:"+id);
	}

	@Override
	public void deleteMarque(Long id) {
		mRepository.deleteById(id);
	}

	@Override
	public Marque updateMarque(Marque marque) {
		return mRepository.save(marque);
	}

}
