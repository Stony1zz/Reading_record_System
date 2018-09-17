package com.lee.model;

import java.sql.Date;

/**
* @author James
* @version 创建时间：2018年6月20日 
* 读者信息模型
*/
public class ReadersInfo {
	private String Uname;
	private String Uno;
	private String Usex;
	private int Uage;
	private Date regdate;

	public void setUage(int uage) {
		Uage = uage;
	}

	public void setUsex(String usex) {
		Usex = usex;
	}

	public void setUno(String uno) {
		Uno = uno;
	}

	public void setUname(String uname) {
		Uname = uname;
	}

	public String getUsex() {
		return Usex;
	}

	public int getUage() {
		return Uage;
	}

	public String getUno() {
		return Uno;
	}

	public String getUname() {
		return Uname;
	}

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
