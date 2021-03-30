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
import project.Utils.objects.Wrappers.UserWrapper;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label usernameLabel;

    
    public void signupAction(MouseEvent event) throws IOException {
        String username = userTxt.getText();
        if(username.length() > 64){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Username Too Long");
            return;
        }
        if(username.length() == 0){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Username Cannot Be Blank");
            return;
        }
        String password = passTxt.getText();
        if(password.length() > 16){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Password Too Long");
            return;
        }
        if(password.length() == 0){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Password Cannot Be Blank");
            return;
        }
        if(Main.getUsernameHandler().containsUser(username,false)){
            resultLabel.setText("Signup Failed!");
            usernameLabel.setText("Username Already Exists");
            return;
        }
        UserWrapper wrapper = new PatientWrapper(username, password);

        Main.setUserWrapper(wrapper);

        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/namescreen.fxml"));
        Main.getPrimaryStage().setTitle("Login Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public void backBtnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/general/main.fxml"));
        Main.getPrimaryStage().setTitle("Main Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
