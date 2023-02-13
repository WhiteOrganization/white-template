package org.white_sdev.templates.white_template;

import io.github.bonigarcia.seljup.EnabledIfDriverUrlOnline;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.white_sdev.templates.white_template.pom.CalculatorPage;
import org.white_sdev.white_seleniumframework.framework.SeleniumJupiterScenario;

import java.time.Duration;

@Slf4j
public class NoSpringFunctionalIT extends SeleniumJupiterScenario {
	
	//region SeleniumJupiter Extension
	@SuppressWarnings("all")
	@RegisterExtension
	SeleniumJupiter seleniumJupiter = new SeleniumJupiter();
	@Override
	public SeleniumJupiter getSeleniumJupiterRegisteredExtension() {
		return seleniumJupiter;
	}
	//endregion SeleniumJupiter Extension
	
	CalculatorPage calculatorPage;
	
	//region Tests
	@Test
	@DisplayName("Basic calculator operations Functional Test")
	@EnabledIfDriverUrlOnline("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html")
	void calculatorTest() {
		calculatorPage= new CalculatorPage(driver).openPage();
		
		// 1 + 3
		calculatorPage.clickNumber(1);
		calculatorPage.plus();
		calculatorPage.clickNumber(3);
		calculatorPage.equals();
		
		// ... should be 4, wait for it
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBe(By.className("screen"), "4"));
	}
	
	//endregion Tests
}