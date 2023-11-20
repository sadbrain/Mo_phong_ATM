package mo_phong_atm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Program {

	
		
	private static Connection connectDb(String driverName,String jdbcUrl,String user,String password) {
		try {
			Class.forName(driverName);
			try {
				Connection con = (Connection) DriverManager.getConnection(jdbcUrl, user, password);
				return con;
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Connection con = connectDb("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/mo_phong_atm?useSSL=false", "root", "");
		ArrayList<User> users = User.load(con);
		ArrayList<Transaction> transactions = Transaction.load(con);
		for (User user1 : users) {
		    System.out.println(user1);
		}
		for (Transaction transaction : transactions) {
		    System.out.println(transaction);
		}
				
		
	}
}
