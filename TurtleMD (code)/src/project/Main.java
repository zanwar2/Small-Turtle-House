package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.objects.Wrappers.UserWrapper;
import project.Utils.storage.DatabaseManager;
import project.Utils.storage.Queries;
import project.Utils.objects.UsernameHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    private static DatabaseManager databaseManager;
    private static UsernameHandler usernameHandler;
    private static Stage stage;

    private static QuestionnaireHandler questionnaireHandler = null;
    private static UserWrapper userWrapper = null;

    @Override
    public void init() throws Exception {
        super.init();

        databaseManager = new DatabaseManager("jdbc:mysql://db4free.net:3306/", "turtlemd", "turtlemd", "SmallTurtleHouse");
        
        Connection con = databaseManager.getConnection();

        //Creating all tables
        createTables(new ArrayList<>(Arrays.asList(Queries.CREATE_STAFF_TABLE,Queries.CREATE_SCHEDULE_TABLE,Queries.CREATE_QUESTIONNAIRE_TABLE,Queries.CREATE_PATIENT_TABLE)), con);

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

    public static QuestionnaireHandler getQuestionnaireHandler() {
        return questionnaireHandler;
    }

    public static void setQuestionnaireHandler(QuestionnaireHandler questionnaireHandler) {
        Main.questionnaireHandler = questionnaireHandler;
    }

    public static Stage getPrimaryStage(){
        return stage;
    }

    private void createTable(String table, Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(table);
        stmt.execute();
        stmt.close();
    }

    private void createTables(List<String> tables, Connection con) throws SQLException {
        for(String query : tables){
            createTable(query, con);
        }
    }
}
