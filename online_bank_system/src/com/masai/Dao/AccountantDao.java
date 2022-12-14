package com.masai.Dao;

import com.masai.Beam.Accountant;
import com.masai.Exception.AccountantException;

public interface AccountantDao {
	public  Accountant LoginAccountant(String username,String password)throws AccountantException;


}
