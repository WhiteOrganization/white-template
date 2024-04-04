package org.white_sdev.templates.white_template;

import com.formdev.flatlaf.FlatDarkLaf;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.white_sdev.templates.white_template.view.UserFrame;

import javax.swing.*;
import java.time.Duration;
import java.time.Instant;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

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
			
//			executeTestsWithJUnitLauncher();System.exit(0);
			
			log.trace("{}Finish", logID);
			
		} catch (Exception e) {
			throw new RuntimeException("Error during execution of the main application process.", e);
		}
	}
	
	@SuppressWarnings("all")
	public static ApplicationContext launchWebApplication(String[] args) {
		return SpringApplication.run(WhiteTemplate.class, args);
	}

	@SneakyThrows
	@SuppressWarnings({"unused", "UnusedReturnValue"})
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
		String logID = "::setFlatDarkLookAndFeel(): ";
		log.trace("{}Start Installing FlatDarkLookAndFeel", logID);
		UIManager.setLookAndFeel(new FlatDarkLaf());
	}
	
	
	/**
	 * Execute tests detected on the specified packages.
	 * SRC test execution requires
	 * <code>
	 *             <testOutputDirectory>target/classes</testOutputDirectory>
	 * </code>
	 * in the build section to detect the tests classes
	 */
	/*
	 * More information at: https://junit.org/junit5/docs/current/user-guide/#launcher-api
	 * Specification: https://junit.org/junit5/docs/5.0.0/api/org/junit/platform/launcher/core/LauncherDiscoveryRequestBuilder.html
	 * Some examples: https://www.baeldung.com/junit-tests-run-programmatically-from-java
	 */
	@SuppressWarnings("unused")
	public static void executeTestsWithJUnitLauncher(){
		String logID="::executeTestsWithLauncher([]): ";
		log.trace("{}Start ", logID);
		final LauncherDiscoveryRequest request =
				LauncherDiscoveryRequestBuilder.request()
						.selectors( selectPackage("org.white_sdev.templates.white_template") )
						.filters( includeClassNamePatterns("("+ ClassNameFilter.STANDARD_INCLUDE_PATTERN+")||^(IT.*|.+[.$]IT.*|.*ITs?)$") )
						.build();
		
		final Launcher launcher = LauncherFactory.create();
		
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();
		launcher.registerTestExecutionListeners(listener);
		
		launcher.execute(request);
		
		printTestsResults(listener.getSummary());
	}
	
	public static void printTestsResults(TestExecutionSummary summary){
		String logID="::printTestsResults([summary]): ";
		log.trace("{}Start ", logID);
		log.info("{}********************************", logID);
		log.info("{}            REPORT:", logID);
		log.info("{}Total Tests Found: {}", logID, summary.getTestsFoundCount());
		log.info("{}Total Tests Succeeded: {}", logID, summary.getTestsSucceededCount());
		log.info("{}Total Tests Failed: {}", logID, summary.getTotalFailureCount());
		log.info("{}Tests Skipped: {}", logID, summary.getTestsSkippedCount());
		log.info("{}Tests Aborted: {}", logID, summary.getTestsAbortedCount());
		log.info("{}Time Elapsed: {}", logID, Duration.between(Instant.ofEpochMilli(summary.getTimeStarted()), Instant.ofEpochMilli(summary.getTimeFinished())).toString()
				.substring(2)
				.replaceAll("(\\d[HMS])(?!$)", "$1 ")
				.toLowerCase());
		log.info("{}********************************", logID);
		
		
		java.util.List<TestExecutionSummary.Failure> failures = summary.getFailures();
		failures.forEach(failure -> log.error(logID+"Failure", failure.getException()));
	}
	
}