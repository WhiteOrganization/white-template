package org.white_sdev.templates.white_template.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.white_sdev.templates.white_template.controller.MainController;
import org.white_sdev.templates.white_template.model.persistence.User;
import org.white_sdev.templates.white_template.functional.pom.IndexPage;
import org.white_sdev.white_seleniumframework.framework.SeleniumJupiterScenario;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@lombok.extern.slf4j.Slf4j
public class MainControllerFunctionalIT extends SeleniumJupiterScenario {
	
	//region SeleniumJupiter Extension
	@SuppressWarnings("all")
	@RegisterExtension
	SeleniumJupiter seleniumJupiter = new SeleniumJupiter();
	@Override
	public SeleniumJupiter getSeleniumJupiterRegisteredExtension() {
		return seleniumJupiter;
	}
	//endregion SeleniumJupiter Extension

	@LocalServerPort
	protected int localPort;
	
	//region Tests
	
	@BeforeAll
	public static void beforeAll(){
		System.setProperty("java.awt.headless", "false");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
	}
	
	//region CRUD User Test
	@Autowired
	MainController mainController;
	public static int userCount = 0;
	
	@Test
//	@EnabledIfBrowserAvailable(Browser.CHROME)
//	@EnabledIfDriverUrlOnline("http://localhost:8080")
	@DisplayName("Register a dummy user Functional Test")
	@Disabled //Impossible to work with headless from White_SeleniumFramework in this version.
	@SneakyThrows
	public void registerUser(TestInfo testInfo) {
		String logID = "::registerUser([driver]): ";
		log.trace("{}Start ", logID);
		String displayName = testInfo.getDisplayName();
		
		try {
			
			//region Setup
			IndexPage indexPage = new IndexPage(driver).openPage(localPort);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			User user = new User("baz" + (++MainControllerFunctionalIT.userCount), "baz@zinga.com");
			//endregion Setup
			
			//region Execution
			log.trace("{}Opening website", logID);
			
			indexPage.synchronize();
			int initialNumOfUsers = indexPage.getElementsInTable();
			log.debug("{} The initial number of users is: {}", logID, initialNumOfUsers);
			indexPage.registerUser(user);
			indexPage.synchronize();
			int newNumOfUsers = indexPage.getElementsInTable();
			log.debug("{} The final number of users is: {}", logID, newNumOfUsers);
			boolean isUserInTable = indexPage.isUserInTable(user);
			log.debug("{}The user is {}in the table.", logID, isUserInTable ? "" : "not ");
			//endregion Execution
			
			//region Validations
			log.trace("{}Validating....", logID);
			assertThat(newNumOfUsers)
					.as("Table increment by one due to new user Verification")
					.isGreaterThan(initialNumOfUsers)
					.isEqualTo(initialNumOfUsers + 1);
			assertThat(isUserInTable)
					.as("Created user shows up in table Verification")
					.isTrue();
			assertThat(mainController.getUsers())
					.as("Backend shows the same data Verification")
					.hasSize(newNumOfUsers);
			//endregion Validations
			log.info("{}Success.", logID);
			
		} catch (Exception | AssertionError ex) {
			log.info("{}{} Failed.", logID, displayName);
			throw new RuntimeException("An Exception has occurred when executing " + displayName, ex);
		}
	}
	
	//endregion CRUD User
	
	//endregion Tests
}

