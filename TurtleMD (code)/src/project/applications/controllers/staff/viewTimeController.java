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
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.storage.Queries;

import java.io.IOException;
import java.sql.*;

public class viewTimeController {
    private Date date;
    private Timestamp appointment;
    private ScheduleHandler scheduler;

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

    public void timeSelectionAction(MouseEvent event) throws IOException, SQLException {
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
        PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_PATIENT_BY_TIME);
        stmt.setTimestamp(1,appointment);
        ResultSet rS = stmt.executeQuery();
        PatientWrapper patient = null;
        if(rS.next())
            patient = new PatientWrapper(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getTimestamp(5), rS.getInt(6));
        stmt.close();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/staff/patientinfoscreen.fxml"));
        Parent root = loader.load();
        PatientInfoScreenController patientInfoScreenController = loader.getController();
        patientInfoScreenController.setPatient(patient);
        patientInfoScreenController.pageSetup();
        Main.getPrimaryStage().setTitle("Patient Info");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(400), root.prefHeight(600)));

    }

    public void returnAction(MouseEvent event) throws IOException {
        Main.setViewDateScreen();
    }

    public void disableButtons(ScheduleHandler scheduler) {
        this.scheduler = scheduler;
        if(scheduler.getNineAM())
            nineTenBtn.setDisable(true);
        if(scheduler.getTenAM())
            tenElevenBtn.setDisable(true);
        if(scheduler.getElevenAM())
            elevenNoonBtn.setDisable(true);
        if(scheduler.getNoon())
            noonOneBtn.setDisable(true);
        if(scheduler.getOnePM())
            oneTwoBtn.setDisable(true);
        if(scheduler.getTwoPM())
            twoThreeBtn.setDisable(true);
        if(scheduler.getThreePM())
            threeFourBtn.setDisable(true);
        if(scheduler.getFourPM())
            fourFiveBtn.setDisable(true);
        if(scheduler.getFivePM())
            fiveSixBtn.setDisable(true);

    }
}
