package org.white_sdev.templates.white_template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.white_sdev.templates.white_template.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MainControllerIntegrationTests {
	
	@Autowired
	MainController mainController;
	
	@Test
	public void customTest() {
		//JUnit5 Assertions
		assertNotNull(mainController);
		
		//AssertJ fluent assertions
		assertThat(mainController)
				.as("Spring initialization is configured and running Verification ")
				.isNotNull()
				.isInstanceOf(MainController.class);
		
		//region Set-up
		List<User> initialUsers = mainController.getUsers();
		User firstUser = initialUsers.get(0);
		//endregion
		
		//Main call
		mainController.users.remove(firstUser);
		
		//region Verifications
		assertThat(initialUsers)
				.as("Initial users load (of 2) Verification")
				.isNotNull()
				.isNotEmpty()
				.hasSize(2);
		
		List<User> finalUsers = mainController.getUsers();
		assertThat(finalUsers)
				.as("'Final user list has only 1 user and it is not the deleted one' Verification")
				.isNotNull()
				.isNotEmpty()
				.doesNotContain(firstUser)
				.hasSize(initialUsers.size()-1);
		
		//endregion
		
		
	}
}