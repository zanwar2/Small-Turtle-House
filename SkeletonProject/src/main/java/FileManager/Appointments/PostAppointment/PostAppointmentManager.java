package main.java.FileManager.Appointments.PostAppointment;

import main.java.FileManager.Appointments.AppointmentManager;
import main.java.Users.Patient;
import java.io.File;

public class PostAppointmentManager extends AppointmentManager {

    public PostAppointmentManager(Patient user) {
        super(user);
    }

    @Override
    public void formQuestionnaire(File file) {

    }
}
