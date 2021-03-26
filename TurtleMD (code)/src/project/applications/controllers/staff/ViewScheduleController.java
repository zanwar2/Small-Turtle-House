package project.applications.controllers.staff;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import project.Main;

public class ViewScheduleController extends DatePicker{

    /*private ObjectProperty<LocalTime> timeValue = new SimpleObjectProperty<>();
    private ObjectProperty<ZonedDateTime> dateTimeValue;

    public ViewScheduleController(){
        super();
        setValue(LocalDate.now());
        setTimeValue(LocalTime.now());
        setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ssZ");
            @Override
            public String toString ( LocalDate object ) {
                return dateTimeValue.get().format(formatter);
            }
            @Override
            public LocalDate fromString ( String string ) {
                    return LocalDate.parse(string, formatter);
                }
        });
    }

    public LocalTime getTimeValue(){
        return timeValue.get();
    }

    void setTimeValue(LocalTime timeValue){
        this.timeValue.set(timeValue);
    }

    public ObjectProperty<LocalTime> timeValueProperty(){
        return timeValue;
    }


    public ZonedDateTime getDateTimeValue() {
        return dateTimeValueProperty().get();
    }


    public void setDateTimeValue (ZonedDateTime dateTimeValue) {
        dateTimeValueProperty().set(dateTimeValue);
    }

    public ObjectProperty<ZonedDateTime> dateTimeValueProperty(){
        if (dateTimeValue == null){
            dateTimeValue = new SimpleObjectProperty<>(ZonedDateTime.of(LocalDateTime.of(this.getValue(), timeValue.get()), ZoneId.systemDefault()));
            timeValue.addListener(t -> {
                dateTimeValue.set(ZonedDateTime.of(LocalDateTime.of(this.getValue(), timeValue.get()), ZoneId.systemDefault()));
            });

            valueProperty().addListener(t -> {
                dateTimeValue.set(ZonedDateTime.of(LocalDateTime.of(this.getValue(), timeValue.get()), ZoneId.systemDefault()));
            });
        }
        return dateTimeValue;
    }*/

    @FXML
    private TableView scheduleviewer;

        @FXML
        private TableColumn patient;

        @FXML
        private TableColumn appointmenttime;

        @FXML
        private TableColumn completion;

    @FXML
    private DatePicker datecompletionconfirmation;

    @FXML
    private Text completedappointment;

    @FXML
    private Button confirm;


    private boolean incompleteAppointments(){
        //if appointment is incomplete or unfulfilled, return true.
        // Else print complete in the completion column AND print "date completed" below DatePicker
        return false;
    }

    public void setScheduleviewer(){

    }

    public void backAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Staff Home");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

}

