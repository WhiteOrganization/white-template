package org.white_sdev.templates.white_template.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.white_sdev.templates.white_template.model.User;
import org.white_sdev.templates.white_template.view.UserFrame;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@Slf4j
@RestController()
@RequestMapping("/api")
public class MainController {
	
	@Setter
	UserFrame view;
	
	public void loadUsers() {
		String logID = "loadUsers()::";
		log.trace("{}Start", logID);
		TableUtils.loadData(view.getUserTable(), getUsers());
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		String logID = "::getUsers(): ";
		log.trace("{}Start ", logID);
		return new ArrayList<>(getUsersSet());
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> newUser(@RequestBody User user) {
		String logID = "::createUser(user): ";
		log.info("{}Creating User - user:{}", logID, user);
		try {
			create(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}
	
	@GetMapping("/tests")
	public void executeTests() {
		String logID="::executeTests([]): ";
		log.trace("{}Start ", logID);
		executeTestsWithLauncher();
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
	 * Some examples: https://www.baeldung.com/junit-tests-run-programmatically-from-java
	 */
	private static void executeTestsWithLauncher(){
		String logID="::executeTestsWithLauncher([]): ";
		log.trace("{}Start ", logID);
		final LauncherDiscoveryRequest request =
				LauncherDiscoveryRequestBuilder.request()
						.selectors(
								selectPackage("org.white_sdev.templates.white_template"),
								selectPackage("org.white_sdev.templates.white_template.controller")
						)
						.filters(
								includeClassNamePatterns(ClassNameFilter.STANDARD_INCLUDE_PATTERN)
						)
						.build();
		
		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();
		
		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);
		
		TestExecutionSummary summary = listener.getSummary();
		
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
	
	
	Set<User> users = new HashSet<>() {{
		add(new User("foo", "foo@dummy.com"));
		add(new User("bar", "bar@dummy.com"));
	}};
	
	public Set<User> getUsersSet(){
		return users;
	}
	
	public boolean create(User user) {
		return users.add(user);
	}
	
	public void rowSelected() {
		selectedUser = getSelectedUser();
		view.getFirstNameTextField().setText(selectedUser.getFirstName());
		view.getEmailTextField().setText(selectedUser.getEmail());
	}
	
	public User getSelectedUser() {
		try {
			int selectedRow = view.getUserTable().getSelectedRow();
			return new User(view.getUserTable().getValueAt(selectedRow, 0).toString(), view.getUserTable().getValueAt(selectedRow, 1).toString());
		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
			return null;
		}
	}
	
	User selectedUser;
	
	public void save() {
		try {
			User user = getCreatedUser();
			if (selectedUser == null) {
				save(user);
			} else {
				update(user);
			}
		} catch (Exception ex) {
			javax.swing.JOptionPane.showMessageDialog(view, "There is an error with your user:\n" + ex.getMessage());
		}
	}
	
	public void save(User user) {
		save(user, true);
	}
	
	public void save(User user, boolean showAlert) {
		if(create(user) && showAlert)
			javax.swing.JOptionPane.showMessageDialog(view, "User Saved");
		else if(showAlert)
			javax.swing.JOptionPane.showMessageDialog(view, "User duplicated");
		clear();
		loadUsers();
	}
	
	
	public void update(User updatedUser) {
		for (User user : users) {
			if (user.equals(selectedUser)) {
				user.setFirstName(updatedUser.getFirstName());
				user.setEmail(updatedUser.getEmail());
				break;
			}
		}
		javax.swing.JOptionPane.showMessageDialog(view, "User Updated");
		clear();
		loadUsers();
	}
	
	public User getCreatedUser() {
		return new User(view.getFirstNameTextField().getText(),
						view.getEmailTextField().getText());
	}
	
	public void clear() {
		view.getFirstNameTextField().setText("");
		view.getEmailTextField().setText("");
		selectedUser = null;
	}
	
	public void delete() {
		User user = getSelectedUser();
		if (user == null) {
			javax.swing.JOptionPane.showMessageDialog(view, "Select a user to delete.");
			return;
		}
		if (remove(user)) javax.swing.JOptionPane.showMessageDialog(view, "User deleted");
		else javax.swing.JOptionPane.showMessageDialog(view, "Impossible to delete user:" + user);
		clear();
		loadUsers();
	}
	
	public boolean remove(User user){
		return users.remove(user);
	}
	
	public static class TableUtils {
		public static void loadData(JTable table, List<User> users) {
			DefaultTableModel userModel = (DefaultTableModel) table.getModel();
			userModel.setDataVector(User.mapUsers(users), new String[]{"First Name", "Email"});
		}
	}
}