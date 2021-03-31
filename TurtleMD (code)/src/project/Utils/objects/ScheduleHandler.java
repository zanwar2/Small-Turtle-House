package project.Utils.objects;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.*;

/*
    This handler handles the Schedule to and from the database.
 */
public class ScheduleHandler {

    private Connection con;

    private Date date;
    private Boolean nineAM = true;
    private Boolean tenAM = true;
    private Boolean elevenAM = true;
    private Boolean noon = true;
    private Boolean onePM = true;
    private Boolean twoPM = true;
    private Boolean threePM = true;
    private Boolean fourPM = true;
    private Boolean fivePM = true;

    /* Constructors based on information given */
    public ScheduleHandler(Date date) {
        this.date = date;
        con = Main.getDatabaseManager().getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(Queries.GET_DAY);
            stmt.setDate(1, date);
            ResultSet rS = stmt.executeQuery();
            rS.next();
            this.nineAM = rS.getBoolean(2);
            this.tenAM = rS.getBoolean(3);
            this.elevenAM = rS.getBoolean(4);
            this.noon = rS.getBoolean(5);
            this.onePM = rS.getBoolean(6);
            this.twoPM = rS.getBoolean(7);
            this.threePM = rS.getBoolean(8);
            this.fourPM = rS.getBoolean(9);
            this.fivePM = rS.getBoolean(10);
            stmt.close();
        } catch (SQLException e){}
    }

    /* saveChanges() saves the current information to the database */
    public void saveChanges() throws SQLException {
        PreparedStatement stmt = con.prepareStatement(Queries.SAVE_DAY);
        stmt.setDate(1, date);
        stmt.setBoolean(2, nineAM);
        stmt.setBoolean(3, tenAM);
        stmt.setBoolean(4, elevenAM);
        stmt.setBoolean(5, noon);
        stmt.setBoolean(6, onePM);
        stmt.setBoolean(7, twoPM);
        stmt.setBoolean(8, threePM);
        stmt.setBoolean(9, fourPM);
        stmt.setBoolean(10, fivePM);
        stmt.execute();
        stmt.close();
    }

    public Boolean getNineAM() {
        return nineAM;
    }

    public void setNineAM(Boolean nineAM) {
        this.nineAM = nineAM;
    }

    public Boolean getTenAM() {
        return tenAM;
    }

    public void setTenAM(Boolean tenAM) {
        this.tenAM = tenAM;
    }

    public Boolean getElevenAM() {
        return elevenAM;
    }

    public void setElevenAM(Boolean elevenAM) {
        this.elevenAM = elevenAM;
    }

    public Boolean getNoon() {
        return noon;
    }

    public void setNoon(Boolean noon) {
        this.noon = noon;
    }

    public Boolean getOnePM() {
        return onePM;
    }

    public void setOnePM(Boolean onePM) {
        this.onePM = onePM;
    }

    public Boolean getTwoPM() {
        return twoPM;
    }

    public void setTwoPM(Boolean twoPM) {
        this.twoPM = twoPM;
    }

    public Boolean getThreePM() {
        return threePM;
    }

    public void setThreePM(Boolean threePM) {
        this.threePM = threePM;
    }

    public Boolean getFourPM() {
        return fourPM;
    }

    public void setFourPM(Boolean fourPM) {
        this.fourPM = fourPM;
    }

    public Boolean getFivePM() {
        return fivePM;
    }

    public void setFivePM(Boolean fivePM) {
        this.fivePM = fivePM;
    }
}
