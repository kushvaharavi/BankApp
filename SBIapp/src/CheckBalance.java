import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckBalance {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String path = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@//localhost:1521/xe";
		String user = "system";
		String jpwd = "system";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//attach the jdbc driver
		
			try {
				Class.forName(path);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
		//make connection to the jdbc
		try {
			con = DriverManager.getConnection(url,user,jpwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Prepare the statement and execute the query
		try {
			ps = con.prepareStatement("select bal from SBI where accno=? and name=?");
			System.out.println("Welcome to SBI");
			System.out.println("Enter Your SBI Account Number and Name to show your account balance");
			System.out.println("Please Enter Account Number");
			String acc = scan.next();
			System.out.println("Please Enter Your Name");
			String name = scan.next();
			ps.setString(1, acc);
			ps.setString(2, name);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//displaying the balance
		try{
		if(rs.next()==true)
		{
			long bal = rs.getLong("bal");
			System.out.println("your account balance is "+bal);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//close the resourses
		try{
			con.close();
			ps.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
