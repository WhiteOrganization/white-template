package org.white_sdev.template.logger_db_runnable_template.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.white_sdev.template.logger_db_runnable_template.controller.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Frame of the application.
 *
 * @author <a href="mailto:obed.vazquez@gmail.com>Obed Vazquez</a>
 * @since 2022-Aug-01
 */
@Slf4j
@Component
public class MainFrame extends JFrame {
	@Autowired
	MainController mainController;
	
	private JButton okButton;
	private JPanel panel1;
	
	/**
	 * Class Constructor. No Requirement Reference
	 *
	 * @author <a href="mailto:obed.vazquez@gmail.com>Obed Vazquez</a>
	 * @since 2022-Aug-01
	 */
	public MainFrame() {
		String logID = "#MainFrame():";
		log.trace("{} Start", logID);
		//checkNotNull("Impossible to create the object. The parameter can't be null.",parameter);
		try {
			okButton=new JButton("OK");
			this.add(okButton);
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					mainController.okButtonPressed();
				}
			});
			this.pack();
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
			log.trace("{} Finish.", logID);
		} catch (Exception e) {
			throw new RuntimeException("Impossible instantiate class MainFrame due to an internal error.", e);
		}
		
	}
}