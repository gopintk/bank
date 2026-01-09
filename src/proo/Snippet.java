package proo;

public class Snippet {
	System.out.println("enter the mail id:");
				String email=sc.next();
				System.out.println("enter the pin:");
				int pin=sc.nextInt();
				String query="select * from user where email=? and pin=?";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, email);
				ps.setInt(2, pin);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					System.out.println("Login Succesfully");
					int choice;
}

