package com.revature.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static com.revature.util.LoggerUtil.*;

public class ConnectionFactory {

		private static String url;
		private static String user;
		private static String password;
		private static final String PROPERTIES_FILE = "/database.properties";
		private static ConnectionFactory cF;
		
		public static Connection getConnection() {
			
			if (cF == null) {
				cF = new ConnectionFactory(); 
				}
				return cF.createConnection();			
		}
		private ConnectionFactory() {
			Properties prop = new Properties();
			try{
				prop.load(ConnectionFactory.class.getResourceAsStream(PROPERTIES_FILE));
				url = prop.getProperty("url");
				user = prop.getProperty("user");
				password = prop.getProperty("password");
			} catch(FileNotFoundException e) {
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Connection createConnection() {
			Connection conn = null;
			
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				System.out.println("Failed to create connection");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
}