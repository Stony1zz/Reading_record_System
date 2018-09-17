package com.lee.iframe;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.lee.dao.BookInfoDao;
import com.lee.model.Book;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
//图书信息添加窗体
public class BookInfoAddIFrame extends JInternalFrame {
	private JTextField BnoTxt;
	private JTextField BnameTxt;
	private JTextField BpageTxt;
	private JTextField BtypeTxt;
	private int getBookStyleNumber(List<?> list){
		//tag等于1时，表示该图书类型编号存在
		int tag = 0;
		Object[] bookStyles = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			Book bookStyle = (Book) list.get(i);
			bookStyles[i] = bookStyle.getBno();
			if(bookStyles[i].equals(BnoTxt.getText())) {
				tag = 1;
			}
		}
		return tag;
	}

	/**
	 * Create the frame.
	 */
	public BookInfoAddIFrame() {
		setTitle("图书信息添加");
		setFrameIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/type_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 500, 480);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/bookAdd.jpg")));
		panel.add(lblNewLabel);

		JPanel panel_Button = new JPanel();
		getContentPane().add(panel_Button, BorderLayout.SOUTH);

		JButton saveButton = new JButton("添加");
		saveButton.addActionListener(new SaveButtonActionListener());
		saveButton.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/save_button_16px.png")));
		panel_Button.add(saveButton);

		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		closeButton.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/logoff_16px.png")));
		panel_Button.add(closeButton);

		JPanel panel_Info = new JPanel();
		getContentPane().add(panel_Info, BorderLayout.CENTER);

		JLabel BnoLabel = new JLabel("图书类别编号：");

		JLabel BnameLabel = new JLabel("图书类别名称：");

		JLabel BpageLabel = new JLabel("图书页数：");

		JLabel BtypeLabel = new JLabel("图书类型：");

		BnoTxt = new JTextField();
		BnoTxt.setColumns(10);

		BnameTxt = new JTextField();
		BnameTxt.setColumns(10);

		BpageTxt = new JTextField();
		BpageTxt.setColumns(10);

		BtypeTxt = new JTextField();
		BtypeTxt.setColumns(10);
		GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
		gl_panel_Info.setHorizontalGroup(
				gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
								.addGap(77)
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(BtypeLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(BtypeTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(BpageLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(BpageTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(BnameLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(BnameTxt))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(BnoLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(BnoTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_panel_Info.setVerticalGroup(
				gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
										.addComponent(BnoLabel)
										.addComponent(BnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(26)
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
										.addComponent(BnameLabel)
										.addComponent(BnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(31)
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
										.addComponent(BpageLabel)
										.addComponent(BpageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(27)
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
										.addComponent(BtypeLabel)
										.addComponent(BtypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(48, Short.MAX_VALUE))
		);
		panel_Info.setLayout(gl_panel_Info);

		//设置窗体可见
		this.setVisible(true);
	}

	//保存按钮监听事件
	class SaveButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String Bno =  BnoTxt.getText();
			String Bname = BnameTxt.getText();
			String Bpage = BpageTxt.getText();
			String Btype = BtypeTxt.getText();
			if(getBookStyleNumber(BookInfoDao.selectBookInfo()) == 1) {
				JOptionPane.showMessageDialog(null, "该图书编号已存在，请重新输入。");
				return;
			}

			if(Bno.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书编号不能为空。");
				return;
			}
			if(Bname.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书名称不能为空。");
				return;
			}
			if(Bpage.length() == 0) {
				JOptionPane.showMessageDialog(null, "页数不能为空。");
				return;
			}
			if(Btype.length() == 0) {
				JOptionPane.showMessageDialog(null, "类型不能为空。");
				return;
			}
			int i = BookInfoDao.insertBookInfo(Bno,Bname,Bpage,Btype);
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功。");
				BnoTxt.setText(null);
				BnameTxt.setText(null);
				BpageTxt.setText(null);
				BtypeTxt.setText(null);
			}
		}
	}
}
