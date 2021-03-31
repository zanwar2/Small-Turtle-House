package project.applications.controllers.patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.Constants;
import project.Utils.objects.ScheduleHandler;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* SchedulingController interacts with elements from the Scheduling.fxml file */
public class SchedulingController
{
    private List<Button> button;
    private List<Date> times;

    /* @FXML allows you to register fields directly from .fxml file */
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

    /* setDates() sets dates on the buttons */
    public void setDates() {
        button = new ArrayList<>(Arrays.asList(Mon,Tue,Wed,Thur,Fri,Sat,Sun));
        times = new ArrayList<>();
        Date date = new Date(Instant.now().toEpochMilli()
                //subtract the additional milliseconds past a day by subtracting the modulus
                - (Instant.now().toEpochMilli() % Constants.DAY)
                //the time calculated above for some reason equates to 6 hours earlier
                //than intended, likely due to timezones. as a result, add 6 hours
                + (6 * Constants.HOUR));
        for(Button btn : button) {
            date = new Date(date.getTime() + Constants.DAY);
            times.add(date);
            btn.setText(date.toString());
        }
    }

    /* BackAction() listens for BackBtn to be clicked. Goes to previous screen. */
    public void BackAction(MouseEvent event) throws IOException {
        Main.setNextAppointmentScreen();
    }

    /* DateAction() listens for any date button to be clicked. Sets the date and proceeds to time selection. */
    public void DateAction(MouseEvent event) throws IOException {
        Button find = (Button) event.getSource();
        Date appointment = times.get(button.indexOf(find));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/TimeSelection.fxml"));
        Parent root = loader.load();
        TimeSelectionController timeController = loader.getController();
        timeController.setDate(appointment);

        ScheduleHandler scheduler = new ScheduleHandler(appointment);
        timeController.disableButtons(scheduler);

        Main.getPrimaryStage().setTitle("Time Selection");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

}
