package com.masai.Dao;

import com.masai.Beam.Accountant;
import com.masai.Beam.Customer;
import com.masai.Exception.AccountException;
import com.masai.Exception.AccountantException;
import com.masai.Exception.CustomerException;

public interface AccountantDao {
	public  Accountant LoginAccountant(String username,String password)throws AccountantException;
	
	public int addCustomer(String cname,String cemail, String cpassword, String cmobile, String caddress) throws CustomerException;
	
	public String addAccount(int cbal,int cid) throws AccountException;
	
	public String updateCustomer(int caccno,String cadd) throws CustomerException;
	
	public  Customer viewCustomer(String caccno) throws CustomerException;
	
	public int getCustomer(String cemail,String cmobile) throws CustomerException;
	
	public Customer viewAllCustomer() throws CustomerException;
	
	public String deleteAccount(int caccno) throws CustomerException;

}
