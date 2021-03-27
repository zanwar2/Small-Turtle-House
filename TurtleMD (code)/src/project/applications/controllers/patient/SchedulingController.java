package project.applications.controllers.patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class SchedulingController
{

    @FXML
    private Button Back;

    @FXML
    private Button Mon;

    @FXML
    private Button Tue;

    @FXML
    private Button Wed;

    @FXML
    private Button Thur;

    @FXML
    private Button Fri;

    @FXML
    private Button Sat;

    @FXML
    private Button Sun;

    private List<Button> button;

    private List<Date> times;

    private Date date;


    public void setDates() {
        button = new ArrayList<>(Arrays.asList(new Button[]{Mon,Tue,Wed,Thur,Fri,Sat,Sun}));
        times = new ArrayList<>();
        int i =0;
        Date today = Date.from(Instant.now());
        for(Button btn : button) {
            today = new Date(today.getTime()+86400000);
            times.add(today);
            btn.setText(today.toString());
            i++;
        }
    }

    public void BackAction(MouseEvent mouseEvent) throws IOException {
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

    public void DateAction(MouseEvent mouseEvent) throws IOException {
        Button find = (Button) mouseEvent.getSource();
        System.out.println(find.getId() + " qq");
        Date date = times.get(button.indexOf(find));
        System.out.println(date);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/TimeSelection.fxml"));
        Parent root = loader.load();
        TimeSelectionController timeController = loader.getController();
        timeController.setDate(date.getTime());
        Main.getPrimaryStage().setTitle("Time Selection");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));

    }

}
