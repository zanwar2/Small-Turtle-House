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

public class PatientHomeController {

    @FXML
    private Text currentUserTxt;

    @FXML
    private ImageView image;

    //function for setting the text as the proper username when the class instance is created -matthew
    public void showUser(String loggedInUser) {
        //sets text -matthew
        currentUserTxt.setText(loggedInUser);
    }

    public void editProfileAction(MouseEvent event) {

    }


    public void signOutAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/signout.fxml"));
        stage.setTitle("Sign Out Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    public void NextAppointmentBtnAction(MouseEvent mouseEvent) throws IOException {
        Main.setNextAppointmentScreen();
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }
}
