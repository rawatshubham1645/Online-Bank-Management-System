package com.masai.Dao;

import com.masai.Beam.Customer;
import com.masai.Exception.CustomerException;

public interface CustomerDao {
	
	
	public String insertCustomerDetails(Customer customer)throws CustomerException;
}
