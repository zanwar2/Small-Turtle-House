package project.Utils.objects.Wrappers;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffWrapper extends UserWrapper {

    /*
        This is a Wrapper class to handle any Staff Members from
        the database, or to put into the database.
    */


    /* Two Constructors based on information we have/need. */
    public StaffWrapper(String username, String password){
        super(username,password,null,null);
    }

    public StaffWrapper(String username, String password, String last_name, String first_name){
        super(username,password,last_name,first_name);
    }

    /* saveChanges() saves the current information to the database */
    public void saveChanges(){
        try{
            Connection con = Main.getDatabaseManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(Queries.SAVE_STAFF);
            stmt.setString(1,this.getUsername());
            stmt.setString(2,this.getPassword());
            stmt.setString(3,this.getLast_name());
            stmt.setString(4,this.getFirst_name());
            stmt.execute();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        Main.getUsernameHandler().reloadStaffUsers();
    }

    /*
        getNextPatient() gets the information of the patient with
        the next appointment.
     */
    public PatientWrapper getNextPatient() throws SQLException {
        PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_NEXT_APPOINTMENT);
        ResultSet rS = stmt.executeQuery();
        rS.next();
        PatientWrapper patient = new PatientWrapper(rS.getString(1), rS.getString(2), rS.getString(3), rS.getString(4), rS.getTimestamp(5), rS.getInt(6));
        stmt.close();
        return patient;
    }
}


