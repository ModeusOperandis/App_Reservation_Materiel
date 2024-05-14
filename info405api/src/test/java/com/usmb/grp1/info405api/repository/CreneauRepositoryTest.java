/**
 * @author: humban
 */
package com.usmb.grp1.info405api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.usmb.grp1.info405api.model.Categorie;
import com.usmb.grp1.info405api.model.Creneau;
import com.usmb.grp1.info405api.model.Image;
import com.usmb.grp1.info405api.model.Marque;
import com.usmb.grp1.info405api.model.Produit;
import com.usmb.grp1.info405api.model.ProduitEntrepot;
import com.usmb.grp1.info405api.model.Utilisateur;

/**
 * classe de test pour tester le repository de Creneau
 */
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // pour reset la db après chaque test
@ActiveProfiles("test")
public class CreneauRepositoryTest {
	
	@Autowired
	private CreneauRepository creneauRepository;
	
	@Autowired
    private UtilisateurRepository utilisateurRepository;
	
	@Autowired
    private ProduitRepository produitRepository; 
	
	@Autowired
    private ProduitEntrepotRepository entrepotRepository; 
	
	@Autowired
    private CategorieRepository categorieRepository; 
	
	@Autowired
    private ImageRepository imageRepository;

	@Autowired
    private MarqueRepository marqueRepository;

	
	@Test
	void contextLoads() {
		assertThat(creneauRepository).isNotNull();
        assertThat(utilisateurRepository).isNotNull();
        assertThat(produitRepository).isNotNull();
        assertThat(entrepotRepository).isNotNull();
        assertThat(categorieRepository).isNotNull();
        assertThat(imageRepository).isNotNull();
        assertThat(marqueRepository).isNotNull();
	}
	
	@BeforeEach
	void setUp() {
		// Setup les entites et ajout dans db
		Utilisateur admin = new Utilisateur(null, "ADMIN", "ADMIN","amin@gmail.com","admin", "1234",new ArrayList<>(), null, null);
		utilisateurRepository.save(admin);
		Categorie audioCat = new Categorie(null, "Audio", null, null);
		categorieRepository.save(audioCat);
		Image image = new Image(null, "lien-vers-image", null,null);
		imageRepository.save(image);
		Marque sony = new Marque(null, "Sony", null, null);
		marqueRepository.save(sony);
		Produit p1 = new Produit(null, "PTest", "descTest.", "4512", 100.0, sony, image, audioCat, null, null);
		produitRepository.save(p1);
		Produit p2 = new Produit(null, "PTest", "descTest.", "4512", 100.0, sony, image, audioCat, null, null);
		produitRepository.save(p2);
		ProduitEntrepot pe1 = new ProduitEntrepot(null, true, p1, null, null);
		ProduitEntrepot pe2 = new ProduitEntrepot(null, true, p2, null, null);
		assertNotNull(entrepotRepository.save(pe1));
		assertNotNull(entrepotRepository.save(pe2));
		ArrayList<ProduitEntrepot> peList= new ArrayList<ProduitEntrepot>();
		peList.add(pe1);
		peList.add(pe2);
		Creneau c1 = new Creneau(null, admin, LocalDateTime.of(2024, 3, 1,0,0), LocalDateTime.of(2024, 3, 8,0,0), peList, null, null);
		assertNotNull(creneauRepository.save(c1));
		ArrayList<ProduitEntrepot> peList2= new ArrayList<ProduitEntrepot>();
		peList2.add(pe2);
		Creneau c2 = new Creneau(null, admin, LocalDateTime.of(2024, 3, 10,0,0), LocalDateTime.of(2024, 3, 25,0,0), peList2, null, null);
		// ajout elements
		assertNotNull(creneauRepository.save(c1));
		assertNotNull(creneauRepository.save(c2));
	}
	
	// --------- BASICS REPOSITORY FUNCTIONS TESTS --------- \\
	
	@Test
	void testFinAllCreneau() {
		// recupere les elements
		assertThat(creneauRepository.findAll()).isNotEmpty().isNotNull();
		assertThat(creneauRepository.findAll().size()).isEqualTo(2);
	}
	
	@Test 
	void testSaveCreneau() {
        Utilisateur admin = new Utilisateur(null, "ADMIN", "ADMIN", "amin@gmail.com", "admin", "1234", new ArrayList<>(), null, null);
        admin = utilisateurRepository.save(admin);
        
        Creneau creneau = new Creneau(null, admin, LocalDateTime.of(2024, 3, 1,0,0), LocalDateTime.of(2024, 3, 9,0,0), new ArrayList<>(), null, null);

        Creneau savedCreneau = creneauRepository.save(creneau);
        // si save ok
        assertNotNull(savedCreneau);
        creneau.setId(savedCreneau.getId()); 
        assertTrue(savedCreneau.equals(creneau));
	}
	
