package br.com.cantinaifal.database.connection;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    
    public static Connection getConnection(String db) throws SQLException {
        String url = "jdbc:mysql://localhost/" + db;
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }
}
