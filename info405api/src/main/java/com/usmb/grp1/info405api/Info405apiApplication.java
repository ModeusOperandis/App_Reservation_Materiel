package com.usmb.grp1.info405api;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.usmb.grp1.info405api.model.AppRole;
import com.usmb.grp1.info405api.model.Categorie;
import com.usmb.grp1.info405api.model.Creneau;
import com.usmb.grp1.info405api.model.Image;
import com.usmb.grp1.info405api.model.Marque;
import com.usmb.grp1.info405api.model.Produit;
import com.usmb.grp1.info405api.model.ProduitEntrepot;
import com.usmb.grp1.info405api.model.Utilisateur;
import com.usmb.grp1.info405api.security.service.AccountService;
import com.usmb.grp1.info405api.service.CategorieService;
import com.usmb.grp1.info405api.service.CreneauService;
import com.usmb.grp1.info405api.service.ImageService;
import com.usmb.grp1.info405api.service.MarqueService;
import com.usmb.grp1.info405api.service.ProduitEntrepotService;
import com.usmb.grp1.info405api.service.ProduitService;

@SpringBootApplication
public class Info405apiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Info405apiApplication.class, args);
	}
	
	
	/**
	 * pour tester notre "AccountService"
	 * @param accountService
	 * @return
	 */
	@Bean
	@Profile("!test")
	CommandLineRunner start(
			AccountService accountService, 
			ProduitService produitService, 
			CategorieService categorieService, 
			ImageService imageService,
			MarqueService marqueService,
			ProduitEntrepotService entrepotService,
			CreneauService creneauService
			) {
		return args -> {
			accountService.addNewRole(new AppRole(null, "USER", null, null));
			accountService.addNewRole(new AppRole(null, "ADMIN", null, null));
			
			accountService.addNewUser(new Utilisateur(null, "Damien", "Panpan","damien@gmail.com","damien", "1234",new ArrayList<>(), null, null));
			accountService.addNewUser(new Utilisateur(null, "Yvan", "Granola","yvan.granola@gmail.com","yvan", "1234",new ArrayList<>(), null, null));
			Utilisateur admin = new Utilisateur(null, "ADMIN", "ADMIN","amin@gmail.com","admin", "1234",new ArrayList<>(), null, null);
			accountService.addNewUser(admin);
			accountService.addNewUser(new Utilisateur(null, "User", "Un","user1@gmail.com","user1", "1234",new ArrayList<>(), null, null));
//			
			//			
			//accountService.saveUtilisateur(new Utilisateur(null, "admin", "1234", new ArrayList<>()));
//			accountService.saveUtilisateur(new Utilisateur(null, "user2", "1234", new ArrayList<>()));
			
			accountService.addRoleToUser("user1", "USER");
			accountService.addRoleToUser("damien", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");
			accountService.addRoleToUser("yvan", "USER");
			accountService.addRoleToUser("yvan", "ADMIN");
			
			// AJOUT CATEGORIES
			Categorie audioCat = new Categorie(null, "Audio", null, null);
			Categorie cameraCat = new Categorie(null, "Camera", null, null);
			categorieService.saveCategorie(audioCat);
			categorieService.saveCategorie(cameraCat);
			
			// AJOUT IMAGE
			Image image = new Image(null, "lien-vers-image", null,null);
			imageService.saveImage(image);
			
			// AJOUT DE MARQUES
			Marque sony = new Marque(null, "Sony", null, null);
			Marque rode = new Marque(null, "Rode", null, null);
			Marque lenovo = new Marque(null, "Lenovo", null, null);
			Marque autre = new Marque(null, "Autre", null, null);
			marqueService.saveMarque(sony);
			marqueService.saveMarque(rode);
			marqueService.saveMarque(lenovo);
			marqueService.saveMarque(autre);
			
			// AJOUT DE PRODUITS
			Produit p1 = new Produit(null, "Rode NT1A", "Le Rode NT1A détient, grâce à sa versatilité, la réputation d’être le meilleur choix de microphone à condensateur d’entrée de gamme.", "4512", 100.0, rode, image, audioCat, null, null);
			produitService.saveProduit(p1);

			Produit p2 = new Produit(null, "Sony Alpha a7 III", "L'appareil photo sans miroir Sony Alpha a7 III offre une qualité d'image exceptionnelle et des performances rapides dans un boîtier compact et robuste.", "A7III", 2000.0, sony, image, cameraCat, null, null);
			produitService.saveProduit(p2);

			Produit p3 = new Produit(null, "Audio-Technica AT2020", "Le microphone à condensateur Audio-Technica AT2020 offre une qualité audio exceptionnelle pour l'enregistrement de voix et d'instruments.", "AT2020", 150.0, autre, image, audioCat, null, null);
			produitService.saveProduit(p3);

			Produit p4 = new Produit(null, "Canon EOS 5D Mark IV", "Le Canon EOS 5D Mark IV est un appareil photo reflex numérique polyvalent doté d'un capteur CMOS plein format de 30,4 mégapixels.", "5D Mark IV", 3000.0, autre, image, cameraCat, null, null);
			produitService.saveProduit(p4);

			Produit p5 = new Produit(null, "Shure SM58", "Le microphone dynamique Shure SM58 est un standard de l'industrie pour les performances vocales en direct et l'enregistrement en studio.", "SM58", 120.0, autre, image, audioCat, null, null);
			produitService.saveProduit(p5);

			Produit p6 = new Produit(null, "Nikon D850", "Le Nikon D850 est un appareil photo reflex numérique plein format offrant une qualité d'image exceptionnelle et une performance rapide.", "D850", 3500.0, autre, image, cameraCat, null, null);
			produitService.saveProduit(p6);

			Produit p7 = new Produit(null, "Sennheiser HD 650", "Le casque Sennheiser HD 650 offre un son naturel et équilibré avec des basses profondes et des aigus clairs.", "HD 650", 250.0, autre, image, audioCat, null, null);
			produitService.saveProduit(p7);

			Produit p8 = new Produit(null, "Fujifilm X-T4", "Le Fujifilm X-T4 est un appareil photo sans miroir APS-C offrant des performances rapides et une excellente qualité d'image.", "X-T4", 1800.0, autre, image, cameraCat, null, null);
			produitService.saveProduit(p8);

			Produit p9 = new Produit(null, "AKG C414 XLII", "Le microphone à condensateur AKG C414 XLII offre une reproduction sonore précise et une polyvalence exceptionnelle pour l'enregistrement en studio.", "C414 XLII", 800.0, autre, image, audioCat, null, null);
			produitService.saveProduit(p9);

			Produit p10 = new Produit(null, "Panasonic Lumix GH5", "Le Panasonic Lumix GH5 est un appareil photo hybride micro quatre tiers offrant une vidéo 4K de haute qualité et des performances de prise de vue rapides.", "Lumix GH5", 2000.0, autre, image, cameraCat, null, null);
			produitService.saveProduit(p10);
			
			// AJOUT DE PRODUITS A L'ENTREPOT
			ProduitEntrepot pe1 = new ProduitEntrepot(null, true, p1, null, null);
			ProduitEntrepot pe2 = new ProduitEntrepot(null, true, p2, null, null);
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, false, p3, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p4, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p5, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, false, p6, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p7, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p8, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p9, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p10, null, null));
			entrepotService.saveProduitEntrepot(pe1);
			entrepotService.saveProduitEntrepot(pe2);
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p1, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, false, p1, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, false, p1, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p1, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p4, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p5, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p6, null, null));
			entrepotService.saveProduitEntrepot(new ProduitEntrepot(null, true, p7, null, null));
			
			
			// AJOUT DE CRENEAU
			ArrayList<ProduitEntrepot> peList= new ArrayList<ProduitEntrepot>();
			peList.add(pe1);
			peList.add(pe2);
			Creneau c1 = new Creneau(null, admin, LocalDateTime.of(2024, 5, 25,0,0), LocalDateTime.of(2024, 6, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c1);
			peList.remove(1);
			Creneau c2 = new Creneau(null, admin, LocalDateTime.of(2024, 7, 10,0,0), LocalDateTime.of(2024, 8, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c2);
			peList.add(pe1);
			Creneau c3 = new Creneau(null, admin, LocalDateTime.of(2024, 9, 1,0,0), LocalDateTime.of(2024, 10, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c3);
			peList.remove(1);
			Creneau c4 = new Creneau(null, admin, LocalDateTime.of(2024, 11, 10,0,0), LocalDateTime.of(2025, 12, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c4);
			peList.add(pe1);
			Creneau c5 = new Creneau(null, admin, LocalDateTime.of(2025, 5, 25,0,0), LocalDateTime.of(2025, 6, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c5);
			peList.remove(1);
			Creneau c6 = new Creneau(null, admin, LocalDateTime.of(2025, 7, 10,0,0), LocalDateTime.of(2025, 8, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c6);
			peList.add(pe1);
			Creneau c7 = new Creneau(null, admin, LocalDateTime.of(2025, 9, 1,0,0), LocalDateTime.of(2025, 10, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c7);
			peList.remove(1);
			Creneau c8 = new Creneau(null, admin, LocalDateTime.of(2025, 11, 10,0,0), LocalDateTime.of(2025, 12, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c8);
			peList.add(pe1);
			Creneau c9 = new Creneau(null, admin, LocalDateTime.of(2026, 5, 25,0,0), LocalDateTime.of(2026, 6, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c9);
			peList.remove(1);
			Creneau c10 = new Creneau(null, admin, LocalDateTime.of(2026, 7, 10,0,0), LocalDateTime.of(2026, 8, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c10);
			peList.add(pe1);
			Creneau c11 = new Creneau(null, admin, LocalDateTime.of(2026, 9, 1,0,0), LocalDateTime.of(2026, 10, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c11);
			peList.remove(1);
			Creneau c12 = new Creneau(null, admin, LocalDateTime.of(2026, 11, 10,0,0), LocalDateTime.of(2026, 12, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c12);
			peList.add(pe1);
			Creneau c13 = new Creneau(null, admin, LocalDateTime.of(2027, 5, 25,0,0), LocalDateTime.of(2027, 6, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c13);
			peList.remove(1);
			Creneau c14 = new Creneau(null, admin, LocalDateTime.of(2027, 7, 10,0,0), LocalDateTime.of(2027, 8, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c14);
			peList.add(pe1);
			Creneau c15 = new Creneau(null, admin, LocalDateTime.of(2027, 9, 1,0,0), LocalDateTime.of(2027, 10, 9,0,0), peList, null, null);
			creneauService.saveCreneau(c15);
			peList.remove(1);
			Creneau c16 = new Creneau(null, admin, LocalDateTime.of(2027, 11, 10,0,0), LocalDateTime.of(2027, 12, 25,0,0), peList, null, null);
			creneauService.saveCreneau(c16);
			peList.add(pe1);
			
		};
	}

}
