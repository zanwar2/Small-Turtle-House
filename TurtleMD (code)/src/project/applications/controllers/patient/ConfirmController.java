package project.applications.controllers.patient;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.ScheduleHandler;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/* ConfirmController interacts with elements from the Confirm.fxml file */
public class ConfirmController {

    private QuestionnaireHandler questionnaire;
    private Timestamp next_appointment;
    private ScheduleHandler scheduler;


    //Function that hides the confirm screen and returns to the questionnaire screen when the no button is clicked
    public void noBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    //Function that saves the questionnaire and appointment of the current user when the yes button is clicked
    //The function uses the getUserWrapper method from main to find the specific user and saves the changes to the
    // the specifc user.
    public void yesBtnAction(MouseEvent event) throws SQLException, IOException {
        PatientWrapper wrapper =  (PatientWrapper) Main.getUserWrapper();
        wrapper.setNext_appointment(next_appointment);
        questionnaire.saveChanges();
        wrapper.saveChanges();
        scheduler.saveChanges();
        (((Node) event.getSource()).getScene().getWindow()).hide();
        Main.setHomeScreen(false);
    }

    public void setData(QuestionnaireHandler questionnaire, Timestamp next_appointment, ScheduleHandler scheduler) {
        this.questionnaire = questionnaire;
        this.next_appointment = next_appointment;
        this.scheduler = scheduler;
    }
}