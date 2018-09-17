package com.lee.iframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.lee.dao.AdminDao;

public class UserAddIFrame extends JInternalFrame {
	private JPasswordField passwordTxt;
	private JTextField userNameTxt;
	private ButtonGroup buttonGroup = new ButtonGroup();

	public UserAddIFrame() {
		setTitle("\u6DFB\u52A0\u7BA1\u7406\u5458");
		setIconifiable(true);
		setFrameIcon(new ImageIcon(UserAddIFrame.class.getResource("/icon/User_add_16px.png")));
		setClosable(true);
		setBounds(100, 100, 380, 415);
		JPanel panel2 = new JPanel();
		getContentPane().add(panel2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/loginbackground.png")));
		panel2.add(lblNewLabel);
		JPanel panel_info = new JPanel();
		getContentPane().add(panel_info, BorderLayout.CENTER);

		JLabel userNameLabel = new JLabel("用户名：");
		JLabel passwordLabel = new JLabel("密  码：");
		
		passwordTxt = new JPasswordField();
		userNameTxt = new JTextField();
		
		JPanel panel = new JPanel();


		GroupLayout groupLayout = new GroupLayout(panel_info);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
									.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(userNameTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
							.addGap(53))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		panel_info.setLayout(groupLayout);
		
		JPanel panel_button = new JPanel();
		getContentPane().add(panel_button, BorderLayout.SOUTH);
		
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordTxt.getPassword());
				String passwordMD5 = password;
				if(userNameTxt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "用户名不能为空。");
					return;
				}
				if(userNameTxt.getText().length() > 10) {
					JOptionPane.showMessageDialog(null, "用户名位数不能超过10位数。");
					return;
				}
				if(passwordMD5.length() == 0) {
					JOptionPane.showMessageDialog(null, "密码不能为空。");
					return;
				}

				int i = AdminDao.insertAdmin(userNameTxt.getText(),passwordMD5);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "添加成功！");
					doDefaultCloseAction();
				}
			}
		});
		saveButton.setIcon(new ImageIcon(UserAddIFrame.class.getResource("/icon/save_button_16px.png")));
		panel_button.add(saveButton);
		
		JButton btnNewButton = new JButton("\u53D6\u6D88");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		btnNewButton.setIcon(new ImageIcon(UserAddIFrame.class.getResource("/icon/cancel_16px.png")));
		panel_button.add(btnNewButton);

		//设置窗体可见
		this.setVisible(true);
	}
}
