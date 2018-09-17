package com.lee.dao;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault;
import com.lee.model.ReadersInfo;
import com.lee.util.DbHelper;

public class ReaderInfoDao {
	//添加读者信息操作
	public static int insertReader(String Uname, String Uno,String Usex, int Uage, Date registrationDate) {
		int i = 0;
		Connection conn = null;
		try{
			conn = DbHelper.getConnection();
			String sql="insert into Ustudent(Uname,Uno,Usex,Uage,regdate) values"
					+ "('"+Uname+"','"+Uno+"','"+Usex+"','"+Uage+"','"+registrationDate+"')";
			System.out.println(sql);
			i=conn.createStatement().executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}
	//根据学号获取读者信息所有数据
	public static List<ReadersInfo> selectNo(String Uno) {
		List<ReadersInfo> list = new ArrayList<ReadersInfo>();
		String sql = "select * from Ustudent where Uno ='" + Uno + "'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while (re.next()) {
				ReadersInfo ReadersInfo = new ReadersInfo();
				ReadersInfo.setUname(re.getString("Uname"));
				ReadersInfo.setUno(re.getString("Uno"));
				ReadersInfo.setUsex(re.getString("Usex"));
				ReadersInfo.setUage(re.getInt("Uage"));
				ReadersInfo.setRegdate(re.getDate("regdate"));
				list.add(ReadersInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//根据姓名获取读者信息
	public static List<ReadersInfo> selectName(String UName) {
		List<ReadersInfo> list = new ArrayList<ReadersInfo>();
		String sql = "select * from Ustudent where Uname ='" + UName + "'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while (re.next()) {
				ReadersInfo readersInfo = new ReadersInfo();
				readersInfo.setUname(re.getString("Uname"));
				readersInfo.setUno(re.getString("Uno"));
				readersInfo.setUsex(re.getString("Usex"));
				readersInfo.setUage(re.getInt("Uage"));
				readersInfo.setRegdate(re.getDate("regdate"));
				list.add(readersInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
//获取全部读者信息
	public static List<ReadersInfo> selectReader() {
		List<ReadersInfo> list = new ArrayList<ReadersInfo>();
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "select * from Ustudent";
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				ReadersInfo readersInfo = new ReadersInfo();
				readersInfo.setUname(re.getString("Uname"));
				readersInfo.setUno(re.getString("Uno"));
				readersInfo.setUsex(re.getString("Usex"));
				readersInfo.setUage(re.getInt("Uage"));
				readersInfo.setRegdate(re.getDate("regdate"));
				list.add(readersInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

//删除读者信息
	public static int deleteReader(String readerNumber) {
		int i = 0;
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql="delete from Ustudent where Uno='"+readerNumber+"'";
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

//更新读者数据
	public static int updateReader(String Uname, String Uno,String Usex, int Uage, Date registrationDate) {
		int i = 0;
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "update Ustudent set Uname='"+Uname+"',Uno='"+Uno+"',Usex='"+Usex+"',Uage='"+Uage+"',regdate='"+registrationDate+"'where Uno='"+Uno+"'";
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
