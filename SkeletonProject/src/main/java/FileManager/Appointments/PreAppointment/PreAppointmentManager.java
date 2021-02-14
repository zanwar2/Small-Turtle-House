package main.java.FileManager.Appointments.PreAppointment;

import main.java.FileManager.Appointments.AppointmentManager;
import main.java.Users.Patient;
import java.io.File;

public class PreAppointmentManager extends AppointmentManager {

    public PreAppointmentManager(Patient user) {
        super(user);
    }

    @Override
    public void formQuestionnaire(File file) {

    }

}
