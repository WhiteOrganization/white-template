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
import org.white_sdev.white_seleniumframework.framework.SeleniumJupiterScenario;

import java.time.Duration;

@Slf4j
public class NoSpringTests extends SeleniumJupiterScenario {
	
	//region SeleniumJupiter Extension
	@SuppressWarnings("all")
	@RegisterExtension
	SeleniumJupiter seleniumJupiter = new SeleniumJupiter();
	@Override
	public SeleniumJupiter getSeleniumJupiterRegisteredExtension() {
		return seleniumJupiter;
	}
	//endregion SeleniumJupiter Extension
	
	//region Tests
	@Test
	@DisplayName("Basic calculator operations Functional Test")
	@EnabledIfDriverUrlOnline("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html")
	void calculatorTest() {
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
		
		// 1 + 3
		driver.findElement(By.xpath("//span[text()='1']")).click();
		driver.findElement(By.xpath("//span[text()='+']")).click();
		driver.findElement(By.xpath("//span[text()='3']")).click();
		driver.findElement(By.xpath("//span[text()='=']")).click();
		
		// ... should be 4, wait for it
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBe(By.className("screen"), "4"));
	}
	
	//endregion Tests
}