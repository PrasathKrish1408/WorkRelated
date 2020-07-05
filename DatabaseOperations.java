import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DatabaseOperations {
	public Connection dbConnection;
	public DatabaseOperations() throws ClassNotFoundException, SQLException {
		dbConnection = DatabaseConnection.getDBConnection();
	}
	public int insert(ChargeObject charge_Object) throws SQLException {
		String query = "INSERT into charge_details ( charge_id,currency,customer,description,payment_intent,status,amount,disputed ) values( ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement st = dbConnection.prepareStatement(query);
		st.setString(1, charge_Object.id);
		st.setString(2, charge_Object.currency);
		st.setString(3, charge_Object.customer);
		st.setString(4, charge_Object.description);
		st.setString(5, charge_Object.payment_intent);
		st.setString(6, charge_Object.status);
		st.setInt(7, charge_Object.amount);
		st.setBoolean(8, charge_Object.disputed);
		int result = st.executeUpdate();
		st.close();
		dbConnection.close();
		return result;
	}
	public int insert(RefundObject refund_Object) throws SQLException {
		String query = "INSERT into refund_details ( refund_id,amount,charge,reason,status ) values( ?, ?, ?, ?, ?);";
		PreparedStatement st = dbConnection.prepareStatement(query);
		st.setString(1, refund_Object.id);
		st.setInt(2, refund_Object.amount);
		st.setString(3, refund_Object.charge);
		st.setString(4, refund_Object.reason);
		st.setString(5, refund_Object.status);
		int result = st.executeUpdate();
		st.close();
		dbConnection.close();
		return result;
	}
	public void closeDbConnection() throws SQLException {
		dbConnection.close();
	}
}
