package org.white_sdev.templates.white_template.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.white_sdev.templates.white_template.controller.MainController;
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
//@PropertySource("classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@ActiveProfiles("test")
@org.springframework.transaction.annotation.Transactional
class MainControllerIT { //failsafe detects names starts with IT, or ends with IT or ITCase
	
	@Autowired
	MainController mainController;
	@Autowired
	@SuppressWarnings("unused")
	private MockMvc mockMvc;
	// Use this for end-to-end tests
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
	
	String moduleSegment = "/api";
	
	@Test
	@SneakyThrows
//	@org.springframework.test.context.jdbc.Sql("/custom-scenario-set-up-inserts.sql")
	public void customTest(@LocalServerPort int port) {
		String logID="::customTest([port]): ";
		log.trace("{}Start - port:{}", logID, port);
		//region Set-up
		
		//configuration check
		assertThat(mainController)
				.as("Spring initialization is configured and running Verification.")
				.isNotNull()
				.isInstanceOf(MainController.class);
		
		List<User> initialUsers = mainController.getUsers();
		SoftAssertions soft = new SoftAssertions();
		soft.assertThat(initialUsers) //This might not be required in this case.
				.as("Initial users load (of 2) Verification")
				.isNotNull()
				.isNotEmpty()
				.hasSize(2);
		User dummyUser = new User("dummy","dummy@decoy.com");
		String jsonUser = javaToJsonMapper.writeValueAsString(dummyUser);
		//endregion
		
		////region Execution
		mockMvc.perform(post(moduleSegment+"/user")
								.contentType(MediaType.APPLICATION_JSON)
								.content(jsonUser))
				.andExpect(status().isCreated());
		
		
		List<User> finalUsers = mainController.getUsers();
		
		soft.assertThat(finalUsers)
				.as("'Final user list has 1 extra user and it is the created one' Verification")
				.isNotNull()
				.isNotEmpty()
				.contains(dummyUser)
				.hasSize(initialUsers.size() + 1);
		
		//endregion Execution
	}
}