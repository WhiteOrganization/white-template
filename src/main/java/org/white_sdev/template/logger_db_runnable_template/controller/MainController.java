package org.white_sdev.template.logger_db_runnable_template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * TODO: Complete class documentation
 *
 * @author <a href="mailto:obed.vazquez@gmail.com>Obed Vazquez</a>
 * @since 2022-Aug-01
 */
@Slf4j
@Controller
public class MainController {
	
	public void okButtonPressed(){
		javax.swing.JOptionPane.showMessageDialog(null,"OK Button Pressed!");
	}
}