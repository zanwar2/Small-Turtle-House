package project.applications.controllers.patient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConfirmController {

    private QuestionnaireHandler questionnaire;
    private Timestamp next_appointment;

    public void noBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    public void yesBtnAction(MouseEvent event) throws SQLException,IOException {
        PatientWrapper wrapper =  (PatientWrapper) Main.getUserWrapper();
        wrapper.setNext_appointment(next_appointment);
        System.out.println(wrapper.getNext_appointment());
        questionnaire.saveChanges();
        wrapper.saveChanges();
        (((Node) event.getSource()).getScene().getWindow()).hide();
            Main.setNextAppointmentScreen();

    }

    public void setData(QuestionnaireHandler questionnaire, Timestamp next_appointment) {
        this.questionnaire = questionnaire;
        this.next_appointment = next_appointment;
    }
}
