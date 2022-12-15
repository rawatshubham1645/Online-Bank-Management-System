package com.masai.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.Beam.Accountant;
import com.masai.Beam.Customer;
import com.masai.Exception.AccountantException;
import com.masai.Exception.CustomerException;

import utility.Utilclass;

public class AccountantDaoImple implements AccountantDao{

	@Override
	public Accountant LoginAccountant(String username, String password)throws AccountantException {
		
		Accountant accountant = null;
		
		try(Connection conn = Utilclass.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("Select*from admin where username = ? and password = ?");
			ps.setString(1,"username");
			ps.setString(2,"password");
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				accountant  = new Accountant();
				accountant.setAname(rs.getString("name"));
				accountant.setAemail(rs.getString("email"));
				accountant.setApassword(rs.getString("password"));
				
			}else {
				throw new AccountantException("Invalid Username and Password");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
			throw new AccountantException(e.getMessage());
		}
		
		
		return accountant;
	}

	
	
}
