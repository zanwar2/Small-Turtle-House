package project.applications.controllers.staff;

import javafx.fxml.FXML;
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

        //this chunk of code gets the strings from the text boxes in the program and assigns them to variables in the method
        String username = userTxt.getText();
        String password = passTxt.getText();
        String firstName = fnameTxt.getText();
        String lastName = lnameTxt.getText();

        //making the checks to see if user entered values are valid, then alerting them if they aren't
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
        if (username.length() > 64) {
            uwarning.setText("Username Too Long");
            return;
        }
        if (username.length() == 0) {
            uwarning.setText("Username Cannot Be Blank");
            return;
        }
        if (Main.getUsernameHandler().containsUser(username, false)) {
            uwarning.setText("Username Already Exists");
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

        //creates a wrapper to enter the information into the database
        UserWrapper wrapper = new StaffWrapper(username, password);

        //setting first and last name
        wrapper.setFirst_name(firstName);
        wrapper.setLast_name(lastName);

        //alerting user
        succTxt.setText("Staff user successfully created");

        //saving changes to database
        ((StaffWrapper) wrapper).saveChanges();

    }

    //this controls the back button, changes javafx scene to the home screen for staff
    public void backAction(MouseEvent event) throws IOException {
        Main.setHomeScreen(true);
    }


}
