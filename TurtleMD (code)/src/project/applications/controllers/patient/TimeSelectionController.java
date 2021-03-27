package project.applications.controllers.patient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.Constants;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class TimeSelectionController
{
    private Date date;
    private Timestamp appointment;

    public void setDate(Date date) {
        this.date = date;
    }

    public void timeSelectionAction(MouseEvent event) throws IOException {
        long timeMS = Constants.HOUR;
        String id = ((Button) event.getSource()).getId();
        switch (id) {
            default:
                return;
            case "nineTenBtn":
                timeMS *= 9;
                System.out.println(9);
                break;
            case "tenElevenBtn":
                timeMS *= 10;
                System.out.println(10);
                break;
            case "elevenNoonBtn":
                timeMS *= 11;
                System.out.println(11);
                break;
            case "noonOneBtn":
                timeMS *= 12;
                System.out.println(12);
                break;
            case "oneTwoBtn":
                timeMS *= 13;
                System.out.println(13);
                break;
            case "twoThreeBtn":
                timeMS *= 14;
                System.out.println(14);
                break;
            case "threeFourBtn":
                timeMS *= 15;
                System.out.println(15);
                break;
            case "fourFiveBtn":
                timeMS *= 16;
                System.out.println(16);
                break;
            case "fiveSixBtn":
                timeMS *= 17;
                System.out.println(17);
                break;
        }
        appointment = new Timestamp(date.getTime() + timeMS);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("applications/resources/fxml/patient/Questionnaire.fxml"));
        Parent root = loader.load();
        ((QuestionnaireController) loader.getController()).setDate(appointment);
        Main.getPrimaryStage().setTitle("Confirm Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));

    }

    public void cancelAction(MouseEvent event) throws IOException {
        Main.setNextAppointmentScreen();
    }
}
