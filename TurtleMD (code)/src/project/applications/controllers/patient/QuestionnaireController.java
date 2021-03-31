package project.applications.controllers.patient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Main;
import java.io.IOException;
import java.sql.Timestamp;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import project.Utils.objects.QuestionnaireHandler;
import project.Utils.objects.ScheduleHandler;

/* QuestionnaireController interacts with elements from the Questionnaire.fxml file */
public class QuestionnaireController {

    private QuestionnaireHandler questionnaire;
    private Timestamp appointment;
    private ScheduleHandler scheduler;

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    private TextField temperature;
  
    @FXML
    private CheckBox Head;

    @FXML
    private CheckBox Mucus;

    @FXML
    private CheckBox Cough;

    @FXML
    private CheckBox Lymph;

    @FXML
    private CheckBox Sore;

    @FXML
    private CheckBox Nausea;

    @FXML
    private Label fail;

    /*
        QuestionnaireChecker() returns whether or not the answers submitted
        are valid and creates a QuestionnaireHandler() if so.
    */
    private boolean QuestionnaireChecker()
    {
        int temp;
        try {
            temp = Integer.parseInt(temperature.getText());
        } catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        boolean headCheck, mucusCheck, coughCheck, lymphCheck, soreCheck, nauseaCheck;
        headCheck = Head.isSelected();
        coughCheck = Cough.isSelected();
        mucusCheck = Mucus.isSelected();
        lymphCheck = Lymph.isSelected();
        soreCheck = Sore.isSelected();
        nauseaCheck = Nausea.isSelected();

        questionnaire = new QuestionnaireHandler(temp, headCheck, coughCheck, mucusCheck, lymphCheck, soreCheck, nauseaCheck);
        return true;
    }

    //Function for the submit button to work
    //Additionally the submit button will also work where an error is contained if the user doesn't input value in temperatures
    public void submitBtnAction (MouseEvent event) throws IOException
    {
        if(!QuestionnaireChecker()){
            fail.setText("Please input a whole number for temperature");
            return;
        }
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/Confirm.fxml"));
        Parent root = loader.load();
        ((ConfirmController) loader.getController()).setData(questionnaire, appointment, scheduler);
        stage.setTitle("Confirm Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    /* setData() takes the data from the previous screen. */
    public void setData(Timestamp appointment, ScheduleHandler scheduler) {
        this.appointment = appointment;
        this.scheduler = scheduler;
    }
}
