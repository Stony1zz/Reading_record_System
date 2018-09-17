package com.lee;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.lee.iframe.*;

public class MenuActions {

	private static Map<String, JInternalFrame> frames; // 子窗体集合
	
	public static PasswordModiAction MODIFY_PASSWORD;
	public static ReaderAddAction READER_ADD;
	public static ReaderModiAction READER_MODIFY;
	public static BookTypeAddAction BOOKSTYLE_ADD;
	public static BookTypeModiAction BOOKTYPE_MODIFY;
	public static BookInfoAddAction BOOK_ADD;
	public static BookModiAction BOOK_MODIFY;
	public static BookSearchAction BOOK_SEARCH;
	public static ReadSearchAction READ_SEARCH;
	public static HotReadSearchAction HOTREAD_SEARCH;
	public static ReaderSearchAction READER_SEARCH;
	public static UserAddAction USER_ADD;
	public static ExitAction EXIT;
	
	static {
		frames = new HashMap<String, JInternalFrame>();
		MODIFY_PASSWORD = new PasswordModiAction();
		READER_ADD = new ReaderAddAction();
		READER_MODIFY = new ReaderModiAction();
		BOOKSTYLE_ADD = new BookTypeAddAction();
		BOOKTYPE_MODIFY = new BookTypeModiAction();
		HOTREAD_SEARCH = new HotReadSearchAction();
		BOOK_ADD = new BookInfoAddAction();
		READ_SEARCH=new ReadSearchAction();
		BOOK_MODIFY = new BookModiAction();
		BOOK_SEARCH = new BookSearchAction();
		READER_SEARCH=new ReaderSearchAction();
		USER_ADD = new UserAddAction();
		EXIT = new ExitAction();
	}


	private static class BookSearchAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书查询")||frames.get("图书查询").isClosed()) {
				BookSearchIFrame iframe = new BookSearchIFrame();
				frames.put("图书查询", iframe);
				ReadMain.addIFame(iframe);
			}
		}
	}
	@SuppressWarnings("serial")
	private static class ReaderSearchAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("读者查询")||frames.get("读者查询").isClosed()) {
				ReaderSearchIFrame iframe = new ReaderSearchIFrame();
				frames.put("读者查询", iframe);
				ReadMain.addIFame(iframe);
			}
		}
	}
	@SuppressWarnings("serial")
	private static class ReadSearchAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("阅读查询")||frames.get("阅读查询").isClosed()) {
				ReadSearchIFrame iframe = new ReadSearchIFrame();
				frames.put("阅读查询", iframe);
				ReadMain.addIFame(iframe);
			}
		}
	}

	private static class HotReadSearchAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("阅读记录查询")||frames.get("阅读记录查询").isClosed()) {
				ReadDataIFrame iframe = new ReadDataIFrame();
				frames.put("阅读记录查询", iframe);
				ReadMain.addIFame(iframe);
			}
		}
	}

	
	@SuppressWarnings("serial")
	private static class BookModiAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息修改与删除")||frames.get("图书信息修改与删除").isClosed()) {
				BookInfoModiAndDelIFrame iframe = new BookInfoModiAndDelIFrame();
				frames.put("图书信息修改与删除", iframe);
				ReadMain.addIFame(iframe);
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookInfoAddAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息添加")||frames.get("图书信息添加").isClosed()) {
				BookInfoAddIFrame iframe = new BookInfoAddIFrame();
				frames.put("图书信息添加", iframe);
				ReadMain.addIFame(frames.get("图书信息添加"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookTypeModiAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("阅读记录更新")||frames.get("阅读记录更新").isClosed()) {
				ReadInfoModiAndDelIFrame iframe=new ReadInfoModiAndDelIFrame();
				frames.put("阅读记录更新", iframe);
				ReadMain.addIFame(frames.get("阅读记录更新"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class BookTypeAddAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("阅读记录添加")||frames.get("阅读记录添加").isClosed()) {
				ReadInfoAddIFrame iframe=new ReadInfoAddIFrame();
				frames.put("阅读记录添加", iframe);
				ReadMain.addIFame(frames.get("阅读记录添加"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class PasswordModiAction extends AbstractAction {
		
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("修改密码")||frames.get("修改密码").isClosed()) {
				UserChangePasswordIFrame iframe=new UserChangePasswordIFrame();
				frames.put("修改密码", iframe);
				ReadMain.addIFame(frames.get("修改密码"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class UserAddAction extends AbstractAction{

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("添加管理员")||frames.get("添加管理员").isClosed()) {
				UserAddIFrame iframe=new UserAddIFrame();
				frames.put("添加管理员", iframe);
				ReadMain.addIFame(frames.get("添加管理员"));
			}
		}
		
	}

	@SuppressWarnings("serial")
	private static class ReaderAddAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("读者信息添加")||frames.get("读者信息添加").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("读者信息添加", iframe);
				ReadMain.addIFame(frames.get("读者信息添加"));
			}
		}
	}
	
	@SuppressWarnings("serial")
	private static class ReaderModiAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			if (!frames.containsKey("读者信息修改与删除")||frames.get("读者信息修改与删除").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("读者信息修改与删除", iframe);
				ReadMain.addIFame(frames.get("读者信息修改与删除"));
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	private static class ExitAction extends AbstractAction{
		public void actionPerformed(final ActionEvent e) {
			int result=JOptionPane.showConfirmDialog(null, "是否退出系统");
			if(result==0){
				System.exit(0);
			}
		}
	}
	private MenuActions() {
		super();
	}
}
