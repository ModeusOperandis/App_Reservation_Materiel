package com.usmb.grp1.info405api;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

//@SpringBootTest
@Disabled("Cette classe de test est désactivée pour le moment")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Info405apiApplicationTests {
	
	@LocalServerPort
    private int port;
	
	//@Test
	void contextLoads() {
	}

}
