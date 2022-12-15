package com.masai.UseCases;

import java.util.Scanner;

import com.masai.Beam.Accountant;
import com.masai.Dao.AccountantDao;
import com.masai.Dao.AccountantDaoImple;
import com.masai.Exception.AccountantException;

public class RegisterAccountant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn= new Scanner(System.in);
		System.out.println("Enter Name of Accountant");
		String name = scn.next();
		
		System.out.println("Enter Accountant Username");
		String Username = scn.next();
		
		System.out.println("Enter Accountant Password");
		String password = scn.next();
		
//		Accountant accountant = new Accountant();
//		
//		accountant.setName(name);
//		accountant.setEmail(Username);
//		accountant.setPassword(password);
		
		AccountantDao dao = new AccountantDaoImple();
		try {
			Accountant account=dao.LoginAccountant(Username, password);
		} catch (AccountantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
