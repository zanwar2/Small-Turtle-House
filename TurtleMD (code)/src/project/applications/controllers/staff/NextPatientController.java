package project.applications.controllers.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.storage.Queries;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* NextPatientController interacts with elements from the nextpatient.fxml file */
public class NextPatientController {

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    private Text patientNameNPC;
    @FXML
    private Label infoFieldNPC;
    @FXML
    private Button patientInfoNPC;
    @FXML
    private Button backBtnNPC;
    @FXML
    private Button cancelBtnNPC;
    @FXML
    private Button confirmBtnNPC;

    //Private Instances
    private PatientWrapper nextPatient;
    private StaffWrapper staff = (StaffWrapper) Main.getUserWrapper();

    //Sets the next patient
    public void setPatient(PatientWrapper patient) {
        this.nextPatient = patient;
    }

    //Sets the time into infoFieldNPC
    public void setInfo(String text) {
        infoFieldNPC.setText(text);
    }

    //Gets the cancel button... don't know if it is in use anymore
    public Button getCancelBtn()
    {
        return cancelBtnNPC;
    }

    //Gets the initial starting page which sets the name and time of the next patient and does some
    //visibility with some buttons
    public void pageSetup() {
        cancelBtnNPC.setVisible(true);
        confirmBtnNPC.setVisible(false);
        patientNameNPC.setText(String.format("%s, %s", nextPatient.getLast_name(), nextPatient.getFirst_name()));
        setInfo(nextPatient.getNext_appointment().toString());
    }

    //Returns back to the staff home screen
    public void backBtnAction(MouseEvent mouseEvent) throws IOException {
        Main.setHomeScreen(true);
    }

    //Makes the current patient's appointment null, does some visibility stuff to show that you have cancelled
    //and makes sure you know that you have cancelled that patient's appointment
    public void cancelBtnAction(MouseEvent mouseEvent) throws SQLException {
        PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.REMOVE_FROM_MASTER_SCHEDULE);
        stmt.setDate(1, new Date(nextPatient.getNext_appointment().getTime()));
        stmt.execute();
        stmt.close();
        nextPatient.setNext_appointment(null);
        nextPatient.saveChanges();
        infoFieldNPC.setText("Appointment Cancelled");
        cancelBtnNPC.setVisible(false);
        patientInfoNPC.setVisible(false);
        confirmBtnNPC.setVisible(true);
    }

    //Gets the next patient according to time, and resets up the page
    public void confirmBtnAction(MouseEvent mouseEvent) throws SQLException {
        setPatient(staff.getNextPatient());
        patientInfoNPC.setVisible(true);
        pageSetup();
    }
    //Function displays the patient info screen and sets the patient using the set patient method from the patientinfo
    //Controller.
    public void patientInfoAction(MouseEvent mouseEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/staff/patientinfoscreen.fxml"));
        Parent root = loader.load();
        PatientInfoScreenController patientInfoScreenController = loader.getController();
        patientInfoScreenController.setPatient(nextPatient);
        patientInfoScreenController.pageSetup();
        Main.getPrimaryStage().setTitle("Patient Info");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(400), root.prefHeight(600)));
    }
}
