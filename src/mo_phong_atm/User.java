package mo_phong_atm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
	private int id;
	private String name;
	private String pin;
	private int balance;
	private String account_number;
	public User() {
		
	}
	public User(int id,String name, String pin, int balance, String account_number) {
		this.id = id;
		this.name = name;
		this.pin = pin;
		this.balance = balance;
		this.account_number = account_number;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String toString() {
		return "User{ id: " + id + " name: " + name + " pin: " + pin + " balance: " + balance + " account_number: " + account_number + "}";
	}
	public static ArrayList<User> load(Connection con) {
		ArrayList<User> users = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			String sql = "SELECT * FROM mo_phong_atm.users";
			ResultSet result = statement.executeQuery(sql);
	
			while(result.next()) {
			
				int id = Integer.parseInt(result.getString(1).trim());
				String name = result.getString(2).trim();
				String pin = result.getString(3).trim();
				int balance = Integer.parseInt(result.getString(4).trim());
				String account_number = result.getString(5).trim();
				User user = new User(id, name, pin, balance, account_number);
				users.add(user);
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return users;
	}
	public static void update(Connection con, User user) {
		String sql = "UPDATE mo_phong_atm.users "
		        + "SET Name = ?, Pin = ?, Balance = ?, AccountNumber = ? "
		        + "WHERE userId = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, user.getName());
	            preparedStatement.setString(2, user.getPin());
	            preparedStatement.setInt(3, user.getBalance());
	            preparedStatement.setString(4, user.getAccount_number());
	            preparedStatement.setInt(5, user.getId());
	            // Thực hiện cập nhật
	            preparedStatement.executeUpdate();
	
//	            if (rowsUpdated > 0) {
//	                System.out.println("Cập nhật thành công!");
//	            } else {
//	                System.out.println("Cập nhật không thành công. Đối tượng không tồn tại hoặc không có sự thay đổi.");
//	            }
	        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
}
