package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/";
		String hostName = "localhost";
		String database = "trandiep";
		String userName = "root";
		String password = "phoenix010";

		return getMySQLConnection(url,hostName, database, userName, password);
	}
	
	
	public static Connection getMySQLConnection(String url ,String hostName, String database,
			String userName, String password) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url+database, userName, password);
		return conn;
	}
}
