package com.lee.iframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.lee.dao.BookInfoDao;
import com.lee.model.Book;

//图书信息修改和删除窗体
public class BookInfoModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	private JTextField bookIDTxt;
	private JTextField bookNameTxt;
	private JTextField pageTxt;
	private JTextField typerTxt;
	private String[] columnNames = { "图书编号", "图书名称", "图书页数", "类型" };
	private String Bno,Bname, Bpage,Btype;
	
	//取数据库中图书相关信息放入表格中
	private Object[][] getFileStates(List<?> list){
		
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i = 0; i < list.size(); i++) {
			Book bookInfo = (Book) list.get(i);
			results[i][0] = bookInfo.getBno();
			results[i][1] = bookInfo.getBname();
			results[i][2] = bookInfo.getBpage();
			results[i][3] = bookInfo.getBtype();
		}
		return results;
	}

	/**
	 * Create the frame.
	 */
	public BookInfoModiAndDelIFrame() {
		setTitle("图书信息修改与删除");
		setFrameIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/check_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 603, 550);
		DefaultTableModel model = new DefaultTableModel();
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/bookModiAndDel.jpg")));
		panel.add(label);
		
		JPanel panel_button = new JPanel();
		getContentPane().add(panel_button, BorderLayout.SOUTH);
		
		JButton modifyButton = new JButton("修改");
		modifyButton.addActionListener(new addBookActionListener());
		modifyButton.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		panel_button.add(modifyButton);

		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new DeleteBookActionListener(model));
		deleteButton.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/delete_16px.png")));
		panel_button.add(deleteButton);

		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		cancelButton.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/cancel_16px.png")));
		panel_button.add(cancelButton);
		
		JPanel panel_Info = new JPanel();
		getContentPane().add(panel_Info, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
		gl_panel_Info.setHorizontalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_Info.setVerticalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
		);
		
		JLabel bookIDLabel = new JLabel("图书编号：");
		
		JLabel bookNameLabel = new JLabel("图书名称：");
		
		JLabel pageLabel = new JLabel("页数：");
		
		JLabel typeLabel = new JLabel("图书类型：");
		
		bookIDTxt = new JTextField();
		bookIDTxt.setColumns(10);
		bookIDTxt.setEditable(false);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		pageTxt = new JTextField();
		pageTxt.setColumns(10);
		pageTxt.setEditable(false);
		
		typerTxt = new JTextField();
		typerTxt.setColumns(10);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(bookNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addComponent(pageLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addComponent(typeLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookIDLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
										.addComponent(pageTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
										.addComponent(typerTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookIDLabel))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(bookNameLabel)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(pageLabel)
										.addComponent(pageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(typeLabel)
										.addComponent(typerTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(27, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		Object[][] results = getFileStates(BookInfoDao.selectBookInfo());
		model.setDataVector(results, columnNames);
		table = new JTable(results,columnNames);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());
		
		scrollPane.setViewportView(table);
		panel_Info.setLayout(gl_panel_Info);

		//设置窗体可见
		this.setVisible(true);
	}
	
	//表格点击事件监听
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			Bno = table.getValueAt(selRow, 0).toString().trim();
			Bname = table.getValueAt(selRow, 1).toString().trim();
			Bpage = table.getValueAt(selRow, 2).toString().trim();
			Btype = table.getValueAt(selRow, 3).toString().trim();
			
			bookIDTxt.setText(Bno);
			bookNameTxt.setText(Bname);
			pageTxt.setText(Bpage);
			typerTxt.setText(Btype);
		}
	}
	
	//修改按钮监听事件
	class addBookActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String Bno = bookIDTxt.getText().trim();
			String Bpage=pageTxt.getText().trim();
			String Btype=typerTxt.getText().trim();
			String Bname = bookNameTxt.getText().trim();
			if(Bname.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书名称不能为空。");
				return;
			}
			if(Bno.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书编号不能为空。");
				return;
			}
			if(Bpage.length() == 0) {
				JOptionPane.showMessageDialog(null, "页数不能为空。");
				return;
			}
			if(Btype.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书类型不能为空。");
				return;
			}
			
			int i = BookInfoDao.updateBookInfo(Bno,Bname,Bpage,Btype);
			if(i == 1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(BookInfoDao.selectBookInfo());
				DefaultTableModel model=new DefaultTableModel();
				table.setModel(model);
				model.setDataVector(results, columnNames);
				resertTextFile();
			}
		}
	}
	//删除按钮监听事件
	class DeleteBookActionListener implements ActionListener{
		private final DefaultTableModel model;

		private DeleteBookActionListener(DefaultTableModel model) {
			this.model = model;
		}
		@Override
		public void actionPerformed(final ActionEvent ae) {
			int i = BookInfoDao.deleteBook(bookIDTxt.getText().trim());
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				Object[][] results=getFileStates(BookInfoDao.selectBookInfo());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	
	//清空文本框
	private void resertTextFile() {
		bookIDTxt.setText(null);
		bookNameTxt.setText(null);
		pageTxt.setText(null);
		typerTxt.setText(null);
	}
}
