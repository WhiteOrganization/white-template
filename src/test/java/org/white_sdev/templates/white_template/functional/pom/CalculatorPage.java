package org.white_sdev.templates.white_template.functional.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.white_sdev.white_seleniumframework.framework.WebDriverUtils;

@lombok.extern.slf4j.Slf4j
public class CalculatorPage {
	final WebDriver driver;
	final WebDriverUtils utils;
	
	public CalculatorPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		utils = new WebDriverUtils(this.driver=driver);
	}
	
	//region Page Elements
	@FindBy(css=".clear btn btn-outline-danger")
	WebElement clearButton;
	@FindBy(css=".screen")
	WebElement screen;
	
	//endregion Page Elements
	
	//region Page Actions
	
	public CalculatorPage openPage(){
		utils.openURL("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
		return this;
	}
	public void clickNumber(int number){
		String logID="::clickNumber([number]): ";
		log.trace("{}Start ", logID);
		utils.clickText(number+"");
		log.trace("{}Finish", logID);
	}
	
	@SuppressWarnings("unused")
	public void clear(){
		String logID="::clear([]): ";
		log.trace("{}Start ", logID);
		clearButton.click();
		log.trace("{}Finish", logID);
	}
	
	public void plus(){
		String logID="::plus([]): ";
		log.trace("{}Start ", logID);
		utils.clickText("+");
		log.trace("{}Finish", logID);
	}
	
	public void equals(){
		String logID="::equals([]): ";
		log.trace("{}Start ", logID);
		utils.clickText("=");
		log.trace("{}Finish", logID);
		
	}
	
	@SuppressWarnings("unused")
	public String getScreenValue(){
		String logID="::getScreenValue([]): ";
		log.trace("{}Start ", logID);
		return screen.getText();
	}
	//endregion Page Actions

}