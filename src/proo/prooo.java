package proo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class prooo {
		 static Scanner sc=new Scanner(System.in);
		public static void registeruser(Connection con) throws SQLException {
			String query="insert into bank(name,email,pin)values(?,?,?)";
			System.out.println("Enter the name: ");
			String name=sc.next();
			System.out.println("Enter the email id: ");
			String email=sc.next();
			System.out.println("Enter the pin:");
			int pin=sc.nextInt();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, pin);
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Register successfully");
			}
		}
		public static void createaccount(Connection con) throws SQLException {
			String query="insert into accounts(name,email,pin) values(?,?,?)";
			System.out.println("Enter the name: ");
			String name=sc.next();
			System.out.println("Enter the email id: ");
			String email=sc.next();
			System.out.println("Enter the pin:");
			int pin=sc.nextInt();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, pin);
			int rows=ps.executeUpdate();
			if(rows>0) {
				System.out.println("Account Created successfully");
				String query1="select accountnum from bank where email=?";
				PreparedStatement ps1=con.prepareStatement(query1);
				ps1.setString(1,email);
				ResultSet rs=ps1.executeQuery();
				rs.next();
				System.out.println("Account number: "+rs.getInt(1));
			}
			else {
				System.out.println("Account Not created");
			}
		}
		public static void loginaccount(Connection con) {
			
		}
		public static void loginuser(Connection con) throws SQLException {
			System.out.println("Enter the Email id: ");
			String email=sc.next();
			System.out.println("Enter the PIN: ");
			int pin=sc.nextInt();
			String query="select * from accounts where email=? and pin=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, pin);
			ResultSet rs=ps.executeQuery();
		    if(rs.next()) {
		    	System.out.println("Login Successfully");
		    	int choice;
		    	do {
		    		System.out.println("Enter choice 1.Create Account 2.Login to account 3.Logout");
		    		choice=sc.nextInt();
		            switch(choice) {
		            case 1:{
		            	createaccount(con);
		            }
		            case 2:{
		            	loginaccount(con);
		            }
		            case 3:{
		           	System.out.println("Thank You");
		            }
		            }
		    	}while(choice!=3);
		    }
			
		}
		
		public static void main(String[] args) throws SQLException{
			String url="jdbc:mysql://localhost:3306/bank";
			String username="root";
			String password="tiger";
			int choice;
			Connection con=DriverManager.getConnection(url,username,password);
			do {
				System.out.println("Enter the choice 1.Register 2.Login 3.Exit");
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
					System.out.println("Thank You");
				}
				}
			}while(choice!=3);
			
		
			
		}
	}
