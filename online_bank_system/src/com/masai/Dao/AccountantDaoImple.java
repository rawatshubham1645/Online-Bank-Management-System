package com.masai.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.Beam.Accountant;
import com.masai.Beam.Customer;
import com.masai.Exception.AccountException;
import com.masai.Exception.AccountantException;
import com.masai.Exception.CustomerException;

import utility.Utilclass;

public class AccountantDaoImple implements AccountantDao{

	@Override
	public Accountant LoginAccountant(String username, String password)throws AccountantException {
		
		Accountant accountant = null;
		
		try(Connection conn = Utilclass.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("Select * from admin where name = ? and password = ?");
			ps.setString(1,username);
			ps.setString(2,password);
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				accountant  = new Accountant();
				accountant.setAname(rs.getString("name"));
				accountant.setAemail(rs.getString("email"));
				accountant.setApassword(rs.getString("password"));
				
			}else {
				throw new AccountantException("Invalid Username and Password.....Try Again! ");
			}
			
		} catch (SQLException e) {
			
			throw new AccountantException(e.getMessage());
		}
		
		
		return accountant;
	}

	

	//------------------------------------addCustomer-------------------------------------------
	
	@Override
	public int addCustomer(String cname, String cemail, String cpassword, String cmobile, String caddress)
			throws CustomerException {
		
		int cid=-1;
		
		try(Connection conn = Utilclass.provideConnection()) {
		 
		 PreparedStatement ps=conn.prepareStatement("insert into Customer(cname,cemail,cpassword,cmobile,caddress) values(?,?,?,?,?)");
	     ps.setString(1,cname);
	     ps.setString(2,cemail);
	     ps.setString(3,cpassword);
	     ps.setString(4,cmobile);
	     ps.setString(5,caddress);
	     
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			 PreparedStatement ps2=conn.prepareStatement("select cid from Customer where cemail=? AND cmobile=?");
			 ps2.setString(1, cemail);
			 ps2.setString(2, cmobile);
			 
			 ResultSet rs=ps2.executeQuery();
			 
			 if(rs.next()) {
				 cid=rs.getInt("cid");
			 }
			 
			 
		 }else
			 System.out.println("Inserted data is not correct");
		 
		}catch(SQLException e) {
			
			e.printStackTrace();

		}
		
		return cid;
		
		
	}



	
	//--------------------------------------Add Account------------------------------------

	@Override
	public String addAccount(int cbal, int cid) throws AccountException {
		String message = null;
		
		try(Connection conn = Utilclass.provideConnection()) {
			PreparedStatement ps =conn.prepareStatement("insert into account(cbal,cid) values(?,?)");
			 ps.setInt(1,cbal);
		     ps.setInt(2,cid);
			int x = ps.executeUpdate();
			if(x>0) {
				System.out.println("Account added Succesfully.....!");
			}
			else
				 System.out.println("Inserted data is not correct");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			message = e.getMessage();
		}
		
		
		return message;
	}

//------------------------------Update Customer Account number and Address ---------------------

	@Override
	public String updateCustomer(int caccno, String caddress) throws CustomerException {
		String message = null;
		try (Connection conn = Utilclass.provideConnection()){
			PreparedStatement ps=conn.prepareStatement(" update Customer i inner join account a on i.cid=a.cid AND a.cAccno=? set i.caddress=?");
			ps.setInt(1, caccno);
			 ps.setString(2,caddress);
			 
			 int x=ps.executeUpdate();
			 if(x > 0) {
				 System.out.println("Address updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			message=e.getMessage();
		}
		
		
		
		return message;
	}


	//--------------------------------------------  View Customer Detail By Account No  --------------------------------------
	
	@Override
	public Customer viewCustomer(String caccno) throws CustomerException {
		Customer customer = null;
			try (Connection conn = Utilclass.provideConnection()){
				PreparedStatement ps= conn.prepareStatement("select * from Customer i inner join Account a on a.cid=i.cid where caccno = ?");			
				ps.setString(1, caccno);
				
				
				ResultSet rs= ps.executeQuery();
				
				
				if(rs.next()) {
					
						int a=rs.getInt("caccno");
						
						String n=rs.getString("cname");
						
						int b=rs.getInt("cbal");
						
						String e= rs.getString("cemail");
						
						String p= rs.getString("cpassword");
						
						String m= rs.getString("cmobile");
						
						String ad= rs.getString("caddress");
						
						customer=new Customer(a,n,b,e,p,m,ad);
					
				}else
					throw new CustomerException("Invalid Account No ");
				
				 
				
				
				
			} catch (SQLException e) {
				throw new CustomerException(e.getMessage());
			}
		
		
		return customer;
	}

	//--------------------------------  Get Customer Id  ----------------------------------------------

	@Override
	public int getCustomer(String cemail, String cmobile) throws CustomerException {
		int cid = -1;
		try(Connection conn = Utilclass.provideConnection()){
			PreparedStatement ps2=conn.prepareStatement("select cid from Customer where cemail=? AND cmobile=?");
			 ps2.setString(1, cemail);
			 ps2.setString(2, cmobile);
			 
			 ResultSet rs=ps2.executeQuery();
			 
			 if(rs.next()) {
				 cid=rs.getInt("cid");
			 }
		} catch (SQLException e) {
			throw new CustomerException("Invalid Account No.");
		}
		
		return cid;
	}

	//-----------------------------  All Customer Details  ---------------------------------------------------------------

	@Override
	public Customer viewAllCustomer() throws CustomerException {
		Customer customer = new Customer();
		
		
		try(Connection conn = Utilclass.provideConnection()){
PreparedStatement ps= conn.prepareStatement("select * from Customer i inner join Account a on a.cid=i.cid");			
			

			ResultSet rs= ps.executeQuery();
			
			
			while(rs.next()) {
				
					int a=rs.getInt("cAccno");
					
					String n=rs.getString("cname");
					
					int b=rs.getInt("cbal"); 	//Customer balance table--->cbal
					
					String e= rs.getString("cemail");
					
					String p= rs.getString("cpassword");
					
					String m= rs.getString("cmobile");
					
					String ad= rs.getString("caddress");
					
					System.out.println("******************************");
					System.out.println("Account No: " + a);
					System.out.println("Name: " + n);
					System.out.println("Balance: " + b);
					System.out.println("Email: " + e);
					System.out.println("Password: " + p);
					System.out.println("Mobile: " + m);
					System.out.println("Address: " + ad);
					System.out.println("******************************");
					
					customer=new Customer(a,n,b,e,p,m,ad);
				
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new CustomerException("Invalid Account No.");
		}
		
		return customer;
		
	}

	//------------------ Delete Account by Account Number ------------------------------------------------

	@Override
	public String deleteAccount(int caccno) throws CustomerException {
		String message=null;
		try(Connection conn = Utilclass.provideConnection()) {
		 
		 
		
		 PreparedStatement ps=conn.prepareStatement("delete i from customer i inner join account a on i.cid=a.cid where a.caccno=?");

		 ps.setInt(1, caccno);
	
		 
	     
		int x=ps.executeUpdate();
		 
		 if(x > 0) {	
			 System.out.println("Account deleted sucessfully..!");
			 System.out.println("-------------------------------");
		 }else {
			 System.out.println("Deletion failed...Account Not Found");
			 System.out.println("------------------------------------");
		 }	
		}catch(SQLException e) {
			
			e.printStackTrace();
			message=e.getMessage();
		}
		
		return message;
		
	}
	
}
