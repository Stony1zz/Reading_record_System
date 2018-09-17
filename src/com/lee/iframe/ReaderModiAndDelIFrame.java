package com.lee.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.lee.dao.ReaderInfoDao;
import com.lee.model.ReadersInfo;
import com.lee.util.MyDocument;

//读者信息修改与查询
@SuppressWarnings("serial")
public class ReaderModiAndDelIFrame extends JInternalFrame {

	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private String[] columnNames = {"读者姓名 ", "读者性别", "读者年龄", "读者编号", "注册日期"};
	private JTextField nameTxt;
	private JTextField ageTxt;
	private JTextField NoTxt;
	private JFormattedTextField regdateTxt;

	private JRadioButton JRadioButton1;
	private JRadioButton JRadioButton2;

	@SuppressWarnings({"static-access", "rawtypes", "unchecked"})
	public ReaderModiAndDelIFrame() {
		super();
		setTitle("\u8BFB\u8005\u4FE1\u606F\u4FEE\u6539\u4E0E\u5220\u9664");
		setIconifiable(true);
		setFrameIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		setClosable(true);
		setBounds(100, 100, 600, 550);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/readerModiAndDel.jpg")));
		getContentPane().add(label, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 100));

		DefaultTableModel model = new DefaultTableModel();
		Object[][] results = getFileStates(ReaderInfoDao.selectReader());
		model.setDataVector(results, columnNames);

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new TableListener());

		JPanel panel_Info = new JPanel();

		JPanel panel_button = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
						.addComponent(panel_Info, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
								.addContainerGap())
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_Info, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addGap(143))
		);

		JButton button_modify = new JButton("\u4FEE\u6539");
		button_modify.setIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		button_modify.addActionListener(new modifyButtonListener(model));

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.setIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/delete_16px.png")));
		button_delete.addActionListener(new DelButtonListener(model));

		GroupLayout gl_panel_button = new GroupLayout(panel_button);
		gl_panel_button.setHorizontalGroup(
				gl_panel_button.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_button.createSequentialGroup()
								.addGap(133)
								.addComponent(button_modify)
								.addGap(142)
								.addComponent(button_delete, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(155, Short.MAX_VALUE))
		);
		gl_panel_button.setVerticalGroup(
				gl_panel_button.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_button.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_button.createParallelGroup(Alignment.LEADING)
										.addComponent(button_delete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addComponent(button_modify, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
								.addContainerGap())
		);
		panel_button.setLayout(gl_panel_button);

		JLabel nameLabel = new JLabel("姓        名：");

		JLabel sexLabel = new JLabel("性        别：");

		JLabel label_1 = new JLabel("年        龄：");

		JLabel label_3 = new JLabel("学        号：");

		JLabel label_4 = new JLabel("日        期：");

		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		ageTxt = new JTextField();
		ageTxt.setDocument(new MyDocument(2));//控制该文本域最大输入两位数
		ageTxt.addKeyListener(new NumberListener());
		NoTxt = new JTextField();
		NoTxt.setColumns(10);
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
		regdateTxt = new JFormattedTextField(myfmt.getDateInstance());
		regdateTxt.setColumns(20);

		JPanel panel_radioButton = new JPanel();

		JRadioButton1 = new JRadioButton("男");
		buttonGroup.add(JRadioButton1);
		panel_radioButton.add(JRadioButton1);

		JRadioButton2 = new JRadioButton("女");
		buttonGroup.add(JRadioButton2);
		panel_radioButton.add(JRadioButton2);
		GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
		gl_panel_Info.setHorizontalGroup(
				gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
								.addGap(30)
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(nameLabel)
												.addGap(10)
												.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
												.addGap(50)
												.addComponent(sexLabel)
												.addGap(18)
												.addComponent(panel_radioButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(label_1)
												.addGap(10)
												.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
												.addGap(50)
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(NoTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
												.addGap(20)
										)))
		);
		gl_panel_Info.setVerticalGroup(
				gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addGap(3)
												.addComponent(nameLabel))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addGap(3)
												.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addGap(3)
												.addComponent(sexLabel))
										.addComponent(panel_radioButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addGap(3)
												.addComponent(label_1))
										.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_Info.createSequentialGroup()
												.addGap(3)
												.addComponent(label_3))
										.addComponent(NoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_panel_Info.createSequentialGroup()
												.addGap(3)
												.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
														.addComponent(label_4)
                                                        .addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addContainerGap(26, Short.MAX_VALUE)
                                                )

						)
		);
		panel_Info.setLayout(gl_panel_Info);
		panel.setLayout(gl_panel);

		//设置窗体可见
		this.setVisible(true);
	}

	//向表格中加载数据
	private Object[][] getFileStates(List<?> list) {
		Object[][] results = new Object[list.size()][columnNames.length];
		for(int i = 0; i < list.size(); i++) {
			ReadersInfo readerInfo=(ReadersInfo)list.get(i);
			results[i][0]=readerInfo.getUname();
			results[i][1]=readerInfo.getUsex();
			results[i][2]=readerInfo.getUage();
			results[i][3]=readerInfo.getUno();
			results[i][4]=readerInfo.getRegdate();

		}
		return results;
	}

	//表格数据点击事件
	private final class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			nameTxt.setText(table.getValueAt(selRow, 0).toString().trim());
			if(table.getValueAt(selRow, 1).toString().trim().equals("男")) {
				JRadioButton1.setSelected(true);
			}else {
				JRadioButton2.setSelected(true);
			}
			ageTxt.setText(table.getValueAt(selRow, 2).toString().trim());
			NoTxt.setText(table.getValueAt(selRow, 3).toString().trim());
			regdateTxt.setText(table.getValueAt(selRow,4 ).toString().trim());

		}
	}

	//修改按钮事件监听
	private final class modifyButtonListener implements ActionListener{
		private final DefaultTableModel model;

		private modifyButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		@Override
		public void actionPerformed(final ActionEvent ae) {
			if(nameTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者姓名不能为空！");
				return;
			}
			if(ageTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者年龄不能为空！");
				return;
			}
			if(NoTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者编号不能为空！");
				return;
			}
			if(regdateTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2017-09-22\"格式");
				return;
			}
			//sex等于1为男生，sex等于2为女生
			String sex = "男";
			if(!JRadioButton1.isSelected()) {
				sex = "女";
			}
//			updateReader(String Uname, String Uno,String Usex, int Uage, Date registrationDate) {
//			System.out.println(id + nameTxt.getText()+sex.trim()+ageTxt.getText().trim()+occupationTxt.getText().trim()+validDocumentation.getSelectedIndex()+idNumberTxt.getText().trim()+telNumberTxt.getText().trim()+depositTxt.getText().trim()+regdateTxt.getText().trim()+readerNumTxt.getText().trim());
			int i = ReaderInfoDao.updateReader(nameTxt.getText().trim(),NoTxt.getText().trim(),sex.trim(), Integer.parseInt(ageTxt.getText().trim()),Date.valueOf(regdateTxt.getText().trim()));

			if(i == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				Object[][] results=getFileStates(ReaderInfoDao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	//删除按钮监听事件
	private final class DelButtonListener implements ActionListener{
		private final DefaultTableModel model;

		private DelButtonListener(DefaultTableModel model) {
			this.model = model;
		}
		@Override
		public void actionPerformed(final ActionEvent ae) {
			int i = ReaderInfoDao.deleteReader(NoTxt.getText().trim());
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				Object[][] results=getFileStates(ReaderInfoDao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}

	}

	//日期格式监听
	public class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(regdateTxt.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2017-09-22\"格式");
			}
		}
	}

	//数字监听事件
	private final class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
}
