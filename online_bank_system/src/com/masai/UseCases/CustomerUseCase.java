package com.masai.UseCases;

import java.util.Scanner;

import com.masai.Beam.Customer;
import com.masai.Dao.CustomerDao;
import com.masai.Dao.CustomerDaoImpl;
import com.masai.Exception.CustomerException;

public class CustomerUseCase {

	public static void insertCustomerDetails() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("---------NEW ACCOUNT----------");
		System.out.println("Enter Customer Name");
			String a2=scn.next();
			System.out.println("Enter Account Opening Balance");
			int a3=scn.nextInt();
			System.out.println("Enter Email");
			String a4=scn.next();
			System.out.println("Enter Password");
			String a5=scn.next();
			System.out.println("Enter Mobile number");
			String a6=scn.next();
			System.out.println("Enter Address");
			String a7=scn.next();
			
			
			Customer customer = new Customer();
			
			customer.setCname(a2);
			customer.setCbalance(a3);
			customer.setCemail(a4);
			customer.setCpassword(a5);
			customer.setCmobile(a6);
			customer.setCaddress(a7);
			
			CustomerDao dao = new CustomerDaoImpl();
			try {
				String message = dao.insertCustomerDetails(customer);
				
				System.out.println(message);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			

	}

}
