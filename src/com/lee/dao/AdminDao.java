package com.lee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.lee.model.Admin;
import com.lee.util.DbHelper;

public class AdminDao {

	//登录数据操作
	public static Admin login(Connection conn, Admin admin) throws Exception {
		Admin resultAdmin=null;
		String sql="select * from sys_admin where sys_name=? and sys_password=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, admin.getSys_name());
		pstmt.setString(2, admin.getSys_password());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){

			resultAdmin=new Admin();
			resultAdmin.setSys_name(rs.getString("sys_name"));
			resultAdmin.setSys_password(rs.getString("sys_password"));
		}
		return resultAdmin;
	}

	//更新密码操作
	public static int updatePassword(String sys_password, String sys_name) {
		int i = 0;
		String sql = "update sys_admin set sys_password='"+sys_password+"' where sys_name='"+sys_name+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i=conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}

	//添加管理员信息
	public static int insertAdmin( String userName, String passwordMD5) {
		int i = 0;
		String sql = "insert into sys_admin(sys_name,sys_password) values ('"+userName+"','"+passwordMD5+"')";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			System.out.println(sql);
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
