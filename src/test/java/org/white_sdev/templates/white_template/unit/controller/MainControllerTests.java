package org.white_sdev.templates.white_template.unit.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.white_sdev.templates.white_template.controller.MainController;
import org.white_sdev.templates.white_template.model.persistence.User;
import org.white_sdev.templates.white_template.repo.UserRepository;
import org.white_sdev.templates.white_template.view.UserFrame;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * This Test is not hooked into Spring, so it won't recognize the `application.properties` properties.
 * Instead, it will look for the logging properties at the `logback-test.xml` file.
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
public class MainControllerTests { //surefire detects names that starts with Test, or ends with Test, Tests or TestCase


	@Test
	public void getUsersTest(){
		//region SetUp
		//use a spy when you want the actual
		UserRepository userRepositoryMock = mock(UserRepository.class);
		MainController mainControllerSpy = spy(new MainController(userRepositoryMock));
		//endregion
		
		//Main Call
		List<User> users = mainControllerSpy.getUsers();
		
		//region Validations
		verify(mainControllerSpy).getUsersSet();
		assertThat(users)
				.as("Users has not preloaded values Verification")
				.isEmpty();
		//endregion Validations
	}
	
	@Test
	public void saveTest(){
		String logID="::saveTest(): ";
		log.info("{}Start", logID);
		//region SetUp
		UserRepository userRepositoryMock = mock(UserRepository.class);
		MainController mainControllerSpy = spy(new MainController(userRepositoryMock));
		UserFrame viewMock = mock(UserFrame.class);
		javax.swing.JTextField jTextFieldMock = new javax.swing.JTextField();
		
		//we can mock 2nd lvl calls
		when(viewMock.getFirstNameTextField()).thenReturn(jTextFieldMock);
		when(viewMock.getEmailTextField()).thenReturn(jTextFieldMock);
		
		mainControllerSpy.view = viewMock;
		
		//or we can intercept with the spy
		doNothing().when(mainControllerSpy).loadUsers();
		
		User user = mock(User.class);
		//save previous states
		List<User> oldUsers = mainControllerSpy.getUsers();
		doReturn(true)
				.when(mainControllerSpy).create(user);
		
		//endregion
		
		//Main Call
		mainControllerSpy.save(user, false);
		
		//region Validations
		verify(mainControllerSpy).create(user);//verify create() was called with that user
		List<User> users= mainControllerSpy.getUsers();
		log.info("{}Finish - users:{}", logID, users);
		assertThat(users)
				.as("Number of users didn't change verification.")
//				.isNotEmpty()
				.hasSameSizeAs(oldUsers);
		//endregion Validations
	}
}