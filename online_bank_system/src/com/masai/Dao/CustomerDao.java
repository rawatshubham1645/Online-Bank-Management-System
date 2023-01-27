package com.masai.Dao;

import java.util.List;

import com.masai.Exception.CustomerException;
import com.masai.Model.Customer;
import com.masai.Model.Transaction;

public interface CustomerDao {
	
	public Customer LoginCustomer(String username, String password, int accountno)throws CustomerException;
	
	public int viewBalance(int cAccno) throws CustomerException;
	
	public int Deposit(int cAccno, int amount) throws CustomerException;
	
	public int Withdraw(int cAccno, int amount) throws CustomerException;
	
	public int Transfer(int cAccno, int amount, int cAcno2) throws CustomerException;
	
	public List<Transaction> viewTransaction(int caccno) throws CustomerException;
}
