package org.white_sdev.templates.white_template.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.white_sdev.templates.white_template.controller.MainController;

import javax.swing.table.DefaultTableModel;

@Slf4j
@Component
@SuppressWarnings("all")
public class UserFrame extends javax.swing.JFrame implements InitializingBean {
	
	@Autowired
	MainController mainController;
	
	
	public UserFrame() {
		initComponents();
	}
	
	@Override
	public void afterPropertiesSet() {
		mainController.setView(this);
		mainController.loadUsers();
	}
	
	public javax.swing.JTable getUserTable() {
		return userTable;
	}
	
	public javax.swing.JTextField getFirstNameTextField() {
		return firstNameTextField;
	}
	
	public javax.swing.JTextField getEmailTextField() {
		return emailTextField;
	}
	
	
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		
		jPanel1 = new javax.swing.JPanel();
		refreshButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		userTable = new javax.swing.JTable();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		firstNameTextField = new javax.swing.JTextField();
		emailTextField = new javax.swing.JTextField();
		DeleteButton = new javax.swing.JButton();
		saveButton = new javax.swing.JButton();
		clearButton = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		executeTestsJButton = new javax.swing.JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		refreshButton.setText("⟳ synchronize");
		refreshButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshButtonActionPerformed(evt);
			}
		});
		
		userTable.setModel(new DefaultTableModel());
		userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		userTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				userTableMouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(userTable);
		
		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));
		
		jLabel1.setText("First Name:");
		
		jLabel2.setText("Email:");
		
		DeleteButton.setText("Delete");
		DeleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteButtonActionPerformed(evt);
			}
		});
		
		saveButton.setText("Save");
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});
		
		clearButton.setText("Clear");
		clearButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearButtonActionPerformed(evt);
			}
		});
		
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
										  .addContainerGap()
										  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
															.addGroup(jPanel2Layout.createSequentialGroup()
																			  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(jLabel1)
																								.addComponent(jLabel2))
																			  .addGap(18, 18, 18)
																			  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																														  false)
																								.addComponent(emailTextField,
																											  javax.swing.GroupLayout.DEFAULT_SIZE,
																											  124,
																											  Short.MAX_VALUE)
																								.addComponent(firstNameTextField)))
															.addGroup(jPanel2Layout.createSequentialGroup()
																			  .addComponent(saveButton)
																			  .addGap(12, 12, 12)
																			  .addComponent(clearButton)
																			  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																			  .addComponent(DeleteButton)))
										  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
										  .addContainerGap()
										  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
															.addComponent(jLabel1)
															.addComponent(firstNameTextField,
																		  javax.swing.GroupLayout.PREFERRED_SIZE,
																		  javax.swing.GroupLayout.DEFAULT_SIZE,
																		  javax.swing.GroupLayout.PREFERRED_SIZE))
										  .addGap(18, 18, 18)
										  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
															.addComponent(jLabel2)
															.addComponent(emailTextField,
																		  javax.swing.GroupLayout.PREFERRED_SIZE,
																		  javax.swing.GroupLayout.DEFAULT_SIZE,
																		  javax.swing.GroupLayout.PREFERRED_SIZE))
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
										  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
															.addComponent(DeleteButton)
															.addComponent(saveButton)
															.addComponent(clearButton))
										  .addGap(12, 12, 12))
		);
		
		executeTestsJButton.setText("Execute All Tests");
		executeTestsJButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				executeTestsJButtonActionPerformed(evt);
			}
		});
		
		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup()
										  .addGap(48, 48, 48)
										  .addComponent(executeTestsJButton)
										  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel3Layout.setVerticalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup()
										  .addContainerGap()
										  .addComponent(executeTestsJButton)
										  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
										  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
															.addGroup(jPanel1Layout.createSequentialGroup()
																			  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addGroup(jPanel1Layout.createSequentialGroup()
																												  .addContainerGap()
																												  .addComponent(jScrollPane2,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																224,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addGroup(jPanel1Layout.createSequentialGroup()
																												  .addGap(35, 35, 35)
																												  .addComponent(refreshButton,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																167,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addGroup(jPanel1Layout.createSequentialGroup()
																												  .addContainerGap()
																												  .addComponent(jPanel2,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																			  .addGap(0, 0, Short.MAX_VALUE))
															.addGroup(jPanel1Layout.createSequentialGroup()
																			  .addContainerGap()
																			  .addComponent(jPanel3,
																							javax.swing.GroupLayout.DEFAULT_SIZE,
																							javax.swing.GroupLayout.DEFAULT_SIZE,
																							Short.MAX_VALUE)))
										  .addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
										  .addContainerGap()
										  .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										  .addComponent(refreshButton)
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										  .addComponent(jPanel2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
										  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										  .addComponent(jPanel3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
										  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
										  .addContainerGap()
										  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										  .addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
										  .addContainerGap()
										  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										  .addContainerGap())
		);
		
		pack();
	}// </editor-fold>//GEN-END:initComponents
	
	private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
		mainController.rowSelected();
	}//GEN-LAST:event_userTableMouseClicked
	
	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
		mainController.save();
	}//GEN-LAST:event_saveButtonActionPerformed
	
	private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
		mainController.clear();
	}//GEN-LAST:event_clearButtonActionPerformed
	
	private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
		mainController.delete();
	}//GEN-LAST:event_DeleteButtonActionPerformed
	
	private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
		mainController.loadUsers();
	}//GEN-LAST:event_refreshButtonActionPerformed
	
	private void executeTestsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeTestsJButtonActionPerformed
		mainController.executeTests();
	}//GEN-LAST:event_executeTestsJButtonActionPerformed
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton DeleteButton;
	private javax.swing.JButton clearButton;
	private javax.swing.JTextField emailTextField;
	private javax.swing.JButton executeTestsJButton;
	private javax.swing.JTextField firstNameTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton refreshButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JTable userTable;
	// End of variables declaration//GEN-END:variables
}
