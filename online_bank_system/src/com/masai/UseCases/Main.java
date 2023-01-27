package com.masai.UseCases;

import java.util.List;
import java.util.Scanner;

import com.masai.Dao.AccountantDao;
import com.masai.Dao.AccountantDaoImple;
import com.masai.Dao.CustomerDao;
import com.masai.Dao.CustomerDaoImpl;
import com.masai.Exception.AccountException;
import com.masai.Exception.AccountantException;
import com.masai.Exception.CustomerException;
import com.masai.Model.Accountant;
import com.masai.Model.Customer;
import com.masai.Model.Transaction;

public class Main {
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		boolean f=true;
		
		while(f) {
			
			System.out.println("**********************************");
			System.out.println("Welcome To Online Banking System");
			System.out.println("**********************************"); 
			System.out.println("1. Accountant Portal \r\n"
					+ "2. Customer Portal \r\n");
			System.out.println("Choose your option");
			int choice=sc.nextInt();
			
			
			switch(choice) {
			case 1:
				System.out.println("LOGIN <<---->> ACCOUNTANT");
				System.out.println("--------------------------");
				System.out.println("Enter Username");
				String uname=sc.next();
				System.out.println("Enter Password");
				String pass=sc.next();
				
				AccountantDao dao = new AccountantDaoImple();
				try {
					Accountant accountant = dao.LoginAccountant(uname, pass);
					
				System.out.println("Welcome "+accountant.getAname()+"  \r\n"
						+ "Designation: ACCOUNTANT");
				System.out.println("----------------------------");
				
				
				
				boolean y = true;
				while(y) {
					System.out.println("-----------------------------------------------------------------\r\n"
							+ "1. Add New Customer Account\r\n"
							+ "2. Edit existing available account\r\n"
							+ "3. Remove the account by account number\r\n"
							+ "4. View particular account details by giving account number\r\n"
							+ "5. View all the account details\r\n"
							+ "6. Add new account for existing Account holder\r\n"
							+ "7. View deposit and withdrawal operations for Customer\r\n"
							+ "8. LOGOUT\r\n"
							+"-----------------------------------------------------------------\r\n");
					
					int x = sc.nextInt();
					
					if(x==1) {
						System.out.println("---------  NEW ACCOUNT  ----------");
						System.out.println();
						System.out.println("Enter Customer Name");
			 			String a2=sc.next();
			 			System.out.println("Enter Account Opening Balance");
			 			int a3=sc.nextInt();
			 			System.out.println("Enter Email");
			 			String a4=sc.next();
			 			System.out.println("Enter Password");
			 			String a5=sc.next();
			 			System.out.println("Enter Mobile number");
			 			String a6=sc.next();
			 			System.out.println("Enter Address");
			 			String a7=sc.next();
			 			
			 			
			 			int s1=-1;
						try {
							s1 = dao.addCustomer(a2,a4,a5,a6,a7);

							try {
								 dao.addAccount(a3, s1);
								
							} catch (AccountException e) {
								
								e.printStackTrace();
							}
						} catch (CustomerException e) {
							
							e.printStackTrace();
						}

					}
					
					if(x==2) {
						System.out.println("------- UPDATE ADDRESS -------");
						System.out.println("Enter Customer Account No: ");
			 			int u=sc.nextInt();
			 			System.out.println("Enter new Address");
			 			String u2=sc.next();
			 			
			 			try {
							String message=dao.updateCustomer(u, u2);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block	
							e.printStackTrace();
						}
					}
					
					if(x==3) {
						System.out.println("---------REMOVE ACCOUNT---------");
						System.out.println("Enter Account No.");
						int ac=sc.nextInt();
						String s=null;
						try {
							s=dao.deleteAccount(ac);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						if(s!=null)
							System.out.println(s);
					}
					
					if(x==4) {
						System.out.println("--------CUSTOMER DETAILS---------");
						System.out.println("Enter Customer Account No.");
						String ac=sc.next();
						
						try {
							Customer customer = dao.viewCustomer(ac);
							
							if(customer!=null) {
								System.out.println("******************************");
								System.out.println("Account No: " + customer.getCaccno());
								System.out.println("Name: " + customer.getCname());
								System.out.println("Balance: " + customer.getCbalance());
								System.out.println("Email: " + customer.getCemail());
								System.out.println("Password: " + customer.getCpassword());
								System.out.println("Mobile: " + customer.getCmobile());
								System.out.println("Address: " + customer.getCaddress());
								System.out.println("******************************");
								
							}else {
								System.out.println("Account does not Exist");
								System.out.println("---------------------------");
							}
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(x==5) {
				try {
					Customer customer = dao.viewAllCustomer();
				} catch (CustomerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
					}
					
					
					if(x==6) {
						System.out.println("--------- NEW ACCOUNT FOR EXISTING ACCOUNT HOLDER ----------");
						System.out.println("Enter email: ");
						String e=sc.next();
						System.out.println("Enter mob: ");
						String m=sc.next();
						System.out.println("Enter new Account balance");
						int b=sc.nextInt();
						
						
						try {
							int customerID=dao.getCustomer(e, m);
							
							try {
								dao.addAccount(b, customerID);
							} catch (AccountException e1) {
								
								e1.printStackTrace();
							}
						} catch (CustomerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(x==7) {
						CustomerDao cd=new CustomerDaoImpl();
						System.out.println("----------------TRANSACTIONS------------------");
						System.out.println("Enter Account No. to view Transaction Records");
						int ac=sc.nextInt();
						List<Transaction> li=null;
						try {
							li= cd.viewTransaction(ac);
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("------TRANSACTION HISTORY-------");
						System.out.println();
						System.out.println("Account No.: " + li.get(0).getAccountNo());
						
						li.forEach(v->{
							System.out.println("----------------------------------------------------");
							if(v.getDeposit()!=0)
								System.out.println("Amount Credit: "+ v.getDeposit());
							if(v.getWithdraw()!=0)
								System.out.println("Amount Debit : "+ v.getWithdraw());
							System.out.println("Date and Time: "+ v.getTransaction_time());
						});
					}
					if(x==8) {
						System.out.println("Accountant Logged out");
						y=false;
					}
					
				}
				break;
				
				
				} catch (AccountantException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
				
//		**********************************   Starting of Case-2   ********************************************	
				
			case 2:
				System.out.println("LOGIN <<---->> CUSTOMER");
				System.out.println("--------------------------");
				System.out.println("Enter Customer Email");
				String username=sc.next();
				System.out.println("Enter Password");
				String password=sc.next();
				System.out.println("Enter Account No");
				int acno=sc.nextInt();
				
				CustomerDao dao2 = new CustomerDaoImpl();
				
				try {
					Customer customer = dao2.LoginCustomer(username, password, acno);
					
					System.out.println("Welcome "+customer.getCname());
					
					
					boolean m= true;
					while(m) {
						System.out.println("-------------------------------\r\n"
								+ "1. View Balance\r\n"
								+ "2. Deposit Money\r\n"
								+ "3. Withdraw Money\r\n"
								+ "4. Transfer Money\r\n"
								+ "5. View Transaction History\r\n"
								+ "6. LOGOUT\r\n"
								+"-------------------------------\r\n");

						
						int x=sc.nextInt();
						
						
						if(x==1) {
							System.out.println("--------BALANCE----------");
							System.out.println("Current Account Balance");
							System.out.println(dao2.viewBalance(acno)); 
							System.out.println();
							System.out.println();
						}
						if(x==2) {
							System.out.println("---------- DEPOSITE -----------");
							System.out.println();
							System.out.println("Enter Amount to Deposit");
							int am=sc.nextInt();
							dao2.Deposit(acno, am);
							System.out.println("Your Balance after Deposit");
							System.out.println();
							System.out.println(dao2.viewBalance(acno));
							System.out.println();
							System.out.println();
						}
						if(x==3) {
							System.out.println("----------WITHDRAWL------------");
							System.out.println("Enter Withdrawl amount");
							int wa=sc.nextInt();
							try {
								dao2.Withdraw(acno, wa);
								System.out.println("Your Balance after Withdrawl");
								System.out.println(dao2.viewBalance(acno));
								System.out.println("-----------------------------");
							}catch(CustomerException e) {
								System.out.println(e.getMessage());
							}
						}
						if(x==4) {
							System.out.println("----------AMOUNT TRANSFER-----------");
							System.out.println();
							System.out.println("Enter Amount to Transfer");
							int t=sc.nextInt();
							System.out.println("Enter Account No. to transfer amount");
							int ac=sc.nextInt();
							
							try {
								dao2.Transfer(acno, t, ac);
								System.out.println("Amount transferred Succesfully...");
								
								System.out.println();
							}catch(CustomerException e) {
								System.out.println(e.getMessage());
							}
						}
						if(x==5) {
							List<Transaction> li=null;
							try {
								li= dao2.viewTransaction(acno);
							}catch(CustomerException e) {
								System.out.println(e.getMessage());
							}
							System.out.println("------TRANSACTION HISTORY-------");
							System.out.println();
							System.out.println("Account No.: " + li.get(0).getAccountNo());
							
							li.forEach(v->{
								System.out.println("----------------------------------------------------");
								if(v.getDeposit()!=0)
									System.out.println("Amount Credit: "+ v.getDeposit());
								if(v.getWithdraw()!=0)
									System.out.println("Amount Debit : "+ v.getWithdraw());
								System.out.println("Date and Time: "+ v.getTransaction_time());
							});
						}
						
						if(x==6) {
							System.out.println();
							System.out.println("Customer Logged out");
							m=false;
						}
					}
					break;
					
				} catch (CustomerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			}
			
			
			
		}
		
	}
}
