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

public class QuestionnaireController {

    private QuestionnaireHandler questionnaire;
    private Timestamp date;

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

    public void submitBtnAction (MouseEvent event) throws IOException
    {
        if(!QuestionnaireChecker()){
            fail.setText("Please input a whole number for temperature");
            return;
        }
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/Confirm.fxml"));
        ((ConfirmController) loader.getController()).setData(questionnaire, date);
        Parent root = loader.load();
        stage.setTitle("Confirm Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
