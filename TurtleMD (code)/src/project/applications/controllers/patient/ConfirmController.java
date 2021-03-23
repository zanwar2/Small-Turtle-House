package project.applications.controllers.patient;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class ConfirmController
{
    @FXML
    private Button yes;

    @FXML
    private Button no;

    public void yesBtnAction(MouseEvent event)
    {

    }

    public void noBtnAction(MouseEvent event) {
        (((Node) event.getSource()).getScene().getWindow()).hide();
    }
}
