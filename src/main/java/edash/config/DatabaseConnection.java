package edash.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edash.utils.EnvConfig;
 
public class DatabaseConnection {
	private static Connection connection;
	
	// Singleton Pattern to ensure single DB connection
	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			String dbUrl = EnvConfig.get("DB_URL");
			String dbUser = EnvConfig.get("DB_USER");
			String dbPassword = EnvConfig.get("DB_PASSWORD");
			
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			System.out.println("Connection to PostgreSQL databasse!");
		}
		return connection;
	}
	
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Database connection closed.");
			} catch (SQLException e) {
				System.out.println("Failed to close database connection: " + e.getMessage());
			}
		}
	}
}
