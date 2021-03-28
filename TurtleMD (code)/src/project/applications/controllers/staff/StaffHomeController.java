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
import project.applications.controllers.patient.SchedulingController;

import java.io.IOException;
import java.sql.SQLException;

public class StaffHomeController {

    @FXML
    private Text currentUserTxt;

    @FXML
    private ImageView image;

    //Accessing the currently logged in username to display as confirmation that the user is on the correct account -matthew
    private StaffWrapper wrapper = (StaffWrapper) Main.getUserWrapper();
    private String loggedInUser = wrapper.getUsername();

    //function for setting the text as the proper username when the class instance is created -matthew
    public void showUser()
    {
        //sets text -matthew
        currentUserTxt.setText(loggedInUser);
    }

    //Loads the nextpatient page and transfers the first patient into the NextPatientController class - Tyler
    public void nextPatientAction(MouseEvent event) throws IOException, SQLException {
        Main.nextPatientScreen();
    }

    public void viewScheduleAction(MouseEvent event) throws IOException {
        Main.setViewDateScreen();
    }

    public void editProfileAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/editprofile.fxml"));
        Main.getPrimaryStage().setTitle("Edit Profile Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public void signOutAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/signout.fxml"));
        stage.setTitle("Sign Out Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    public void createStaffAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/CreateNewStaff.fxml"));
        Main.getPrimaryStage().setTitle("Create new staff");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

}
