package project.Utils.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Using a driver from the "mysql-connector-java-8.0.23.jar" file to connect to the free database we used provided by
 * db4free.net. This is a requirement for the project to run as most of code is using the database to function.
 * Various methods have been made to connect the user through the main project. **/
public class DatabaseManager {

    //This Manager connects to the database, and returns connection where needed. Close at program exit.
    private Connection connection;

    /** DatabaseManager is the method that will succeed connection or fail connection for the user
     * trying to connect to the database. **/
    public DatabaseManager(String url, String databaseName, String username, String password){

        try {
            connection = DriverManager.getConnection(url + databaseName, username, password);
            System.out.println("Database Connection to " + databaseName + " Succeeded!");
        } catch (SQLException e){
            System.out.println("Database Connection to " + databaseName + " Failed.");
            e.printStackTrace();
        }
    }

    //Constructor for connecting to the database
    public Connection getConnection() {
        return connection;
    }

    //When the user exits the project it will close the connection to the database.
    public void close() throws SQLException {
        this.connection.close();
    }
}
