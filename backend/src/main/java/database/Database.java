package database;

import java.sql.DriverManager;
import java.sql.Connection;


public class Database {
    private final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = System.getenv("DATABASE_URL");
    private final String USERNAME = System.getenv("USERNAME");
    private final String PASSWORD = System.getenv("PASSWORD");

    public Connection getConnection() {
        Connection conn = null;

        try {
			Class.forName(DATABASE_DRIVER);
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

        return conn;
    }
}
