/**
 * @author humban
 */
package com.usmb.grp1.info405api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.Marque;
import com.usmb.grp1.info405api.service.MarqueService;

import jakarta.validation.Valid;

/**
 * 
 * @author humban
 */
@RestController // le serveur va rediriger les requetes ici + fait le @ResponseBody (renvoie
				// dans la requete des donnees)
public class MarqueController {

	@Autowired
	private MarqueService mService;

	@GetMapping("/marques")
	public List<Marque> getMarques() {
		return mService.getMarques();
	}

	@PostMapping("/marques")
	public Marque saveMarque(@Valid @RequestBody Marque marque) {
		return mService.saveMarque(marque);
	}

	@GetMapping("/marques/{id}")
	public Marque getMarque(@PathVariable("id") Long id) {
		return mService.getSingleMarque(id);
	}
	
	@DeleteMapping("/marques")
	public void deleteMarque(@RequestParam("id") Long id) {
		mService.deleteMarque(id);
	}
	
	@PutMapping("/marques/{id}")
	public Marque updateMarque(@PathVariable("id") Long id, @RequestBody Marque marque) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		marque.setId(id);
		return mService.updateMarque(marque);
	}
}
