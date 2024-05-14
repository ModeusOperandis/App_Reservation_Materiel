/**
 * @author: humban
 */
package com.usmb.grp1.info405api.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.usmb.grp1.info405api.repository.CreneauRepository;

/**
 * classe de test pour le service de Creneau
 * utilise des "mocks" pour simuler des donnees
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreneauServiceTests {
	
	@LocalServerPort
    private int port;
	
	@MockBean
	private CreneauRepository creneauRepository;
	
	@Autowired
	private CreneauService creneauService;
	
	@Test
	void contextLoads() {
		assertThat(creneauRepository).isNotNull();
		assertThat(creneauService).isNotNull();
	}
	
	// --------- BASICS SERVICE FUNCTIONS TESTS --------- \\
	
	// --------- CUSTOMS SERVICE FUNCTIONS TESTS --------- \\
}
