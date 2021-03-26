package project.applications.controllers.staff;

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

    public void viewScheduleAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/viewschedule.fxml"));
        Main.getPrimaryStage().setTitle("View Schedule");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(400), root.prefHeight(600)));
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

}
