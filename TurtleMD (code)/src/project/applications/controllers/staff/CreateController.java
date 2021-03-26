package project.applications.controllers.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import project.Main;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.objects.Wrappers.UserWrapper;

import java.io.IOException;

//for creating new staff
public class CreateController {
    //Fields assigned to objects in fmxl
    @FXML
    private TextField fnameTxt;

    @FXML
    private TextField lnameTxt;

    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private Text fwarning;

    @FXML
    private Text lwarning;

    @FXML
    private Text uwarning;

    @FXML
    private Text pwarning;

    @FXML
    private Text succTxt;

    //This method checks the user fields to make sure the data is entered properly and then creates a new staff member and adds it to the database.
    public void createAction(MouseEvent event) throws IOException {
        String username = userTxt.getText();
        String password = passTxt.getText();
        String firstName = fnameTxt.getText();
        String lastName = lnameTxt.getText();

        if (username.length() > 64) {
            uwarning.setText("Username Too Long");
            return;
        }
        if (username.length() == 0) {
            uwarning.setText("Username Cannot Be Blank");
            return;
        }
        if (password.length() > 16) {
            pwarning.setText("Password Too Long");
            return;
        }
        if (password.length() == 0) {
            pwarning.setText("Password Cannot Be Blank");
            return;
        }
        if (Main.getUsernameHandler().containsUser(username, false)) {
            uwarning.setText("Username Already Exists");
            return;
        }
        if (firstName.length() > 32) {
            fwarning.setText("First Name Too Long");
            return;
        }
        if (firstName.length() == 0) {
            fwarning.setText("First Name Cannot Be Blank");
            return;
        }
        if (lastName.length() > 32) {
            lwarning.setText("Last Name Too Long");
            return;
        }
        if (lastName.length() == 0) {
            lwarning.setText("Last Name Cannot Be Blank");
            return;
        }
        UserWrapper wrapper = new StaffWrapper(username, password);

        wrapper.setFirst_name(firstName);
        wrapper.setLast_name(lastName);

        succTxt.setText("Staff user successfully created");

        ((StaffWrapper) wrapper).saveChanges();

    }

    public void backAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Staff Home");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

}
