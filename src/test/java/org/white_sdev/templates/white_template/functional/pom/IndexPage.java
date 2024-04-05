package org.white_sdev.templates.white_template.functional.pom;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.white_sdev.templates.white_template.model.persistence.User;
import org.white_sdev.white_seleniumframework.framework.WebDriverUtils;

import java.util.List;

@Slf4j
public class IndexPage {
	
	public IndexPage(WebDriver driver){
		PageFactory.initElements(driver, this);
//		this.driver = driver;
		utils = new WebDriverUtils(driver);
	}
	
//	private final WebDriver driver;
	private final WebDriverUtils utils;
	
	@FindBy(id="first_name")
	WebElement firstNameInput;
	
	@FindBy(id="email")
	WebElement emailInput;
	
	@FindBy(css=".submit")
	WebElement submitButton;
	
	@FindBy(xpath = "//*[text() = 'Synchronize']")
	@SuppressWarnings("unused")
	WebElement synchronizeButton; //requires an implicit wait utils works best for this case
	
	@FindAll(@FindBy(xpath = "/html/body/div[3]/table//tr/td[1]"))
	List<WebElement> rowsOfTable;


	@SuppressWarnings("unused")
	public IndexPage openPage(){
		return openPage(8080);
	}
	
	public IndexPage openPage(int port){
		utils.openURL("http://localhost:"+port);
		return this;
	}
	
	public void registerUser(User user) {
		String logID = "::registerUser(user): ";
		log.trace("{}Start ", logID);
		try {
			firstNameInput.sendKeys(user.getFirstName());
			emailInput.sendKeys(user.getEmail());
			submitButton.click();
			utils.acceptAlert();
			log.trace("{}Finish - User registered", logID);
			
		} catch (Exception ex) {
			throw new RuntimeException(String.format("Impossible to register User %s", user), ex);
		}
	}
	
	public void synchronize() {
		String logID = "::synchronize(): ";
		log.trace("{}Start - Synchronizing table", logID);
		try {
			utils.clickText("Synchronize",null,null,10,null);
		} catch (Exception ex) {
			throw new RuntimeException("Impossible to synchronize the table by clicking the button.", ex);
		}
	}
	
	public int getElementsInTable() {
		String logID="::getElementsInTable(): ";
		log.trace("{}Start - Obtaining the number of elements in the table", logID);
		return rowsOfTable.size();
	}
	
	public boolean isUserInTable(User user) {
		return rowsOfTable.stream().anyMatch(td -> td.getText().equals(user.getFirstName()));
	}
	
}