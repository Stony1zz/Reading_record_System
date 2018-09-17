package com.lee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.lee.iframe.UserLoginIFrame;


public class ReadMain extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	//程序入口
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			new UserLoginIFrame();//登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=readcard";
			Connection con = DriverManager.getConnection(url,"lee","8235160");
			System.out.println("数据库连接成功");
			//URL=url;
			con.close();
		}
		catch(Exception e) {
			System.out.println("数据库连接失败\n" + e.toString());
		}
	}
	
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
		iframe.toFront();//窗体顶置
	}
	//主窗体构造方法
	public ReadMain() {
		setTitle("阅读记录管理系统");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(800, 720);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReadMain.class.getResource("/icon/book_200px.png")));
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/icon/backlmg.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
		//设置窗体居中
		this.setLocationRelativeTo(null);
		
		
	}
	
	//创建菜单栏
	public JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu basicDataMaintenance = new JMenu("基础数据维护");
		basicDataMaintenance.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/data_16px.png")));
		menuBar.add(basicDataMaintenance);
		
		JMenu readerInformationManagement = new JMenu("读者信息管理");
		readerInformationManagement.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/read_16px.png")));
		basicDataMaintenance.add(readerInformationManagement);
		
		JMenuItem readerInformationAdd = new JMenuItem("读者信息添加");
		readerInformationAdd.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/add user_16px.png")));
		readerInformationManagement.add(readerInformationAdd);
		readerInformationAdd.addActionListener(MenuActions.READER_ADD);
		
		JMenuItem readerInformationSetting = new JMenuItem("读者信息修改与删除");
		readerInformationSetting.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/change_16px.png")));
		readerInformationManagement.add(readerInformationSetting);
		readerInformationSetting.addActionListener(MenuActions.READER_MODIFY);
		
		JMenu bookTypeManagement = new JMenu("阅读记录管理");
		bookTypeManagement.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/type_16px.png")));
		basicDataMaintenance.add(bookTypeManagement);
		
		JMenuItem bookTypeAdd = new JMenuItem("阅读记录添加");
		bookTypeAdd.addActionListener(MenuActions.BOOKSTYLE_ADD);
		bookTypeAdd.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/add_16px.png")));
		bookTypeManagement.add(bookTypeAdd);
		
		JMenuItem bookTypeModify = new JMenuItem("阅读记录更新");
		bookTypeModify.addActionListener(MenuActions.BOOKTYPE_MODIFY);
		bookTypeModify.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/change_16px.png")));
		bookTypeManagement.add(bookTypeModify);
		
		JMenu bookInformationManagement = new JMenu("图书信息管理");
		bookInformationManagement.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/toop_book_add_16px.png")));
		basicDataMaintenance.add(bookInformationManagement);
		
		JMenuItem bookInformationAdd = new JMenuItem("图书信息添加");
		bookInformationAdd.addActionListener(MenuActions.BOOK_ADD);
		bookInformationAdd.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/add_16px.png")));
		bookInformationManagement.add(bookInformationAdd);
		
		JMenuItem bookInformationModify = new JMenuItem("图书信息修改与删除");
		bookInformationModify.addActionListener(MenuActions.BOOK_MODIFY);
		bookInformationModify.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/change_16px.png")));
		bookInformationManagement.add(bookInformationModify);
		
		JMenuItem safeExitSystem = new JMenuItem("安全退出");
		safeExitSystem.addActionListener(MenuActions.EXIT);
		safeExitSystem.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/logoff_16px.png")));
		basicDataMaintenance.add(safeExitSystem);
		
		JMenu bookBorrowManagement = new JMenu("查询管理");
		bookBorrowManagement.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/read_16px.png")));
		menuBar.add(bookBorrowManagement);
		
		JMenuItem bookSearch = new JMenuItem("图书信息搜索");
		bookSearch.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/toop_book_add_16px.png")));
		bookSearch.addActionListener(MenuActions.BOOK_SEARCH);
		bookBorrowManagement.add(bookSearch);

		JMenuItem ReaderSearch = new JMenuItem("读者信息搜索");
		ReaderSearch.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/read_16px.png")));
		ReaderSearch.addActionListener(MenuActions.READER_SEARCH);
		bookBorrowManagement.add(ReaderSearch);

		JMenuItem ReadSearch = new JMenuItem("阅读记录搜索");
		ReadSearch.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/type_16px.png")));
		ReadSearch.addActionListener(MenuActions.READ_SEARCH);
		bookBorrowManagement.add(ReadSearch);

		JMenuItem HotReadSearch = new JMenuItem("阅读总结展示");
		HotReadSearch.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/Book_Search_16px.png")));
		HotReadSearch.addActionListener(MenuActions.HOTREAD_SEARCH);
		bookBorrowManagement.add(HotReadSearch);

		JMenu systemMaintenance = new JMenu("系统维护");
		systemMaintenance.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/settings_16px.png")));
		menuBar.add(systemMaintenance);
		
		JMenuItem modifyPassword = new JMenuItem("更改口令");
		modifyPassword.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/change_password_16px.png")));
		modifyPassword.addActionListener(MenuActions.MODIFY_PASSWORD);
		systemMaintenance.add(modifyPassword);

		JMenuItem adminUserAdd = new JMenuItem("用户添加");
		adminUserAdd.setIcon(new ImageIcon(ReadMain.class.getResource("/icon/User_add_16px.png")));
		adminUserAdd.addActionListener(MenuActions.USER_ADD);
		systemMaintenance.add(adminUserAdd);

		return menuBar;
	}
	
	//创建工具栏
	private JToolBar createToolBar(){
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(SystemColor.controlHighlight);
		toolBar.setFloatable(false);

		return toolBar;
	}
}
