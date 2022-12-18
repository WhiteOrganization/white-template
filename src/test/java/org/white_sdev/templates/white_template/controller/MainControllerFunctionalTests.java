package org.white_sdev.templates.white_template.controller;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.white_sdev.templates.white_template.model.User;
import org.white_sdev.templates.white_template.pom.IndexPage;
import org.white_sdev.templates.white_template.pom.IndexPageSelenium;
import org.white_sdev.white_seleniumframework.framework.AutomationScenario;
import org.white_sdev.white_seleniumframework.framework.AutomationSuite;
import org.white_sdev.white_seleniumframework.framework.WebDriverUtils;

import java.io.File;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainControllerFunctionalTests {
	@Autowired
	MainController mainController;
	
	public static int userCount = 0;
	
	
	@Test
	public void registerUserAsWhite_SeleniumFrameworkTest() {
		String logID = "::registerUserAsWhite_SeleniumFrameworkTest(): new User registration Test";
		log.info("{} ", logID);
		registerTestCase(logID);
		AutomationSuite.launchExecutions();
		log.info("{}Success", logID);
	}
	
	void registerTestCase(String name) {
		AutomationSuite.registerAutomationScenario(new AutomationScenario() {
			@SneakyThrows
			@Override
			public void run(final WebDriverUtils utils) {
				String logID = "::run(): ";
				log.trace("{}Start ", logID);
				try {
					//Set up
					User user = new User("baz" + (++MainControllerFunctionalTests.userCount), "baz@zinga.com");
					
					//region Execution
					utils.openURL("http://localhost:8080");
					IndexPage.synchronize(utils);
					int initialNumOfUsers = IndexPage.getElementsInTable(utils);
					IndexPage.registerUser(utils, user);
					IndexPage.synchronize(utils);
					int newNumOfUsers = IndexPage.getElementsInTable(utils);
					boolean isUserInTable = IndexPage.isUserInTable(utils, user);
					//endregion Execution
					
					//region Validations
					SoftAssertions softly = new SoftAssertions();
					softly.assertThat(newNumOfUsers)
							.as("Table increment by one due to new user Verification")
							.isGreaterThan(initialNumOfUsers)
							.isEqualTo(initialNumOfUsers + 1);
					softly.assertThat(isUserInTable)
							.as("Created user shows up in table Verification")
							.isTrue();
					softly.assertThat(mainController.getUsers())
							.as("Backend shows the same data Verification")
							.hasSize(newNumOfUsers);
					softly.assertAll();
					//endregion Validations
					
					utils.forcedQuit();
					
					log.info("{}Finish", logID);
				} catch (Exception | AssertionError ex) {
					
					String screenShotFileName="./Screenshots/" + logID + "_" + LocalDate.now().toString().replace(":| ", "_") + ".png";
					log.warn("{}Evidences:{}", logID, screenShotFileName);
					FileUtils.copyFile(((TakesScreenshot) utils.driver).getScreenshotAs(OutputType.FILE),
									   new File(screenShotFileName));
					utils.closeWebBrowserWindow();
					throw new RuntimeException(
							String.format(
									"An Exception has occurred when executing %s",
									getScenarioFullName()),
							ex);
				}
			}
			
			@Override
			public String getScenarioFullName() {
				return name;
			}
		});
	}
	
	@SneakyThrows
	@Test
	public void registerUserSeleniumTest() {
		String logID = "registerUserSeleniumTest-";
		log.trace("{}Start ", logID);
		//Set up
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		try {
			User user = new User("baz" + (++MainControllerFunctionalTests.userCount), "baz@zinga.com");
			
			//region Execution
			driver.get("http://localhost:8080");
			IndexPageSelenium.synchronize(driver);
			int initialNumOfUsers = IndexPageSelenium.getElementsInTable(driver);
			IndexPageSelenium.registerUser(driver, user);
			IndexPageSelenium.synchronize(driver);
			int newNumOfUsers = IndexPageSelenium.getElementsInTable(driver);
			boolean isUserInTable = IndexPageSelenium.isUserInTable(driver, user);
			//endregion Execution
			
			//region Validations
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
			
			driver.quit();
			
		} catch (Exception | AssertionError ex) {
			String screenShotFileName="./Screenshots/" + logID + "_" + LocalDate.now().toString().replace(":| ", "_") + ".png";
			log.warn("{}Evidences:{}", logID, screenShotFileName);
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
							   new File(screenShotFileName));
			driver.quit();
			throw new RuntimeException(
					String.format(
							"An Exception has occurred when executing %s",
							logID),
					ex);
		}
	}
	
}

