package project.applications.controllers.patient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Main;

import java.io.IOException;

public class HomeController {

    public void nextPatientAction(MouseEvent event) {
    }

    public void viewScheduleAction(MouseEvent event) {
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

}
