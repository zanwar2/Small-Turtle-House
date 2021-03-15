package project.applications.controllers.patient;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

public class SignOutController {

    public void confirmBtnAction(MouseEvent event) {
        ((PatientWrapper) Main.getUserWrapper()).saveChanges();
        Main.getPrimaryStage().hide();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void backBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }
}