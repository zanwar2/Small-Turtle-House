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

    @FXML
    private CheckBox Submit;

    public void submitAction (MouseEvent event) throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/Confirm.fxml"));
        stage.setTitle("Confirm Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

}
