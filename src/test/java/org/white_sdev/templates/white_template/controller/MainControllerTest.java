package org.white_sdev.templates.white_template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MainControllerTest {
	
	@Autowired
	MainController mainController;
	
	@Test
	public void customTest() {
		assertNotNull(mainController);
	}
}