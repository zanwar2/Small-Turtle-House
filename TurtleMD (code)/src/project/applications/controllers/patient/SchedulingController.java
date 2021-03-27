package project.applications.controllers.patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import project.Main;

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

    List<Button> button;


    public void setDates()
    {
        button = new ArrayList<>(Arrays.asList(new Button[]{Mon,Tue,Wed,Thur,Fri,Sat,Sun}));
        Date today = Date.from(Instant.now());
        for(Button btn : button)
        {
            today = new Date(today.getTime()+86400000);
            btn.setText(today.toString());
        }
    }

    public void BackAction(MouseEvent mouseEvent) throws IOException
    {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/NextAppointment.fxml"));
        Main.getPrimaryStage().setTitle("NextAppointment Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    public void DateAction(MouseEvent mouseEvent)
    {

    }

}
