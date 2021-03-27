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

public class ConfirmController {

    private QuestionnaireHandler questionnaire;
    private Timestamp next_appointment;
    private ScheduleHandler scheduler;

    public void noBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

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