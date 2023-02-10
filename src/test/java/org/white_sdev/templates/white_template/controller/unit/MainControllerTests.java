package org.white_sdev.templates.white_template.controller.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.white_sdev.templates.white_template.controller.MainController;
import org.white_sdev.templates.white_template.model.User;
import org.white_sdev.templates.white_template.view.UserFrame;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MainControllerTests { //surefire detects names that starts with Test, or ends with Test, Tests or TestCase
	
	@Test
	public void getUsersTest(){
		//region SetUp
		//use a spy when you want the actual
		MainController mainControllerSpy = spy(MainController.class);
		//endregion
		
		//Main Call
		List<User> users = mainControllerSpy.getUsers();
		
		//region Validations
		verify(mainControllerSpy).getUsersSet();
		assertThat(users)
				.as("Users has preloaded values Verification")
				.isNotEmpty();
		//endregion Validations
	}
	
	@Test
	public void saveTest(){
		//region SetUp
		MainController mainControllerSpy = spy(new MainController());
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
		assertThat(mainControllerSpy.getUsers())
				.as("Number of users didn't change verification.")
				.isNotEmpty()
				.hasSameSizeAs(oldUsers);
		//endregion Validations
	}
}