package project.applications.controllers.staff;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.StaffWrapper;

import java.io.IOException;

public class SignOutController {

    public void confirmBtnAction(MouseEvent event) throws IOException {
        ((StaffWrapper) Main.getUserWrapper()).saveChanges();
        Main.setMainScreen();
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    public void backBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }
}
