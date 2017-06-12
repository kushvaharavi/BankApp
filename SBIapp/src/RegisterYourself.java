import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterYourself {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String path = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@//localhost:1521/xe";
		String user = "system";
		String jpwd = "system";
		Connection con = null;
		PreparedStatement ps = null;
		//attach the jdbc driver
		
			try {
				Class.forName(path);
				System.out.println("Driver loaded successfuly");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
		//make the connection to the jdbc
		try {
			con = DriverManager.getConnection(url,user,jpwd);
			System.out.println("connection establised");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Preparing the statement and execute the query
		try {
			ps = con.prepareStatement("insert into sbi values(?,?,?,?,?,?)");
			System.out.println("Statement Preapared");
			System.out.println("Welcome to SBI");
			System.out.println("Register Yourself...!");
			System.out.println("Enter Account Number");
			String acc = scan.next();
			System.out.println("Enter Your Name");
			String name = scan.next();
			System.out.println("Enter account Password");
			String pwd = scan.next();
			System.out.println("Enter account Confirm Password");
			String cpwd = scan.next();
			System.out.println("Enter Account type");
			String type = scan.next();
			System.out.println("Enter your account Balance");
			int bal = scan.nextInt();
			if((pwd.equals(cpwd))==true)
			{
			ps.setString(1,acc);
			ps.setString(2, name);
			ps.setString(3, pwd);
			ps.setString(4, cpwd);
			ps.setString(5, type);
			ps.setInt(6, bal);
			ps.executeUpdate();
			System.out.println("Your account is created");
			}
			else
			{
				System.out.println("Password mismatched");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//close the resources
		try{
			con.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
