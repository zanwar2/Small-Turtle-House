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
import project.Utils.storage.Queries;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void cancelBtnAction(MouseEvent mouseEvent) throws SQLException {
        PatientWrapper user = (PatientWrapper) Main.getUserWrapper();
        PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.REMOVE_FROM_MASTER_SCHEDULE);
        stmt.setDate(1, new Date(user.getNext_appointment().getTime()));
        stmt.execute();
        stmt.close();
        user.setNext_appointment(null);
        user.saveChanges();
        setInfo("Appointment has been cancelled!");

    }

    public void backBtnAction(MouseEvent mouseEvent) throws IOException {
        Main.setHomeScreen(false);
    }


    public void optionBtnAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/Scheduling.fxml"));
        Parent root = loader.load();
        SchedulingController schedulingController = loader.getController();
        schedulingController.setDates();
        Main.getPrimaryStage().setTitle("Scheduling Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
