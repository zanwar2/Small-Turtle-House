package project.applications.controllers.patient;

import java.sql.Date;

public class TimeSelectionController
{
    private Date date;

    public void setDate(long date) {
        this.date = new Date(date);
    }

}
