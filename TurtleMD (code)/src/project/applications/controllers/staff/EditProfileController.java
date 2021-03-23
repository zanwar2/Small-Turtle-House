package project.applications.controllers.staff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditProfileController {

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


    public void submitAction(MouseEvent mouseEvent) {

        String username = userTxt.getText();
        String password = passTxt.getText();
        String first = firstTxt.getText();
        String last = lastTxt.getText();

        if(username.length() == 0){
            resultLabel.setText("Edit Failed");
            reasonLabel.setText("Username must not be blank");
        }
    }


}
