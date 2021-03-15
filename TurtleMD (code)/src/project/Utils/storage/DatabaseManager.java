package project.Utils.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    //This Manager connects to the database, and returns connection where needed. Close at program exit.
    private Connection connection;

    public DatabaseManager(String url, String databaseName, String username, String password){

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Database Connection to " + databaseName + " Succeeded!");
        } catch (SQLException e){
            System.out.println("Database Connection to " + databaseName + " Failed.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        this.connection.close();
    }
}
