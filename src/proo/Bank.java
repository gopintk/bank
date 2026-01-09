package proo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Bank {
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
			String query="insert into accounts(name,email,pin)values(?,?,?)";
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
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Account created");
				String query1="select account_num from accounts where email=?";
				PreparedStatement ps1=con.prepareStatement(query1);
				ps1.setString(1, email);
				ResultSet rs=ps1.executeQuery();
				rs.next();
				System.out.println("Account number: "+rs.getInt(1));
			}
		}
		
	public static void loginaccount(Connection con) {
		System.out.println("enter the mail id:");
		String email=sc.next();
		System.out.println("enter the pin:");
		int pin=sc.nextInt();
		String query="select * from accounts where email=? and pin=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, email);
		ps.setInt(2, pin);
		ResultSet rs2=ps.executeQuery();
		if(rs2.next()) {
			System.out.println("Login Succesfully");
			int choice;
			do {
				System.out.println("enter choice 1.balance"+"2.Tranfer"+"3.credit"+"4.debit"+"5.Logout");
				choice=sc.nextInt();
				switch(choice) {
//				case 1:{
//					checkbalance(email);
//					break;
//					}
//				case 2:{
//					tranferamount(email);
//					break;
//					}
//				case 3:{
//					creditedamount(email);
//					break;
//				}
//				case 4:{
//					dibtedamount(email);
//					break;
					//}
				case 5:{
					System.out.println("logout");
					}
				}
		}while(choice!=5);
			}
	}
	public static void transformation(Connection con,String email) {
		System.out.println("Account to be transfered to");
		String transemail=sc.next();
		System.out.println("Account to be transfered :");
		int transamount=sc.nextInt();
		String query1="update account set balance=balance-? where email=?";
		String query2="update account set balance=balance+? where email=?";
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
			// TODO Auto-generated method stub
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

