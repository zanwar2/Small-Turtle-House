package main.java.FileManager.Appointments;

import main.java.TurtleMD;
import main.java.Users.Patient;
import main.java.storage.DatabaseManager;

import java.io.File;
import java.util.ArrayList;

public abstract class AppointmentManager {

    private Patient user;
    private final DatabaseManager dbManager = TurtleMD.getDbManager();

    public AppointmentManager(Patient user){
        this.user = user;
        //db connection
    }

    public abstract void formQuestionnaire(File file);

    public void sendToDatabase(ArrayList<String> answers){
        //code using our Queries.java to send this information to a database to store their responses for later use
    }

    public Patient getUser(){
        return this.user;
    }
}
