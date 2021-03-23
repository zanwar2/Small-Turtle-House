package project.applications.controllers.patient;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ConfirmController
{

    public void noBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }

    public void yesBtnAction(MouseEvent mouseEvent) {
    }
}
