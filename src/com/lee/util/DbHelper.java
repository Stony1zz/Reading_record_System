package com.lee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
* @author James
* @version 创建时间：2018年6月20日 
* 数据库操作工具
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作工具
 */
public class DbHelper {
    private static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dbUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=readcard";
    private static String dbUser = "lee";
    private static String dbPassword = "8235160";
    private static DbHelper instance = null;

    private DbHelper() {
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

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    static {
        try {
            Class.forName(dbClassName);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn = null;
        }
    }

    public static void Close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
