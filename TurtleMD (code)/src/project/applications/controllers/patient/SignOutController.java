package project.applications.controllers.patient;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;

public class SignOutController {

    public void confirmBtnAction(MouseEvent event) throws IOException {
        ((PatientWrapper) Main.getUserWrapper()).saveChanges();
        Main.setMainScreen();
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    public void backBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }
}