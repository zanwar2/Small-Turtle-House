package project.applications.controllers.patient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;

public class NextAppointmentController
{
    @FXML
    private Label infoField;

    @FXML
    private Button backBtn;

    @FXML
    private Button optionBtn;

    @FXML
    private Button cancelBtn;

    public void setInfo(String text)
    {
        infoField.setText(text);
    }

    public void setOptionText(String text)
    {
        optionBtn.setText(text);
    }

    public Button getCancelBtn()
    {
        return cancelBtn;
    }

    public void cancelBtnAction(MouseEvent mouseEvent)
    {
        PatientWrapper user = (PatientWrapper) Main.getUserWrapper();
        user.setNext_appointment(null);
        user.saveChanges();
        setInfo("Appointment has been cancelled!");
    }

    public void backBtnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Home Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }


    public void optionBtnAction(MouseEvent mouseEvent) throws IOException {
        //send to scheduling page
    }
}
