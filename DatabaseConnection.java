import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
	String url = "jdbc:postgresql://localhost:5432/mydb";
	String user = "postgres";
	String password = "database";
	Class.forName("org.postgresql.Driver");
	return DriverManager.getConnection(url, user, password);
}
}
