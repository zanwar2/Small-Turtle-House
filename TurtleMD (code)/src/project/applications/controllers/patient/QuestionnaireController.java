package project.applications.controllers.patient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Main;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import project.Utils.objects.QuestionnaireHandler;

public class QuestionnaireController {

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

        Main.setQuestionnaireHandler(new  QuestionnaireHandler(temp, headCheck, coughCheck, mucusCheck, lymphCheck, soreCheck, nauseaCheck));
        return true;
    }

    public void submitBtnAction (MouseEvent event) throws IOException
    {
        if(!QuestionnaireChecker()){
            //display a failed msg on screen
            return;
        }
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/Confirm.fxml"));
        stage.setTitle("Confirm Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }
}
