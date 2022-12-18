package org.white_sdev.templates.white_template.pom;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.white_sdev.templates.white_template.model.User;

import java.time.Duration;
import java.util.stream.Collectors;

@Slf4j
//TODO Generate documentation for every method
public class IndexPageSelenium {
	
	public static void registerUser(final WebDriver driver, User user) {
		String logID = "::registerUser([utils, user]): ";
		log.trace("{}Start ", logID);
		try {
			driver.findElement(By.id("first_name")).sendKeys(user.getFirstName());
			driver.findElement(By.id("email")).sendKeys(user.getEmail());
			driver.findElement(By.className("submit")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2L));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			
			log.trace("{}Finish - User registered", logID);
			
		} catch (Exception ex) {
			throw new RuntimeException(String.format("Impossible to register User %s", user), ex);
		}
	}
	
	public static void synchronize(final WebDriver driver) {
		String logID = "::synchronize([utils]): ";
		log.trace("{}Start ", logID);
		try {
			driver.findElement(By.xpath("//*[text() = 'Synchronize']")).click();
			log.trace("{}Finish", logID);
		} catch (Exception ex) {
			throw new RuntimeException("Impossible to synchronize the table by clicking the button.", ex);
		}
	}
	
	public static int getElementsInTable(final WebDriver driver) {
		return driver.findElements(By.xpath("/html/body/div[3]/table//tr/td[1]")).size();
	}
	
	public static boolean isUserInTable(final WebDriver driver, User user) {
		return driver.findElements(By.xpath("/html/body/div[3]/table//tr/td[1]"))
				.stream().anyMatch(td -> td.getText().equals(user.getFirstName()));
	}
	
}