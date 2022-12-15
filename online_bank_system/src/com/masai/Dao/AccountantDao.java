package com.masai.Dao;

import com.masai.Beam.Accountant;
import com.masai.Beam.Customer;
import com.masai.Exception.AccountantException;
import com.masai.Exception.CustomerException;

public interface AccountantDao {
	public  Accountant LoginAccountant(String username,String password)throws AccountantException;
	
	

}
