/**
 * @author: humban
 */
package com.usmb.grp1.info405api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.usmb.grp1.info405api.model.Categorie;

/**
 * classe de test pour tester le repository de Categorie
 */
@DataJpaTest
@ActiveProfiles("test")
public class CategorieRepositoryTest {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Test
	void contextLoads() {
		assertThat(categorieRepository).isNotNull();
	}
	
	// --------- BASICS REPOSITORY FUNCTIONS TESTS --------- \\
	@Test
	void testFindAllCategorie() {
		// nvl element
		Categorie cat1 = new Categorie(null, "CategorieTest1", null, null);
		Categorie cat2 = new Categorie(null, "CategorieTest2", null, null);
		// enregistre l'element
		assertNotNull(categorieRepository.save(cat1));
		assertNotNull(categorieRepository.save(cat2));
		// recupere les elements
		assertThat(categorieRepository.findAll()).isNotEmpty().isNotNull();
		assertThat(categorieRepository.findAll().size()).isEqualTo(2);
	}
	
	@Test
	void testSaveCategorie() {
		// nvl element
		Categorie cat = new Categorie(null, "CategorieTest", null, null);
		
		// enregistre l'element
		Categorie saveCate = categorieRepository.save(cat);
		
		assertNotNull(saveCate);
		assertEquals("CategorieTest", saveCate.getNom());
	}
	
	@Test
	void testFindByIdCategorie() {
		// nvl element
		Categorie cat = new Categorie(null, "CategorieTest", null, null);
		
		// enregistre l'element
		Categorie saveCate = categorieRepository.save(cat);
		
		assertNotNull(saveCate);
		
		//recherche element
		Optional<Categorie> optional = categorieRepository.findById(saveCate.getId());
		
		assertTrue(optional.isPresent());
		Categorie catFound = optional.get();
		assertEquals("CategorieTest", catFound.getNom());
	}
	
	@Test
	void testDeleteByIdCategorie() {
		// nvl element
		Categorie cat = new Categorie(null, "CategorieTest", null, null);
		
		// enregistre l'element
		Categorie saveCate = categorieRepository.save(cat);
		
		assertNotNull(saveCate);
		
		assertTrue(categorieRepository.findById(saveCate.getId()).isPresent());
		
		//supprime element
		categorieRepository.deleteById(saveCate.getId());
		
		assertFalse(categorieRepository.findById(saveCate.getId()).isPresent());

	}
	
	@Test
	void testUpdateCategorie() {
		// nvl element
		Categorie cat = new Categorie(null, "CategorieTest", null, null);
		
		// enregistre l'element
		Categorie saveCate = categorieRepository.save(cat);
		
		assertNotNull(saveCate);
		
		assertEquals("CategorieTest", saveCate.getNom());
		
		//modifie l'element  element
		saveCate.setNom("UpdateTest");
		categorieRepository.save(saveCate);
		
		assertTrue(categorieRepository.findById(saveCate.getId()).isPresent());
		
		assertEquals("UpdateTest", saveCate.getNom());

	}
	
	
	// --------- CUSTOMS REPOSITORY FUNCTIONS TESTS --------- \\
	
	
}





















