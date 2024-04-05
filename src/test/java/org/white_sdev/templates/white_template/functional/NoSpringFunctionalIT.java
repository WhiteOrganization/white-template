package org.white_sdev.templates.white_template.functional;

import io.github.bonigarcia.seljup.EnabledIfDriverUrlOnline;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.white_sdev.templates.white_template.functional.pom.CalculatorPage;

import java.time.Duration;

@Slf4j
public class NoSpringFunctionalIT {

	CalculatorPage calculatorPage;
	
//	@SuppressWarnings("all")
//	@RegisterExtension
//	SeleniumJupiter seleniumJupiter = new SeleniumJupiter();
//
//	@DriverCapabilities
//	Capabilities capabilities = new ChromeOptions();
//	@BeforeEach
//	public void init(){
//		((ChromeOptions)capabilities).addArguments("--remote-allow-origins=*");
//	}
//
//
//	//region Tests
//	@Test
//	@DisplayName("Basic calculator operations Functional Test")
//	@EnabledIfDriverUrlOnline("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html")
//	void calculatorTest(WebDriver driver) {

	public static boolean HEADLESS = true;
	public static ChromeOptions chromeOptions = new ChromeOptions();
	@BeforeAll
	static void beforeAll() {
		String logID="::beforeAll(): ";
		log.trace("{}Start", logID);
		chromeOptions.addArguments("--remote-allow-origins=*");
		if(HEADLESS){
			chromeOptions.addArguments("--headless=new");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("disable-gpu");
			chromeOptions.addArguments("window-size=1980,960");
		}else{
			System.setProperty("java.awt.headless", "false");
		}
		WebDriverManager.chromedriver().setup();
		log.trace("{}Finish", logID);
	}
	WebDriver driver;
	@BeforeEach
	void beforeEach() {
		driver = new ChromeDriver(chromeOptions);
	}
	
	@Test
	@DisplayName("Basic calculator operations Functional Test")
	@EnabledIfDriverUrlOnline("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html")
	void calculatorTest(){
		try {
			calculatorPage = new CalculatorPage(driver).openPage();
			
			// 1 + 3
			calculatorPage.clickNumber(1);
			calculatorPage.plus();
			calculatorPage.clickNumber(3);
			calculatorPage.equals();
			
			// ... should be 4, wait for it
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.textToBe(By.className("screen"), "4"));
		}catch (Exception e){
			throw new RuntimeException(e);
		}finally {
			driver.quit();
		}
	}
	
	//endregion Tests
}