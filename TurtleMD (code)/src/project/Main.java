package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Utils.Constants;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.objects.Wrappers.UserWrapper;
import project.Utils.storage.DatabaseManager;
import project.Utils.storage.Queries;
import project.Utils.objects.UsernameHandler;
import project.applications.controllers.general.MainController;
import project.applications.controllers.patient.NextAppointmentController;
import project.applications.controllers.patient.PatientHomeController;
import project.applications.controllers.staff.NextPatientController;
import project.applications.controllers.staff.StaffHomeController;
import project.applications.controllers.staff.viewDateController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    private static DatabaseManager databaseManager;
    private static UsernameHandler usernameHandler;
    private static Stage stage;

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
        //Adding default Questionnaire to avoid patient erroring
        QuestionnaireHandler questionnaire = new QuestionnaireHandler(1, 100, true, true, false, false, true, false, false, false, false);
        questionnaire.saveChanges();
        //Adding default patient DefaultUser with password DefaultPass, name Default Patient
        PatientWrapper defaultPatient = new PatientWrapper("DefaultUser", "DefaultPass", "Patient", "Default");
        defaultPatient.setNext_appointment(new Timestamp(Instant.now().toEpochMilli() - ((Instant.now().toEpochMilli()) % Constants.DAY) + (Constants.HOUR * 39)));
        defaultPatient.setPatient_id(1);
        defaultPatient.saveChanges();



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.stage = primaryStage;
        stage.setResizable(false);
        stage.show();
        //Read .fxml file and show it
        Main.setMainScreen();
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

    public static void setHomeScreen(boolean staff) throws IOException {
        String path = staff ? "applications/resources/fxml/staff/homescreen.fxml" : "applications/resources/fxml/patient/homescreen.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        Parent root = loader.load();
        URL imageUrl = Main.class.getResource("applications/resources/images/tmd_icon.png");
        if(staff) {
            StaffHomeController controller = loader.getController();
            controller.setImage(new Image(imageUrl.toExternalForm()));
            controller.showUser(Main.getUserWrapper().getUsername());
        }
        else {
            PatientHomeController controller = loader.getController();
            controller.setImage(new Image(imageUrl.toExternalForm()));
            controller.showUser(Main.getUserWrapper().getUsername());
        }
        stage.setTitle("Home Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }


    public static void setViewDateScreen() throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/staff/viewDate.fxml"));
        Parent root = loader.load();
        viewDateController schedulingController = loader.getController();
        schedulingController.setDate();
        Main.getPrimaryStage().setTitle("View Schedule Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public static void setNextAppointmentScreen() throws IOException {
        PatientWrapper user = (PatientWrapper) Main.getUserWrapper();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/NextAppointment.fxml"));
        Parent root = loader.load();
        NextAppointmentController appointmentController = loader.getController();
        if(user.getNext_appointment() == null) {
            appointmentController.getCancelBtn().setVisible(false);
            appointmentController.setInfo("There is no appointment Scheduled");
            appointmentController.setOptionText("Schedule");
        }
        else {
            appointmentController.getCancelBtn().setVisible(true);
            appointmentController.setInfo(user.getNext_appointment().toString());
            appointmentController.setOptionText("Reschedule");
        }
        Main.getPrimaryStage().setTitle("Next Appointment Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    //Loads the nextpatient page and transfers the first patient into the NextPatientController class - Tyler
    public static void nextPatientScreen() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/staff/nextpatient.fxml"));
        Parent root = loader.load();
        NextPatientController patientController = loader.getController();
        StaffWrapper wrapper = (StaffWrapper) Main.getUserWrapper();
        PatientWrapper patient = wrapper.getNextPatient();
        patientController.setPatient(patient);
        patientController.pageSetup();
        Main.getPrimaryStage().setTitle("Next Patient Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(400), root.prefHeight(600)));
    }

    public static void setMainScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/general/main.fxml"));
        Parent root = loader.load();
        URL imageUrl = Main.class.getResource("applications/resources/images/tmd_icon.png");
        ((MainController) loader.getController()).setImage(new Image(imageUrl.toExternalForm()));
        Main.getPrimaryStage().setTitle("Main Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
