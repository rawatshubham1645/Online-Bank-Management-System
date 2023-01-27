package com.masai.Model;

public class Accountant {
	
	
	private String aname;
	private String aemail;
	private String apassword;
	public Accountant(String name, String email, String password) {
		super();
		this.aname = name;
		this.aemail = email;
		this.apassword = password;
	}
	public Accountant() {
		super();
	}

	public String getAname() {
		return aname;
	}
	public void setAname(String name) {
		this.aname = name;
	}
	public String getAemail() {
		return aemail;
	}
	public void setAemail(String email) {
		this.aemail = email;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String password) {
		this.apassword = password;
	}
	
	@Override
	public String toString() {
		return "Accountant [name=" + aname + ", email=" + aemail + ", password=" + apassword + "]";
	}
	
}
