package project.applications.controllers.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.StaffWrapper;
import project.Utils.objects.Wrappers.UserWrapper;
import project.applications.controllers.patient.SchedulingController;

import java.io.IOException;
import java.sql.SQLException;

/* StaffHomeController interacts with elements from the staff/homescreen.fxml file */
public class StaffHomeController {

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    private Text currentUserTxt;

    @FXML
    private ImageView image;

    //function for setting the text as the proper username when the class instance is created
    public void showUser(String loggedInUser) {
        //sets text
        currentUserTxt.setText(loggedInUser);
    }

    //Loads the nextpatient page and transfers the first patient into the NextPatientController class
    public void nextPatientAction(MouseEvent event) throws IOException, SQLException {
        Main.nextPatientScreen();
    }
    /* viewScheduleAction() listens for viewScheduleBtn to be clicked. Proceeds to view date screen */
    public void viewScheduleAction(MouseEvent event) throws IOException {
        Main.setViewDateScreen();
    }

    /* editProfileAction() listens for editProfileBtn to be clicked. Proceeds to edit profile screen */
    public void editProfileAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/editprofile.fxml"));
        Main.getPrimaryStage().setTitle("Edit Profile Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    /* signOutAction() listens for signOutBtn to be clicked. Proceeds to sign out screen */
    public void signOutAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/signout.fxml"));
        stage.setTitle("Sign Out Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    /* createStaffAction() listens for createStaffBtn to be clicked. Proceeds to staff creation screen */
    public void createStaffAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/CreateNewStaff.fxml"));
        Main.getPrimaryStage().setTitle("Create new staff");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    /* sets the image on the page */
    public void setImage(Image image) {
        this.image.setImage(image);
    }

}
