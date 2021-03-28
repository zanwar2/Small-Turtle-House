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

import java.io.IOException;
import java.sql.SQLException;

public class NextPatientController {

    //FXML Stuff - Tyler
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

    //Private Instances - Tyler
    private PatientWrapper nextPatient;
    private StaffWrapper staff = (StaffWrapper) Main.getUserWrapper();

    //Sets the next patient - Tyler
    public void setPatient(PatientWrapper patient) {
        this.nextPatient = patient;
    }

    //Sets the time into infoFieldNPC - Tyler
    public void setInfo(String text) {
        infoFieldNPC.setText(text);
    }

    //Gets the cancel button... don't know if it is in use anymore - Tyler
    public Button getCancelBtn()
    {
        return cancelBtnNPC;
    }

    //Gets the initial starting page which sets the name and time of the next patient and does some
    //visibility with some buttons - Tyler
    public void pageSetup() {
        cancelBtnNPC.setVisible(true);
        confirmBtnNPC.setVisible(false);
        patientNameNPC.setText(String.format("%s, %s", nextPatient.getLast_name(), nextPatient.getFirst_name()));
        setInfo(nextPatient.getNext_appointment().toString());
    }

    //Returns back to the staff home screen - Tyler
    public void backBtnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Home Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    //Makes the current patient's appointment null, does some visibility stuff to show that you have cancelled
    //and makes sure you know that you have cancelled that patient's appointment - Tyler
    public void cancelBtnAction(MouseEvent mouseEvent) {
        nextPatient.setNext_appointment(null);
        nextPatient.saveChanges();
        infoFieldNPC.setText("Appointment Cancelled");
        cancelBtnNPC.setVisible(false);
        patientInfoNPC.setVisible(false);
        confirmBtnNPC.setVisible(true);
    }

    //Gets the next patient according to time, and resets up the page - Tyler
    public void confirmBtnAction(MouseEvent mouseEvent) throws SQLException {
        setPatient(staff.getNextPatient());
        patientInfoNPC.setVisible(true);
        pageSetup();
    }

    public void patientInfoAction(MouseEvent mouseEvent) {

    }
}
