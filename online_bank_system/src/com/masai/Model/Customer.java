package com.masai.Model;

public class Customer {
	private int caccno;
	private String cname;
	private int cbalance;
	private String cemail;
	private String cpassword;
	private String cmobile;
	private String caddress;
	
	
	public Customer() {
		super();
	}

	public Customer(int caccno, String cname, int cbalance, String cemail, String cpassword, String cmobile,
			String caddress) {
		super();
		this.caccno = caccno;
		this.cname = cname;
		this.cbalance = cbalance;
		this.cemail = cemail;
		this.cpassword = cpassword;
		this.cmobile = cmobile;
		this.caddress = caddress;
	}
	public int getCaccno() {
		return caccno;
	}
	public void setCaccno(int caccno) {
		this.caccno = caccno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCbalance() {
		return cbalance;
	}
	public void setCbalance(int cbalance) {
		this.cbalance = cbalance;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getCmobile() {
		return cmobile;
	}
	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	
	
	
	@Override
	public String toString() {
		return "Customer [caccno=" + caccno + ", cname=" + cname + ", cbalance=" + cbalance + ", cemail=" + cemail
				+ ", cpassword=" + cpassword + ", cmobile=" + cmobile + ", caddress=" + caddress + "]";
	}
}
