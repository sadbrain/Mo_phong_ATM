package mo_phong_atm;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private int transfered_user_id;
	public Transaction() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTransfered_user_id() {
		return this.transfered_user_id;
	}
	public void setTransfered_user_id(int value) {
		 this.transfered_user_id = value;
	}
	public Transaction(int id,int user_id, String type, int amount, LocalDateTime datetime, int transferedUserId) {
		this.id = id;
		this.user_id = user_id;
		this.type = type;
		this.amount = amount;
		this.datetime = datetime;
		this.transfered_user_id = transferedUserId;
	}
	
	
	public static ArrayList<Transaction> load(Connection con) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		try {
			Statement statement = con.createStatement();
			String sql = "SELECT * FROM mo_phong_atm.transactions";
			ResultSet result = statement.executeQuery(sql);
	
	        while (result.next()) {

	            int id = result.getInt("TransactionId");
	            int userId = result.getInt("UserId");
	            String type = result.getString("Type");
	            int amount = result.getInt("Amount");
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime datetime = LocalDateTime.parse(result.getString("DateTime"), formatter);
	            int transferredUserId = result.getInt("TransferedUserId");
	            Transaction transaction = new Transaction(id, userId, type, amount, datetime, transferredUserId);
	            transactions.add(transaction);
	        }
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return transactions;
	}
	public static int lastTransactionID(Connection con) {
        String sql = "SELECT TransactionId FROM mo_phong_atm.transactions ORDER BY TransactionId DESC LIMIT 1";

        try (PreparedStatement preparedStatement =  con.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int lastObjectId = resultSet.getInt("TransactionId");
                return lastObjectId;
            } 
            
            
        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        return 1;
	}
	
	public static void create(Connection con, Transaction transaction) throws SQLException {
		String sql = "INSERT INTO mo_phong_atm.transactions (TransactionId, UserId, Type, Amount, DateTime, TransferedUserId) "
		        + "VALUES (?, ?, ?, ?, ?, ?)";
		
		String sqlLastTransacton = "SELECT TransactionId FROM mo_phong_atm.transactions  ORDER BY TransactionId DESC LIMIT 1";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sqlLastTransacton);
		int lastTransactionId = lastTransactionID(con);
		transaction.setId(lastTransactionId + 1);
	     try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            // Đặt giá trị cho các tham số trong SQL query
	            preparedStatement.setInt(1, transaction.getId());
	            preparedStatement.setInt(2, transaction.getUser_id());
	            preparedStatement.setString(3, transaction.getType());
	            preparedStatement.setInt(4, transaction.getAmount());
	            preparedStatement.setObject(5, transaction.getDatetime());
	            preparedStatement.setInt(6,transaction.getTransfered_user_id());
	           
	            // Thực hiện truy vấn
	            preparedStatement.executeUpdate();
	

	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
    @Override
    public String toString() {
    
    	LocalDateTime date_time = datetime;
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = date_time.format(formatter);
        return "Transaction{" +
                "id=" + id +
                ", userId=" + user_id +
                ", datetime=" + formattedDateTime +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", transferredUserId=" + transfered_user_id +
                '}';
    }

}
