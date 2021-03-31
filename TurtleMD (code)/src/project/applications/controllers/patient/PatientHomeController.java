package project.applications.controllers.patient;

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


import java.io.IOException;

/* PatientHomeController interacts with elements from the patient/homescreen.fxml file */
public class PatientHomeController {

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    private Text currentUserTxt;

    @FXML
    private ImageView image;

    //function for setting the text as the proper username when the class instance is created -matthew
    public void showUser(String loggedInUser) {
        //sets text -matthew
        currentUserTxt.setText(loggedInUser);
    }

    //Function that changes the screen to the edit profile screen when clicked.
    public void editProfileAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/editprofile.fxml"));
        Main.getPrimaryStage().setTitle("Edit Profile Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    //Function that brings out a confirm pop out screen that allows user to sign out
    public void signOutAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/signout.fxml"));
        stage.setTitle("Sign Out Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    //function that uses a method from main that sets up the next appointment screen when the next appointment button is clicked.
    public void NextAppointmentBtnAction(MouseEvent mouseEvent) throws IOException {
        Main.setNextAppointmentScreen();
    }

    //function that sets the TurtleMD icon when called.
    public void setImage(Image image) {
        this.image.setImage(image);
    }
}
