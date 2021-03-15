package project.Utils.objects;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsernameHandler {

    private List<String> staff_usernames = new ArrayList<>();
    private List<String> patient_usernames = new ArrayList<>();

    //This handler makes a database connection to get a list of all staff usernames to avoid needless DB Queries later. Will expand later.
    public UsernameHandler() {
        try {
            Connection con = Main.getDatabaseManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(Queries.GET_STAFF_USERNAMES);
            ResultSet rS = stmt.executeQuery();
            while(rS.next()){
                this.staff_usernames.add(rS.getString(1));
            }
            stmt = con.prepareStatement(Queries.GET_PATIENT_USERNAMES);
            rS = stmt.executeQuery();
            while(rS.next()){
                this.patient_usernames.add(rS.getString(1));
            }
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<String> getStaff_Usernames(){
        return this.staff_usernames;
    }

    public List<String> getPatient_Usernames(){
        return this.patient_usernames;
    }

    public boolean containsUser(String username, boolean staff){
        return staff ? this.staff_usernames.contains(username) : this.patient_usernames.contains(username);
    }

    public void reloadPatientUsers(){
        try {
            PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_PATIENT_USERNAMES);
            ResultSet rS = stmt.executeQuery();
            this.patient_usernames.clear();
            while(rS.next()){
                this.patient_usernames.add(rS.getString(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
}

    public void reloadStaffUsers() {
        try {
            PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_STAFF_USERNAMES);
            ResultSet rS = stmt.executeQuery();
            this.staff_usernames.clear();
            while (rS.next()) {
                this.staff_usernames.add(rS.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
