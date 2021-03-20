package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.objects.Wrappers.UserWrapper;
import project.Utils.storage.DatabaseManager;
import project.Utils.storage.Queries;
import project.Utils.objects.UsernameHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Main extends Application {

    private static DatabaseManager databaseManager;
    private static UsernameHandler usernameHandler;
    private static UserWrapper userWrapper = null;
    private static Stage stage;

    @Override
    public void init() throws Exception {
        super.init();

        databaseManager = new DatabaseManager("jdbc:mysql://db4free.net:3306/", "turtlemd", "turtlemd", "SmallTurtleHouse");
//        databaseManager = new DatabaseManager(url, databaseName, username, password);
//        should look similar to this:
//        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/", "testing_java", "root", "password");
//        gonna try to set this up remotely, but for now it's local
        Connection con = databaseManager.getConnection();
        //Creating table of staff
        PreparedStatement stmt = con.prepareStatement(Queries.CREATE_STAFF_TABLE);
        stmt.execute();

        stmt = con.prepareStatement(Queries.CREATE_SCHEDULE_TABLE);
        stmt.execute();

        stmt = con.prepareStatement(Queries.CREATE_QUESTIONNAIRE_TABLE);
        stmt.execute();
        //Creating table of patients
        stmt = con.prepareStatement(Queries.CREATE_PATIENT_TABLE);
        stmt.execute();

        stmt.close();
        
        //Setting up the UsernameHandler to avoid needless DB Queries after program starts
        usernameHandler = new UsernameHandler();

        //Adding default staff TurtleMD with password SmallTurtleHouse, name Franklin the Turtle
        StaffWrapper defaultStaff = new StaffWrapper("TurtleMD", "SmallTurtleHouse", "the Turtle", "Franklin");
        defaultStaff.saveChanges();
        //Adding default patient DefaultUser with password DefaultPass, name Default Patient
        PatientWrapper defaultPatient = new PatientWrapper("DefaultUser", "DefaultPass", "Patient", "Default");
        defaultPatient.saveChanges();



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.stage = primaryStage;
        //Read .fxml file and show it
        Parent root = FXMLLoader.load(getClass().getResource("applications/resources/fxml/general/main.fxml"));
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        databaseManager.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static UsernameHandler getUsernameHandler() {
        return usernameHandler;
    }

    public static UserWrapper getUserWrapper() {
        return userWrapper;
    }

    public static void setUserWrapper(UserWrapper userWrapper) {
        Main.userWrapper = userWrapper;
    }

    public static Stage getPrimaryStage(){
        return stage;
    }
}
