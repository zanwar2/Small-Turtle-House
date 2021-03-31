package project.applications.controllers.patient;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;

/* SignOutController interacts with elements from the SignOut.fxml file */
public class SignOutController {

    //Function that saves changes made to the specific patient using main methods and signs the user out
    //Returns to the main screen and hides the confirm screen.
    public void confirmBtnAction(MouseEvent event) throws IOException {
        ((PatientWrapper) Main.getUserWrapper()).saveChanges();
        Main.setMainScreen();
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    //Function that hides the confirm screen when the no button is clicked
    public void backBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }
}