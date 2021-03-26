package project.applications.controllers.patient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;

public class EditProfileController
{
    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private TextField firstTxt;

    @FXML
    private TextField lastTxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label reasonLabel;


    public void submitAction(MouseEvent mouseEvent) throws IOException {

        PatientWrapper wrapper = (PatientWrapper) Main.getUserWrapper();
        String username = userTxt.getText();
        String password = passTxt.getText();
        String first = firstTxt.getText();
        String last = lastTxt.getText();

        if(username.length() > 64){
            resultLabel.setText("Edit Failed");
            reasonLabel.setText("Username Too Long");
            return;
        }
        if(password.length() > 16){
            resultLabel.setText("Edit Failed");
            reasonLabel.setText("Password Too Long");
            return;
        }
        if(first.length() > 32){
            resultLabel.setText("Edit Failed");
            reasonLabel.setText("First Name Too Long");
            return;
        }
        if(last.length() > 32){
            resultLabel.setText("Edit Failed");
            reasonLabel.setText("Last Name Too Long");
            return;
        }

        if(username.length() != 0){
            wrapper.setUsername(username);
        }
        if(password.length() != 0){
            wrapper.setPassword(password);
        }
        if(first.length() != 0){
            wrapper.setFirst_name(first);
        }
        if(last.length() != 0){
            wrapper.setLast_name(last);
        }

        wrapper.saveChanges();

        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Home Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));

    }
}
