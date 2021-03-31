package project.applications.controllers.staff;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.StaffWrapper;

import java.io.IOException;

/* SignOutController interacts with elements from the staff/signout.fxml file */
public class SignOutController {

    //Saves any necessary changes to the staff members related data then sends the user back to the main login screen
    //on clicking the confirm button
    public void confirmBtnAction(MouseEvent event) throws IOException {
        ((StaffWrapper) Main.getUserWrapper()).saveChanges();
        Main.setMainScreen();
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    //hides the sign out screen after clicking the back button displayed.
    public void backBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }
}
