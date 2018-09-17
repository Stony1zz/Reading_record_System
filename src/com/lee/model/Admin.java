package com.lee.model;

import java.sql.Date;


public class Admin {
	private String sys_name;
	private String sys_password;
	private Date sys_regdate;
	public Admin() {
		super();
	}
	
	public Admin(String sys_name, String sys_password) {
		super();
		this.sys_name = sys_name;
		this.sys_password = sys_password;
	}

	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getSys_password() {
		return sys_password;
	}
	public void setSys_password(String sys_password) {
		this.sys_password = sys_password;
	}

}
