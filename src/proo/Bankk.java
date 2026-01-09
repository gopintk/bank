package proo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Bankk {
	static Scanner sc=new Scanner(System.in);
	public static void registeruser(Connection con) throws SQLException {
		String query="insert into bank(name,email,pin)values(?,?,?)";
		System.out.println("enter the name:");
		String name=sc.next();
		System.out.println("enter the email:");
		String email=sc.next();
		System.out.println("enter the pin:");
		int pin=sc.nextInt();
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setInt(3, pin);
		int rows=ps.executeUpdate();
		if(rows>0) {
			System.out.println("Register Succecfully");
		}
	}
	public static void createaccount(Connection con) throws SQLException {
		String query="insert into accounts(name,email,pin,balance)values(?,?,?,?)";
		System.out.println("enter the name:");
		String name=sc.next();
		System.out.println("enter the email:");
		String email=sc.next();
		System.out.println("enter the pin:");
		int pin=sc.nextInt();
		System.out.println("enter the balance");
		int balance=sc.nextInt();
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setInt(3, pin);
		ps.setInt(4, balance);
		int rows=ps.executeUpdate();
		if(rows>0) {
			System.out.println("Account created");
			String query1="select account_number from accounts where email=?";
			PreparedStatement ps1=con.prepareStatement(query1);
			ps1.setString(1, email);
			ResultSet rs=ps1.executeQuery();
			rs.next();
			System.out.println("Account number: "+rs.getInt(1));
		}
	}
public static void loginaccount(Connection con) throws SQLException {
	System.out.println("Enter the mail id: ");
	String email=sc.next();
	System.out.println("enter the pin: ");
	int pin=sc.nextInt();
	String query="select * from accounts where email=? and pin=?";
	PreparedStatement ps2=con.prepareStatement(query);
	ps2.setString(1, email);
	ps2.setInt(2, pin);
	ResultSet rs2=ps2.executeQuery();
	if(rs2.next()) {
		System.out.println("Login Succesfully");
		int choice;
		do {
			System.out.println("enter choice 1.balance"+"2.Tranfer"+"3.credit"+"4.debited"+"5.logout");
			choice=sc.nextInt();
			switch(choice) {
			case 1:{
				checkbalance(email,con);
				break;
			}
			case 2:{
				transferamount(email,con);
				break;
			}
			case 3:{
				creditamount(email,con);
				break;
			}
			case 4:{
				withdrawamount(email,con);
				break;
			} 
			case 5:{
				System.out.println("logout");
			}
			}
			
		}while(choice!=5);
		}
	}
public static void creditamount(String email,Connection con) throws SQLException {
	System.out.println("Enter the credited amount:");
	int creditedamount=sc.nextInt();
	String query1="update accounts set balance=balance+? where email=?";
	PreparedStatement ps1=con.prepareStatement(query1);
	ps1.setInt(1, creditedamount);
	ps1.setString(2, email);
	ps1.executeUpdate();
}
public static void withdrawamount(String email,Connection con) throws SQLException {
	System.out.println("Enter the withdraw amount:");
	int withdrawamount1=sc.nextInt();
	String query1="update accounts set balance=balance-? where email=?";
	PreparedStatement ps1=con.prepareStatement(query1);
	ps1.setInt(1, withdrawamount1);
	ps1.setString(2, email);
	ps1.executeUpdate();
}
public static void transferamount(String email,Connection con) throws SQLException {
	System.out.println("Account to be transfered to:");
	int transaccount=sc.nextInt();
	System.out.println("Amount to be transfered: ");
	int transamount=sc.nextInt();
	String query1="update accounts set balance=balance-? where email=?";
	PreparedStatement ps1=con.prepareStatement(query1);
	ps1.setInt(1, transamount);
	ps1.setString(2, email);
	ps1.executeUpdate();
	String query2="update accounts set balance=balance+? where accountnum=?";
	PreparedStatement ps2=con.prepareStatement(query2);
	ps2.setInt(1, transamount);
	ps2.setInt(2, transaccount);
	ps2.executeUpdate();
}
public static void checkbalance(String email,Connection con) throws SQLException {
	System.out.println(email+" "+"Check account balance :");
	String query1="select balance from accounts where email=?";
	PreparedStatement ps1=con.prepareStatement(query1);
	ps1.setString(1,email);
	ResultSet rs=ps1.executeQuery();
	rs.next();
	System.out.println("Account balance:"+rs.getInt(1));
}


public static void loginuser(Connection con) throws SQLException {
		System.out.println("enter the mail id:");
		String email=sc.next();
		System.out.println("enter the pin:");
		int pin=sc.nextInt();
		String query="select * from bank where email=? and pin=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, email);
		ps.setInt(2, pin);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			System.out.println("Login Succesfully");
			int choice;
			do {
				System.out.println("enter choice 1.create Account"+"2.Login to Acount"+"3.Logout");
				choice=sc.nextInt();
				switch(choice) {
				case 1:{
					createaccount(con);
					break;
				}
				case 2:{
					loginaccount(con);
					break;
				}
				case 3:{
					System.out.println("Thank you");
				}
				
				}
			}while(choice!=3);
		}else {
			System.out.println("Pass or username wrong");
			
		}
	}
	public static void main(String[] args) throws SQLException {
		int choice;
		String url = "jdbc:mysql://localhost:3306/bank";
		String username = "root";
        String password = "tiger";
        Connection con = DriverManager.getConnection(url,username,password);
		do {
			System.out.println("Enter the choice 1.Register 2.Login"+"3.Exit");
			choice=sc.nextInt();
			switch(choice) {
			case 1:{
				registeruser(con);
				break;
			}
			case 2:{
				loginuser(con);
				break;
			}
			case 3:{
				System.out.println("Thank you!");
			}
			}
		}while(choice!=3);
		
		}
	
	}

