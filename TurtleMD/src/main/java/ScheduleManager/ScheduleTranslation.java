package main.java.ScheduleManager;

import main.java.TurtleMD;
import main.java.storage.DatabaseManager;

public class ScheduleTranslation {

    private String schedule;
    private final DatabaseManager dbManager = TurtleMD.getDbManager();

    public ScheduleTranslation(){
        //db connection
        //read schedule from the Master Schedule, create a String that can be printed for anyone to see
    }

    public String printSchedule(){
        //print current schedule out formatted etc
        return schedule;
    }
}
