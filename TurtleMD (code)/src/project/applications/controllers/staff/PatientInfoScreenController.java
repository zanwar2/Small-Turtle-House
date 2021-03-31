package project.applications.controllers.staff;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import project.Main;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;
import java.sql.SQLException;

/* PatientInfoScreenController interacts with elements from the patientinfoscreen.fxml file */
public class PatientInfoScreenController {

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    private Text patientName;
    @FXML
    private Text patientUsername;
    @FXML
    private Label appointmentTime;
    @FXML
    private Text temperature;
    @FXML
    private Text headaches;
    @FXML
    private Text coughing;
    @FXML
    private Text mucus;
    @FXML
    private Text swollen_lymph;
    @FXML
    private Text soreness;
    @FXML
    private Text nausea;
    @FXML
    private Text common_cold;
    @FXML
    private Text flu;
    @FXML
    private Text bronchitis;
    @FXML
    private Button backBtn;

    private PatientWrapper patient;
    private QuestionnaireHandler questionnaire;

    public void setPatient(PatientWrapper patient) {
        this.patient = patient;
    }

    public void setQuestionnaire() throws SQLException {
        this.questionnaire = patient.getQuestionnaire();
    }
    //returns the GUI back to the staff homescreen using the setHomeScreen method from Main.
    public void backBtnAction(MouseEvent event) throws IOException, SQLException {
        Main.setHomeScreen(true);
    }
    //Sets up the page for the patient info after retrieving related data from a specific patient user.
    public void pageSetup() throws SQLException {
        setQuestionnaire();
        patientName.setText(String.format("%s, %s", patient.getLast_name(), patient.getFirst_name()));
        patientUsername.setText(patient.getUsername());
        appointmentTime.setText(patient.getNext_appointment().toString());
        temperature.setText(String.format("%18s %d", "Temperature:", questionnaire.getTemp()));
        headaches.setText(String.format("%18s %s", "Headaches:", yesOrNo(questionnaire.getHeadahe())));
        coughing.setText(String.format("%18s %s", "Coughing:", yesOrNo(questionnaire.getCough())));
        mucus.setText(String.format("%18s %s", "Mucus:", yesOrNo(questionnaire.getMucus())));
        swollen_lymph.setText(String.format("%18s %s", "Swollen Lymphnodes:", yesOrNo(questionnaire.getLymph())));
        soreness.setText(String.format("%18s %s", "Soreness:", yesOrNo(questionnaire.getSore())));
        nausea.setText(String.format("%18s %s", "Nausea:", yesOrNo(questionnaire.getNausea())));
        common_cold.setText(String.format("%18s %s", "Common Cold:", yesOrNo(questionnaire.getCold())));
        flu.setText(String.format("%18s %s", "Flu: ", yesOrNo(questionnaire.getFlu())));
        bronchitis.setText(String.format("%18s %s", "Bronchitis", yesOrNo(questionnaire.getBronchitis())));

    }
    //Prints out "yes" or "no" depending on what boolean was inserted.
    private String yesOrNo(boolean temp) {
        if(temp)
            return "yes";
        else return "no";
    }
}
