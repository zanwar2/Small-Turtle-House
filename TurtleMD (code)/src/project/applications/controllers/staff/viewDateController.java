package project.applications.controllers.staff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.Constants;
import project.Utils.objects.ScheduleHandler;
import project.applications.controllers.patient.TimeSelectionController;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class viewDateController {
    @FXML
    private Button day1;

    @FXML
    private Button day2;

    @FXML
    private Button day3;

    @FXML
    private Button day4;

    @FXML
    private Button day5;

    @FXML
    private Button day6;

    @FXML
    private Button day7;

    @FXML
    private Button Back;

    private List<Button> btns;
    private List<Date> times;


    public void BackAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Homescreen Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public void setDate() {
        btns = new ArrayList<>(Arrays.asList(day1,day2,day3,day4,day5,day6,day7));
        times = new ArrayList<>();
        Date date = new Date(Instant.now().toEpochMilli()
                //subtract the additional milliseconds past a day by subtracting the modulus
                - (Instant.now().toEpochMilli() % Constants.DAY)
                //the time calculated above for some reason equates to 6 hours earlier
                //than intended, likely due to timezones. as a result, add 6 hours
                + (6 * Constants.HOUR));
        for(Button btn : btns) {
            date = new Date(date.getTime() + Constants.DAY);
            times.add(date);
            btn.setText(date.toString());
        }
    }

    public void DateAction(MouseEvent event) throws IOException {
        Button find = (Button) event.getSource();
        Date appointment = times.get(btns.indexOf(find));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/staff/viewTime.fxml"));
        Parent root = loader.load();
        viewTimeController timeController = loader.getController();
        timeController.setDate(appointment);

        ScheduleHandler scheduler = new ScheduleHandler(appointment);
        timeController.disableButtons(scheduler);

        Main.getPrimaryStage().setTitle("Time Selection");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
