package com.masai.Dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.Beam.Customer;
import com.masai.Beam.Transaction;
import com.masai.Exception.CustomerException;

import utility.Utilclass;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public Customer LoginCustomer(String username, String password, int accountno) throws CustomerException {
		Customer customer = null;
		
		try(Connection conn = Utilclass.provideConnection()) {
			
		PreparedStatement ps= conn.prepareStatement("select * from Customer i inner join Account a on i.cid=a.cid where cemail = ? AND cpassword = ? AND cAccno=?" );			
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, accountno);
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				int ac=rs.getInt("cAccno");	
					
				String n=rs.getString("cname");
				
				int b=rs.getInt("cbal");
				
				String e= rs.getString("cemail");
				
				String p= rs.getString("cpassword");
				
				String m=rs.getString("cmobile");
				
				String ad=rs.getString("caddress");
				
			customer=new Customer(ac,n,b,e,p,m,ad);	
				
				
			}else {
				throw new CustomerException("Invalid Username or password....Try Again!");
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new CustomerException(e.getMessage());
		}
		
		
		return customer;
	}
//---------------------------------View Balance by Customer Account number-------------------------------------------
	
	@Override
	public int viewBalance(int cAccno) throws CustomerException {
		int c = -1;
		
		try (Connection conn = Utilclass.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("select cbal from account where cAccno=?");
			ps.setInt(1,cAccno);
			
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				c = rs.getInt("cbal");
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CustomerException(e.getMessage());
		}
		
		
		return c;
		
	}
	//-------------------------Update balance and insert into transaction table---------------
	@Override
	public int Deposit(int cAccno, int amount) throws CustomerException {
int b=-1;
		
		try(Connection conn = Utilclass.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("update Account set cbal=cbal+? where cAccno = ?");			
				
				ps.setInt(1, amount);
				ps.setInt(2, cAccno);
				
				int rs= ps.executeUpdate();
				
			if(rs>0) {
				PreparedStatement ps2=conn.prepareStatement("insert into transaction values(?,?,0,NOW())");
				
				ps2.setInt(1, cAccno);
				ps2.setInt(2, amount);
				
				int rs2=ps2.executeUpdate();
			}else {
				throw new CustomerException("Account not found");
			}
			
				
					
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CustomerException(e.getMessage());
			}
		return b;
	}
	//-------------------------------withdraw amount--------------------------
	
	@Override
	public int Withdraw(int cAccno, int amount) throws CustomerException {
		int vb=viewBalance(cAccno);
		if(vb>amount) {
			try(Connection conn = Utilclass.provideConnection()) {
				
				PreparedStatement ps= conn.prepareStatement("update Account set cbal=cbal-? where cAccno = ?" );			
					
					ps.setInt(1, amount);
					ps.setInt(2, cAccno);
					
					int rs= ps.executeUpdate();
					
				if(rs>0) {
					PreparedStatement ps2=conn.prepareStatement("insert into Transaction values(?,0,?,NOW())");
					
					ps2.setInt(1, cAccno);
					ps2.setInt(2, amount);
					
					int rs2=ps2.executeUpdate();
				}else {
					throw new CustomerException("Account not found");
				}
				
					
						
			} catch (SQLException e) {
					throw new CustomerException(e.getMessage());
			}
			
		}else {
			throw new CustomerException("Insufficient Balance");
		}
		
		return vb+amount;
	}

	
	
	
	//-------------------------------------------------------------------------
	
	@Override
	public int Transfer(int cAccno, int amount, int cAcno2) throws CustomerException {
			int vb=viewBalance(cAccno);
		
		if(vb>amount && checkAccount(cAcno2)) {
			
			int wid=Withdraw(cAccno, amount);
			int dep=Deposit(cAcno2, amount);
			
			
		}else {
			throw new CustomerException("Insufficient Balance");
		}
		
		return 0;
	}
	
	private boolean checkAccount(int cAccno) {
		
		try(Connection conn = Utilclass.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from Account where cAccno=?;");
			
			ps.setInt(1, cAccno);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	
		//----------------------------------------------------------------
	
	@Override
	public List<Transaction> viewTransaction(int caccno) throws CustomerException {
		List<Transaction> li=new ArrayList<>();
		
		try(Connection conn = Utilclass.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("select * from transaction where caccno=?");
			ps.setInt(1, caccno);
			
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) {
				int ac=rs.getInt("caccno");
				int dep=rs.getInt("deposit");
				int wid=rs.getInt("withdraw");
				Timestamp tt= rs.getTimestamp("timestamp");
				
				li.add(new Transaction(ac,dep,wid,tt));
			}
			if(li.size()==0) {
				throw new CustomerException("No Transaction Found");
			}
			
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return li;
	}
	
	
	
}
