package project.applications.controllers.patient;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.StaffWrapper;


import java.io.IOException;

public class HomeController {

    @FXML
    private Button NextAppointmentBtn;

    @FXML
    private Button editProfileBtn;

    @FXML
    private Button signOutBtn;

    @FXML
    private Button viewScheduleBtn;

    @FXML
    private Text currentUserTxt;

    //Accessing the currently logged in username to display as confirmation that the user is on the correct account -matthew
    private PatientWrapper wrapper = (PatientWrapper) Main.getUserWrapper();
    private String loggedInUser = wrapper.getUsername();

    //function for setting the text as the proper username when the class instance is created -matthew
    public void showUser()
    {
        //sets text -matthew
        currentUserTxt.setText(loggedInUser);
    }

    public void editProfileAction(MouseEvent event)
    {

    }


    public void signOutAction(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/signout.fxml"));
        stage.setTitle("Sign Out Screen");
        stage.setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        stage.setResizable(false);
        stage.show();
    }

    public void NextAppointmentBtnAction(MouseEvent mouseEvent) throws IOException
    {
        PatientWrapper user = (PatientWrapper) Main.getUserWrapper();
        if(user.getNext_appointment() == null)
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/NextAppointment.fxml"));
            Parent root = loader.load();
            NextAppointmentController appointmentController = loader.getController();
            appointmentController.getCancelBtn().setVisible(false);
            appointmentController.setInfo("There is no appointment Scheduled");
            appointmentController.setOptionText("Schedule New Appointment");
            Main.getPrimaryStage().setTitle("Next Appointment Screen");
            Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        }
        else
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/NextAppointment.fxml"));
            Parent root = loader.load();
            NextAppointmentController appointmentController = loader.getController();
            appointmentController.getCancelBtn().setVisible(true);
            appointmentController.setInfo(user.getNext_appointment().toString());
            appointmentController.setOptionText("Reschedule Appointment");
            Main.getPrimaryStage().setTitle("Next Appointment Screen");
            Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
        }
    }

    public void viewScheduleBtnAction(MouseEvent mouseEvent)
    {

    }
}
