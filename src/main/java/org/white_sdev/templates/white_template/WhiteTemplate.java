package org.white_sdev.templates.white_template;

import com.formdev.flatlaf.FlatDarkLaf;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.white_sdev.templates.white_template.view.UserFrame;

import javax.swing.*;

/**
 * Main class of the application
 *
 * @since 2022-Aug-01
 */
@SpringBootApplication
@Slf4j
//@ComponentScan(basePackages = {"org.white_sdev.template.logger_db_runnable_template"})
public class WhiteTemplate {
	/**
	 * Main method of the application.
	 * This is the method that will launch the main process and run the provided application.
	 * This is a special method of the language used to launch the main thread;
	 * <a href="https://www.oracle.com/java/technologies/jpl1-building-applications.html#class"> more information</a>.
	 *
	 * @param args {@link String} Arguments provided by the caller of the application (Often omitted)
	 * @since 2022/08/01
	 */
	public static void main(String[] args) {
		String logID = "::main(args[]): ";
		log.trace("{}Start", logID);
		try {
			launchWebApplication(args);
//			launchWebAndDesktopApplication(args);
			
			log.trace("{}Finish", logID);
			
		} catch (Exception e) {
			throw new RuntimeException("Error during execution of the main application process.", e);
		}
	}
	
	@SuppressWarnings("all")
	public static ApplicationContext launchWebApplication(String[] args) {
		return SpringApplication.run(WhiteTemplate.class, args);
	}
	
	@SuppressWarnings("unused")
	@SneakyThrows
	public static ApplicationContext launchWebAndDesktopApplication(String[] args) {
		ApplicationContext springApplicationContext = new SpringApplicationBuilder(WhiteTemplate.class)
				.headless(false)
				.run(args);
		launchMainFrame(springApplicationContext, UserFrame.class);
		return springApplicationContext;
	}
	
	@SneakyThrows
	public static void launchMainFrame(ApplicationContext context, Class<? extends JFrame> mainFrameClass) {
		java.awt.EventQueue.invokeLater(() -> {
			setFlatDarkLookAndFeel();
			try {
				var frame = context.getBean(mainFrameClass);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			} catch (org.springframework.beans.factory.BeanCreationException e) {
				JOptionPane.showMessageDialog(null, "A Fatal exception has occurred when initializing the application.\n " + "Check the logs for more information.");
				throw e;
			}
		});
	}
	
	@SuppressWarnings("unused")
	public static void setWindowsLookAndFeel() throws Exception {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			if ("Windows".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			}
	}
	
	@SneakyThrows(UnsupportedLookAndFeelException.class)
	public static void setFlatDarkLookAndFeel() {
		String logID = "::setLookAndFeel([]): ";
		log.trace("{}Start Installing FlatDarkLookAndFeel", logID);
		UIManager.setLookAndFeel(new FlatDarkLaf());
	}
}