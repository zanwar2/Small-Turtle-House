package project.applications.controllers.patient;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.sql.SQLException;
import java.sql.Timestamp;

public class ConfirmController {

    private QuestionnaireHandler questionnaire;
    private Timestamp next_appointment;

    public void noBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    public void yesBtnAction(MouseEvent event) throws SQLException {
        Main.setQuestionnaireHandler(questionnaire);
        questionnaire.saveChanges();
        PatientWrapper wrapper =  (PatientWrapper) Main.getUserWrapper();
        wrapper.setNext_appointment(next_appointment);
        wrapper.saveChanges();

    }

    public void setData(QuestionnaireHandler questionnaire, Timestamp date) {
        this.questionnaire = questionnaire;
        this.next_appointment = date;
    }
}
