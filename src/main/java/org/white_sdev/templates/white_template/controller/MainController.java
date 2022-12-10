package org.white_sdev.templates.white_template.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.white_sdev.templates.white_template.model.User;
import org.white_sdev.templates.white_template.view.UserFrame;

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
		return new ArrayList<>(users);
	}
	
	@PostMapping("/user")
	public boolean newUser(@RequestBody User user) {
		String logID = "::createUser(user): ";
		log.info("{}Creating User - user:{}", logID, user);
		try {
			create(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	Set<User> users = new HashSet<>() {{
		add(new User("foo", "foo@dummy.com"));
		add(new User("bar", "bar@dummy.com"));
	}};
	
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
		if(create(user))
			javax.swing.JOptionPane.showMessageDialog(view, "User Saved");
		else
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
		if (users.remove(user)) javax.swing.JOptionPane.showMessageDialog(view, "User deleted");
		else javax.swing.JOptionPane.showMessageDialog(view, "Impossible to delete user:" + user);
		clear();
		loadUsers();
	}
	
	public static class TableUtils {
		public static void loadData(JTable table, List<User> users) {
			DefaultTableModel userModel = (DefaultTableModel) table.getModel();
			userModel.setDataVector(User.mapUsers(users), new String[]{"First Name", "Email"});
		}
	}
}