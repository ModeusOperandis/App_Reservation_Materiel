/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.usmb.grp1.info405api.model.Categorie;
import com.usmb.grp1.info405api.repository.CategorieRepository;

/**
 * classe de test pour le service de Categorie
 * utilise des "mocks" pour simuler des donnees
 */
//@SpringBootTest
//@Disabled("Cette classe de test est désactivée pour le moment")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategorieServiceTests {
	
	@LocalServerPort
    private int port;
	
	@MockBean
	private CategorieRepository categorieRepository;
	
	@Autowired
	private CategorieService categorieService;
	
//	@BeforeEach
//    public void setUp() {
//		Categorie cat1 = new Categorie(null, "Categorie 1", null, null);
//		Categorie cat2 = new Categorie(null, "Categorie 2", null, null);
//        categorieService.saveCategorie(cat1);
//        categorieService.saveCategorie(cat2);
//    }
	
	@Test
	void contextLoads() {
		assertThat(categorieRepository).isNotNull();
		assertThat(categorieService).isNotNull();
	}
	
	
	// --------- BASICS SERVICE FUNCTIONS TESTS --------- \\
	
	@Test
	void getCategoriesTest(){
		Categorie cat1 = new Categorie(null, "TestCategorie1", null, null);
		Categorie cat2 = new Categorie(null, "TestCategorie2", null, null);
		ArrayList<Categorie> catList = new ArrayList<Categorie>();
		catList.add(cat1);
		catList.add(cat2);
		
		// lorsque 'findAll' appelee alors simule envoie de 2 elements
		when(categorieRepository.findAll()).thenReturn(catList);
		
		assertThat(categorieService.getCategories()).isNotEmpty().isNotNull();
		assertThat(categorieService.getCategories()).hasSize(2);
	}
	
	@Test
	void saveCategorieTest() {
		Categorie cat = new Categorie(null, "TestCategorie", null, null);
		
		// lorsque 'save' appelee alors simule envoie d'un element
		when(categorieRepository.save(any(Categorie.class))).thenReturn(cat);
		
		Categorie saveCategorie = categorieService.saveCategorie(cat);
		
		assertNotNull(saveCategorie);
		assertThat(saveCategorie.getNom()).isSameAs(cat.getNom());

	}
	
	
	@Test
	void getSingleCategorieTestValid() {
		Long id = (long) 1;
		// lorsque 'findById' appelee alors simule envoie d'un element
		when(categorieRepository.findById(any(Long.class))).thenReturn(Optional.of(new Categorie()));
		assertNotNull(categorieService.getSingleCategorie(id));
	}
	
	@Test
	void getSingleCategorieTestFail() {
		Long id = Long.MAX_VALUE;
		// lorsque 'findById' appelee alors simule envoie d'un element
		when(categorieRepository.findById(any(Long.class))).thenReturn(Optional.empty());
		
		assertThrows(RuntimeException.class,() ->categorieService.getSingleCategorie(id));
	}
	
	
	@Test
	void deleteCategorie() {
		Long id = (long) 1;
		Categorie categorieToDelete = new Categorie(1L, "TestCategorie", null, null);
		
		// methode appelee lors 'deleteById' donc on simule qu'on a une donnee 
		when(categorieRepository.findById(1L)).thenReturn(Optional.of(categorieToDelete));
		 
		// ne fait rien lors de la suppression (comportement de base)
		doNothing().when(categorieRepository).deleteById(any(Long.class));
		
		categorieService.deleteCategorie(id);
		
		// verifie que la methode 'delete' a ete appelee 1 fois lors de l'execution
		Mockito.verify(categorieRepository, Mockito.times(1)).deleteById(id);
		
	}
	
	@Test
	void updateCategorie() {
		Categorie cat = new Categorie(1L, "TestCategorie", null, null);
		// lorsque 'save' appelee alors simule envoie d'un element
		when(categorieRepository.save(any(Categorie.class))).thenReturn(cat);
		
		Categorie saveCategorie = categorieService.saveCategorie(cat);
		assertNotNull(saveCategorie);
		assertThat(saveCategorie.getNom()).isSameAs("TestCategorie");
		
		cat.setNom("TestUpdate"); // simule update du nom
		// lorsque 'save' appelee alors simule envoie de l'element modifie
		when(categorieRepository.save(any(Categorie.class))).thenReturn(cat);
		
		Categorie upCategorie = categorieService.saveCategorie(cat);
		assertNotNull(upCategorie);
		assertThat(upCategorie.getNom()).isSameAs("TestUpdate");
		
	}
	
	
	
	// --------- CUSTOMS SERVICE FUNCTIONS TESTS --------- \\
}

























