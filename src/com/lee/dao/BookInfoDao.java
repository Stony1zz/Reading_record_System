package com.lee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.lee.model.Book;
import com.lee.util.DbHelper;
public class BookInfoDao {

	//插入图书信息
	public static int insertBookInfo(String Bno, String Bname, String Bpage, String Btype) {
		int i = 0;
		String sql = "insert into Book(Bno,Bname,Bpage,Btype) values('"+Bno+"','"+Bname+"','"+Bpage+"','"+Btype+"')";
		System.out.println(sql);
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//获取图书信息所有数据
	public static List<Book> selectBookInfo() {
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from Book";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				Book bookInfo = new Book();
				bookInfo.setBno(re.getString("Bno"));
				bookInfo.setBname(re.getString("Bname"));
				bookInfo.setBpage(re.getString("Bpage"));
				bookInfo.setBtype(re.getString("Btype"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//根据图书编号获取图书信息所有数据
	public static List<Book> selectBookInfo(String bookId) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from Book where Bno ='"+bookId+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				Book bookInfo = new Book();
				bookInfo.setBno(re.getString("Bno"));
				bookInfo.setBname(re.getString("Bname"));
				bookInfo.setBpage(re.getString("Bpage"));
				bookInfo.setBtype(re.getString("Btype"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
	//根据图书编号获取图书信息
	public static List<Book> selectBookName(String bookName) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select * from Book where Bname ='"+bookName+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				Book bookInfo = new Book();
				bookInfo.setBno(re.getString("Bno"));
				bookInfo.setBname(re.getString("Bname"));
				bookInfo.setBpage(re.getString("Bpage"));
				bookInfo.setBtype(re.getString("Btype"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//更新图书信息
	public static int updateBookInfo(String Bno, String Bname, String Bpage, String Btype){
		int i=0;
		String sql = "update Book set Bno='"+Bno+"',Bname='"+Bname+"',Bpage='"+Bpage+"',Btype='"+Btype+"' where Bno='"+Bno+"'";
		System.out.println(sql);
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			 i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}
	//删除
	public static int deleteBook(String BookNumber) {
		int i = 0;
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql="delete from Book where Bno='"+BookNumber+"'";
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}
}