	@Test 
	void testFindByIdCreneau() {
        Utilisateur admin = new Utilisateur(null, "ADMIN", "ADMIN", "amin@gmail.com", "admin", "1234", new ArrayList<>(), null, null);
        admin = utilisateurRepository.save(admin);
        
        Creneau creneau = new Creneau(null, admin, LocalDateTime.of(2024, 3, 1,0,0), LocalDateTime.of(2024, 3, 9,0,0), new ArrayList<>(), null, null);

        Creneau savedCreneau = creneauRepository.save(creneau);
        // si save ok
        assertNotNull(savedCreneau);
        //recherche element
  		Optional<Creneau> optional = creneauRepository.findById(savedCreneau.getId());
  		// verif si present
  		assertTrue(optional.isPresent());
  		Creneau cFound = optional.get();
       
        assertTrue(cFound.equals(creneau));
	}
	
	//TODO: ecrire tests pour delete et update
	
	
	
	// --------- CUSTOMS REPOSITORY FUNCTIONS TESTS --------- \\
	
	@Test
	void testFindByutilisateur_idCreneau() {
		Utilisateur testUser = new Utilisateur(null, "test", "test", "test@gmail.com", "tet", "ts", new ArrayList<>(), null, null);
		testUser = utilisateurRepository.save(testUser);
        assertNotNull(testUser);
		
        Creneau creneau = new Creneau(null, testUser, LocalDateTime.of(2024, 3, 1,0,0), LocalDateTime.of(2024, 3, 9,0,0), new ArrayList<>(), null, null);
        assertNotNull(creneauRepository.save(creneau));
        // recupere les elements
		assertThat(creneauRepository.findAll().size()).isEqualTo(3);
		List<Creneau> nCreneau = creneauRepository.findByUtilisateur_id(testUser.getId());
		assertThat(nCreneau).isNotEmpty().isNotNull();
		assertThat(nCreneau.size()).isEqualTo(1);
		assertTrue(nCreneau.get(0).equals(creneau));
	}
	
	@Test
	void testFindByProduitEntrepots_produit_idCreneau() {
		// recupere le premier produit
		Produit produit = produitRepository.findAll().get(0);
		List<Creneau> nCreneau = creneauRepository.findByProduitEntrepots_produit_id(produit.getId());
		assertThat(nCreneau).isNotEmpty().isNotNull();
		assertThat(nCreneau.size()).isEqualTo(1);
	}
	
	@Test
	void testFindByProduitEntrepots_id() {
		// recupere le premier produit dans entrepot
		ProduitEntrepot produit = entrepotRepository.findAll().get(0);
		List<Creneau> nCreneau = creneauRepository.findByProduitEntrepots_id(produit.getId());
		assertThat(nCreneau).isNotEmpty().isNotNull();
		assertThat(nCreneau.size()).isEqualTo(1);
	}
	
	// test pour voir si on a bien aucun produit dans l'interval donne
	@Test
	void testFindByDateDebutNotBetweenAndDateFinNotBetween_noProduit1() {
		LocalDateTime dateDebut = LocalDateTime.of(2024, 3, 3,0,0);
		LocalDateTime dateFin = LocalDateTime.of(2024, 3, 7,0,0);
		
		List<ProduitEntrepot> nProEntrepot= creneauRepository.findProduitEntrepotHorsDates(dateDebut, dateFin);
		assertThat(nProEntrepot).isEmpty();
		assertThat(nProEntrepot.size()).isEqualTo(0);
	}
	
	// test pour voir si on a bien aucun produit dans l'interval donne
	@Test
	void testFindByDateDebutNotBetweenAndDateFinNotBetween_noProduit2() {
		LocalDateTime dateDebut = LocalDateTime.of(2024, 3, 8,0,0);
		LocalDateTime dateFin = LocalDateTime.of(2024, 3, 13,0,0);
		
		List<ProduitEntrepot> nProEntrepot= creneauRepository.findProduitEntrepotHorsDates(dateDebut, dateFin);
		assertThat(nProEntrepot).isEmpty();
		assertThat(nProEntrepot.size()).isEqualTo(0);
	}
	
	// test pour voir si on a bien 2 produits dans l'interval donne
	@Test
	void testFindByDateDebutNotBetweenAndDateFinNotBetween_twoProduits() {
		LocalDateTime dateDebut = LocalDateTime.of(2024, 1, 8,0,0);
		LocalDateTime dateFin = LocalDateTime.of(2024, 1, 13,0,0);
		
		List<ProduitEntrepot> nProEntrepot= creneauRepository.findProduitEntrepotHorsDates(dateDebut, dateFin);
		assertThat(nProEntrepot).isNotEmpty();
		assertThat(nProEntrepot.size()).isEqualTo(2);
	}
	
	// test pour voir si on a bien 1 produit dans l'interval donne
	@Test
	void testFindByDateDebutNotBetweenAndDateFinNotBetween_OneProduit() {
		LocalDateTime dateDebut = LocalDateTime.of(2024, 3, 11,0,0);
		LocalDateTime dateFin = LocalDateTime.of(2024, 3, 20,0,0);
		
		List<ProduitEntrepot> nProEntrepot= creneauRepository.findProduitEntrepotHorsDates(dateDebut, dateFin);
		assertThat(nProEntrepot).isNotEmpty();
		assertThat(nProEntrepot.size()).isEqualTo(1);
	}
}


























