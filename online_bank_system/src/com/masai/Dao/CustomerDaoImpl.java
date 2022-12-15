package com.masai.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.masai.Beam.Customer;
import com.masai.Exception.CustomerException;

import utility.Utilclass;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public String insertCustomerDetails(Customer customer) throws CustomerException {
		
		String message = "Customer Details Not Inserted";
		
		try(Connection conn = Utilclass.provideConnection()){
			
			
			PreparedStatement ps =conn.prepareStatement("insert into customer(cname,cbalance,cemail,cpassword,cmobile,caddress) values(?,?,?,?,?,?)");
			
			ps.setString(1,customer.getCname());
			ps.setInt(2,customer.getCbalance());
			ps.setString(3,customer.getCemail());
			ps.setString(4,customer.getCpassword());
			ps.setString(5,customer.getCmobile());
			ps.setString(6,customer.getCaddress());
			
			int x = ps.executeUpdate();
			if(x>0) {
				message = "Customer Details Inserted Successfully";
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			throw new CustomerException(e.getMessage());
		}
		return message;
		
		
	}
}
