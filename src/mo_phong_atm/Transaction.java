package mo_phong_atm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transaction {
	private int id;
	private int user_id;
	private LocalDateTime datetime;
	private int amount;
	private String type;
	public Transaction() {
		
	}
	public Transaction(int id,int user_id, String type, int amount, LocalDateTime datetime) {
		this.id = id;
		this.user_id = user_id;
		this.type = type;
		this.amount = amount;
		this.datetime = datetime;
	}
	
	
	public static ArrayList<Transaction> load(Connection con) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			String sql = "SELECT * FROM mo_phong_atm.transactions";
			ResultSet result = statement.executeQuery(sql);
	
			while(result.next()) {
			
				int id = Integer.parseInt(result.getString(1).trim());
				int userId = Integer.parseInt(result.getString(2).trim());
				String type = result.getString(3).trim();
				int amount = Integer.parseInt(result.getString(4).trim());
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
				LocalDateTime datetime = LocalDateTime.parse(result.getString(5).trim(), formatter);
				Transaction transaction = new Transaction(id, userId, type, amount, datetime);
				transactions.add(transaction);
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return transactions;
	}

}
