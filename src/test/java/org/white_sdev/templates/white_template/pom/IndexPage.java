package org.white_sdev.templates.white_template.pom;

import lombok.extern.slf4j.Slf4j;
import org.white_sdev.templates.white_template.model.User;
import org.white_sdev.white_seleniumframework.framework.WebDriverUtils;

import java.util.Map;

@Slf4j
public class IndexPage {
	public static void registerUser(final WebDriverUtils utils, User user) {
		String logID = "::registerUser([utils, user]): ";
		log.trace("{}Start ", logID);
		try {
			utils.writeId("first_name", user.getFirstName());
			utils.writeId("email", user.getEmail());
			utils.clickClass("submit");
			utils.acceptAlert();
			log.trace("{}Finish - User registered", logID);
			
		} catch (Exception ex) {
			throw new RuntimeException(String.format("Impossible to register User %s @ home page", user), ex);
		}
	}
	
	public static void synchronize(final WebDriverUtils utils) {
		String logID = "::synchronize([utils]): ";
		log.trace("{}Start ", logID);
		try {
			utils.clickText("Synchronize");
			log.trace("{}Finish", logID);
		} catch (Exception ex) {
			throw new RuntimeException("Impossible to synchronize the table by clicking the button @ home page.", ex);
		}
	}
	
	public static int getElementsInTable(final WebDriverUtils utils) {
		return utils.getTableDataWithTag().orElseThrow().size();
	}
	
	public static boolean isUserInTable(final WebDriverUtils utils, User user) {
		for (Map<String, String> userRow : utils.getTableDataWithText("Email").orElseThrow())
			if (userRow.get("First Name").equals(user.getFirstName())) return true;
		return false;
	}
}