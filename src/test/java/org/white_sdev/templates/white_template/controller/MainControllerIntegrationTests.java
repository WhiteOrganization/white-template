package org.white_sdev.templates.white_template.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.white_sdev.templates.white_template.model.User;
import org.white_sdev.white_seleniumframework.framework.SeleniumJupiterScenario;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Documentation sample: https://www.arhohuttunen.com/spring-boot-integration-testing/
 */
@lombok.extern.slf4j.Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@org.springframework.transaction.annotation.Transactional
@ActiveProfiles("test")
class MainControllerIntegrationTests {
	
//	@org.springframework.beans.factory.annotation.Value("${local.server.port}")
//	protected int localPort;
	
	@Autowired
	MainController mainController;
	@Autowired
	private MockMvc mockMvc;
//	@Autowired
//	private WebTestClient webClient;
	
	ObjectMapper javaToJsonMapper = new ObjectMapper();
	
	//region Before & After
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("java.awt.headless", "false");
	}
	
	@BeforeEach
	public void beforeEach(TestInfo testInfo) {
		String displayName = testInfo.getDisplayName();
		org.slf4j.MDC.put("testId", displayName);
		log.info("{}Test Name: {} {}", SeleniumJupiterScenario.ScenarioUtils.getLogBreak(), displayName, SeleniumJupiterScenario.ScenarioUtils.getLogBreak());
	}
	
	@AfterEach
	public void afterEach() {
		String logID = "::afterEach([testInfo]): ";
		log.trace("{}Start ", logID);
		log.info("{}{}", logID, SeleniumJupiterScenario.ScenarioUtils.getLogBreak());
		org.slf4j.MDC.remove("testId");
	}
	
	//endregion Before & After
	
	@Test
	@SneakyThrows
//	@org.springframework.test.context.jdbc.Sql("/custom-scenario-set-up-inserts.sql")
	public void customTest() {
		
		//region Set-up
		SoftAssertions soft = new SoftAssertions();
		//configuration check
		assertThat(mainController)
				.as("Spring initialization is configured and running Verification.")
				.isNotNull()
				.isInstanceOf(MainController.class);
		List<User> initialUsers = mainController.getUsers();
		soft.assertThat(initialUsers) //This might not be required in this case.
				.as("Initial users load (of 2) Verification")
				.isNotNull()
				.isNotEmpty()
				.hasSize(2);
		User dummyUser = new User("dummy","dummy@decoy.com");
		String jsonUser = javaToJsonMapper.writeValueAsString(dummyUser);
		//endregion
		
		//Main call
		mockMvc.perform(post("/user")
								.contentType(MediaType.APPLICATION_JSON)
								.content(jsonUser))
				.andExpect(status().isCreated());
//		mainController.users.remove(firstUser);
		
		
		
		List<User> finalUsers = mainController.getUsers();
		soft.assertThat(finalUsers)
				.as("'Final user list has 1 extra user and it is the created one' Verification")
				.isNotNull()
				.isNotEmpty()
				.contains(dummyUser)
				.hasSize(initialUsers.size() + 1);
		
		
	}
}