package com.lee.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.lee.dao.AdminDao;
import com.lee.model.Admin;
//import com.lee.util.MD5Util;

/**
* @author James
* @version 创建时间：2018年6月20日 
* 修改密码窗体
*/
@SuppressWarnings("serial")
public class UserChangePasswordIFrame extends JInternalFrame {
	private JTextField userNameTxt;
	private JPasswordField oldPasswordTxt;
	private JPasswordField newPasswordTxt;
	private JPasswordField verifyNewPasswordTxt;
	private JButton modifyButton;
	private JButton resetButton;
	private Admin userName = UserLoginIFrame.getUser();

	public UserChangePasswordIFrame() {
		super();
		setTitle("修改密码");
		setFrameIcon(new ImageIcon(UserChangePasswordIFrame.class.getResource("/icon/change_password_16px.png")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 300, 270);


		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("登  录  名：");
		
		JLabel label = new JLabel("旧  密  码：");

		JLabel label_1 = new JLabel("新  密  码：");
		
		JLabel label_2 = new JLabel("再 次 确 认：");
		
		userNameTxt = new JTextField();
		userNameTxt.setText(userName.getSys_name());
		userNameTxt.setColumns(10);
		userNameTxt.setEditable(false);
		
		oldPasswordTxt = new JPasswordField();
		oldPasswordTxt.setColumns(10);
		oldPasswordTxt.setEchoChar('*');//设置密码框的回显字符
		
		newPasswordTxt = new JPasswordField();
		newPasswordTxt.setColumns(10);
		newPasswordTxt.setEchoChar('*');//设置密码框的回显字符
		
		verifyNewPasswordTxt = new JPasswordField();
		verifyNewPasswordTxt.setColumns(10);
		verifyNewPasswordTxt.setEchoChar('*');//设置密码框的回显字符
		verifyNewPasswordTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					modifyButton.doClick();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(oldPasswordTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPasswordTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(verifyNewPasswordTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(oldPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(newPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(verifyNewPasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panelButton = new JPanel();
		getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String oldPassword =new String(oldPasswordTxt.getPassword());
				String newPassword =new String(newPasswordTxt.getPassword());
				String verifyNewPassword =new String(verifyNewPasswordTxt.getPassword());
				String oldPasswordMD5 = oldPassword;
				String newPasswordMD5 = newPassword;
				String verifyNewPasswordMD5 = verifyNewPassword;
				System.out.println("旧密码："+oldPasswordMD5+" 新密码1："+newPasswordMD5+" 新密码2："+verifyNewPasswordMD5);
				System.out.println(userName.getSys_password());
				if(!oldPasswordMD5.equals(userName.getSys_password())) {
					if(newPasswordMD5.equals(verifyNewPasswordMD5)) {
						userName.setSys_password(newPasswordMD5);
						int i = AdminDao.updatePassword(userName.getSys_password(),userName.getSys_name());
						if(i == 1) {
							oldPasswordTxt.setText(null);
							newPasswordTxt.setText(null);
							verifyNewPasswordTxt.setText(null);
							JOptionPane.showMessageDialog(getContentPane(), "密码修改成功。");
							doDefaultCloseAction();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "密码修改失败。");
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "两次输入的密码不一致，请重新输入。");
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "旧密码输入错误，请确认密码。");
				}
			}
		});
		modifyButton.setIcon(new ImageIcon(UserChangePasswordIFrame.class.getResource("/icon/change_16px.png")));
		panelButton.add(modifyButton);
		
		resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				oldPasswordTxt.setText(null);
				newPasswordTxt.setText(null);
				verifyNewPasswordTxt.setText(null);
			}
		});
		resetButton.setIcon(new ImageIcon(UserChangePasswordIFrame.class.getResource("/icon/reset_button_16px.png")));
		panelButton.add(resetButton);
		
		//设置窗体可见
		this.setVisible(true);
	}
}
