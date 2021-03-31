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
import java.sql.Timestamp;

/* TimeSelectionController interacts with elements from the TimeSelection.fxml file */
public class TimeSelectionController
{
    private Date date;
    private Timestamp appointment;
    private ScheduleHandler scheduler;

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    Button nineTenBtn;

    @FXML
    Button tenElevenBtn;

    @FXML
    Button elevenNoonBtn;

    @FXML
    Button noonOneBtn;

    @FXML
    Button oneTwoBtn;

    @FXML
    Button twoThreeBtn;

    @FXML
    Button threeFourBtn;

    @FXML
    Button fourFiveBtn;

    @FXML
    Button fiveSixBtn;

    public void setDate(Date date) {
        this.date = date;
    }

    /*
        timeSelectionAction() listens for timeSelectionBtn to be clicked.
        Sets the time and removes the availability of the timeslot
    */
    public void timeSelectionAction(MouseEvent event) throws IOException {
        long timeMS = Constants.HOUR;
        String id = ((Button) event.getSource()).getId();
        switch (id) {
            default:
                return;
            case "nineTenBtn":
                timeMS *= 9;
                scheduler.setNineAM(false);
                System.out.println(scheduler.getNineAM());
                break;
            case "tenElevenBtn":
                timeMS *= 10;
                scheduler.setTenAM(false);
                break;
            case "elevenNoonBtn":
                timeMS *= 11;
                scheduler.setElevenAM(false);
                break;
            case "noonOneBtn":
                timeMS *= 12;
                scheduler.setNoon(false);
                break;
            case "oneTwoBtn":
                timeMS *= 13;
                scheduler.setOnePM(false);
                break;
            case "twoThreeBtn":
                timeMS *= 14;
                scheduler.setTwoPM(false);
                break;
            case "threeFourBtn":
                timeMS *= 15;
                scheduler.setThreePM(false);
                break;
            case "fourFiveBtn":
                timeMS *= 16;
                scheduler.setFourPM(false);
                break;
            case "fiveSixBtn":
                timeMS *= 17;
                scheduler.setFivePM(false);
                break;
        }
        appointment = new Timestamp(date.getTime() + timeMS);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/Questionnaire.fxml"));
        Parent root = loader.load();
        ((QuestionnaireController) loader.getController()).setData(appointment, scheduler);
        Main.getPrimaryStage().setTitle("Confirm Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));

    }

    /* cancelAction() listens for cancelBtn to be clicked. Goes back to the previous screen. */
    public void cancelAction(MouseEvent event) throws IOException {
        Main.setNextAppointmentScreen();
    }

    /* Disables buttons with unavailable timeslots. */
    public void disableButtons(ScheduleHandler scheduler) {
        this.scheduler = scheduler;
        if(!scheduler.getNineAM())
            nineTenBtn.setDisable(true);
        if(!scheduler.getTenAM())
            tenElevenBtn.setDisable(true);
        if(!scheduler.getElevenAM())
            elevenNoonBtn.setDisable(true);
        if(!scheduler.getNoon())
            noonOneBtn.setDisable(true);
        if(!scheduler.getOnePM())
            oneTwoBtn.setDisable(true);
        if(!scheduler.getTwoPM())
            twoThreeBtn.setDisable(true);
        if(!scheduler.getThreePM())
            threeFourBtn.setDisable(true);
        if(!scheduler.getFourPM())
            fourFiveBtn.setDisable(true);
        if(!scheduler.getFivePM())
            fiveSixBtn.setDisable(true);

    }
}
