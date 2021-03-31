package project.applications.controllers.patient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;

/* NamesController interacts with elements from the namescreen.fxml file */
public class NamesController {
    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    private TextField firstTxt;

    @FXML
    private TextField lastTxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label nameLabel;

    /* submitAction() listens for submitBtn to be clicked. Attempts to submit information */
    public void submitAction(MouseEvent event) throws IOException {
        String firstName = firstTxt.getText();
        if (firstName.length() > 32) {
            resultLabel.setText("Signup Failed!");
            nameLabel.setText("First Name Too Long");
            return;
        }
        if (firstName.length() == 0) {
            resultLabel.setText("Signup Failed!");
            nameLabel.setText("First Name Cannot Be Blank");
            return;
        }
        String lastName = lastTxt.getText();
        if (lastName.length() > 32) {
            resultLabel.setText("Signup Failed!");
            nameLabel.setText("Last Name Too Long");
            return;
        }
        if (lastName.length() == 0) {
            resultLabel.setText("Signup Failed!");
            nameLabel.setText("Last Name Cannot Be Blank");
            return;
        }
        PatientWrapper wrapper = (PatientWrapper) Main.getUserWrapper();
        wrapper.setFirst_name(firstName);
        wrapper.setLast_name(lastName);
        wrapper.saveChanges();

        Main.setHomeScreen(false);
    }
}
