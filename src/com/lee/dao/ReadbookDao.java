package com.lee.dao;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.lee.model.Book;
import com.lee.model.Read;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault;
import com.lee.model.ReadersInfo;
import com.lee.util.DbHelper;

public class ReadbookDao {
    //添加阅读信息操作
    public static int insertRead(String Uno, String Bno,float Rpage) {
        int i = 0;
        float Rpecent=0;
        Connection conn = null;
        try{
            List<Book> list = new ArrayList<Book>();
            conn = DbHelper.getConnection();
            Rpecent=Rpage;
            String sql1 = "select * from Book where Bno ='" + Bno + "'";
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql1);
            while (re.next()) {
                Book Read = new Book();
                Read.setBpage(re.getString("Bpage"));
                list.add(Read);
                System.out.println(Float.parseFloat(Read.getBpage()));
                System.out.println(Rpage/Float.parseFloat(Read.getBpage()));
                Rpecent=( Rpage/Float.parseFloat(Read.getBpage()));
                System.out.println(Rpecent);
            }

            String sql="insert into Readbook(Uno,Bno,Rpage,Rpecent) values"
                    + "('"+Uno+"','"+Bno+"','"+Rpage+"','"+Rpecent+"')";

            System.out.println(sql);
            System.out.println(sql1);
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
    public static List<Read> selectUname(String Uname) {
        List<Read> list = new ArrayList<Read>();
        String sql = "select * from Read_Card where Uname ='" + Uname + "'";
        Connection conn = null;
        try {
            conn = DbHelper.getConnection();
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
            while (re.next()) {
                Read Read = new Read();
                Read.setUno(re.getString("Uno"));
                Read.setUname(re.getString("Uname"));
                Read.setBno(re.getString("Bno"));
                Read.setBname(re.getString("Bname"));
                Read.setRpage(re.getFloat("Rpage"));
                Read.setRpecent(re.getFloat("Rpecent"));
                list.add(Read);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DbHelper.close(conn);
            JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
        }
        DbHelper.close(conn);
        return list;
    }

//根据读书获取读者信息
    public static List<Read> selectBname(String Bname) {
        List<Read> list = new ArrayList<Read>();
        String sql = "select * from Read_Card where Bname ='" + Bname + "'";
        Connection conn = null;
        try {
            conn = DbHelper.getConnection();
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
            while (re.next()) {
                Read Read = new Read();
                Read.setUno(re.getString("Uno"));
                Read.setUname(re.getString("Uname"));
                Read.setBno(re.getString("Bno"));
                Read.setBname(re.getString("Bname"));
                Read.setRpage(re.getFloat("Rpage"));
                Read.setRpecent(re.getFloat("Rpecent"));
                list.add(Read);
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
    public static List<Read> selectRead() {
        List<Read> list = new ArrayList<Read>();
        Connection conn = null;
        try {
            conn = DbHelper.getConnection();
            String sql = "select * from Read_Card";
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
            while (re.next()) {
                Read Read = new Read();
                Read.setUno(re.getString("Uno"));
                Read.setUname(re.getString("Uname"));
                Read.setBno(re.getString("Bno"));
                Read.setBname(re.getString("Bname"));
                Read.setRpage(re.getFloat("Rpage"));
                Read.setRpecent(re.getFloat("Rpecent"));
                list.add(Read);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DbHelper.close(conn);
            JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
        }
        DbHelper.close(conn);
        return list;
    }
    //获取图书热度信息
    public static List<Read> Readhot() {
        List<Read> list = new ArrayList<Read>();
        Connection conn = null;
        try {
            conn = DbHelper.getConnection();
            String sql = "select * from Hotbook";
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
            while (re.next()) {
                Read Read = new Read();
                Read.setBname(re.getString("Bname"));
                Read.setRpiont(re.getFloat("Bpiont"));
                list.add(Read);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DbHelper.close(conn);
            JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
        }
        DbHelper.close(conn);
        return list;
    }

    //获取读者阅读记录
    public static List<Read> Readerhard() {
        List<Read> list = new ArrayList<Read>();
        Connection conn = null;
        try {
            conn = DbHelper.getConnection();
            String sql = "select * from HardReader";
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
            while (re.next()) {
                Read Read = new Read();
                Read.setUname(re.getString("Uname"));
                Read.setRpiont(re.getFloat("Bpiont"));
                list.add(Read);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DbHelper.close(conn);
            JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
        }
        DbHelper.close(conn);
        return list;
    }
    //更新读者数据
    public static int updateReader(String Uno, String Bno,float Rpage) {
        int i = 0;
        float Rpecent;
        Connection conn = null;
        try {
            List<Book> list = new ArrayList<Book>();
            conn = DbHelper.getConnection();
            Rpecent=Rpage;
            String sql1 = "select * from Book where Bno ='" + Bno + "'";
            ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql1);
            while (re.next()) {
                Book Read = new Book();
                Read.setBpage(re.getString("Bpage"));
                list.add(Read);
                System.out.println(Float.parseFloat(Read.getBpage()));
                System.out.println(Rpage/Float.parseFloat(Read.getBpage()));
                Rpecent=( Rpage/Float.parseFloat(Read.getBpage()));
                System.out.println(Rpecent);
            }
            String sql = "update Readbook set Uno='"+Uno+"',Bno='"+Bno+"',Rpage='"+Rpage+"',Rpecent='"+Rpecent+"'where Uno='"+Uno+"'AND Bno='"+Bno+"'";
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
