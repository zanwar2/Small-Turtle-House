package project.Utils.objects.Wrappers;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientWrapper extends UserWrapper {

    private Date next_appointment;
    private Integer patient_id;

    public PatientWrapper(String username, String password){
        super(username,password,null,null);
        this.next_appointment = null;
        this.patient_id = null;
    }

    public PatientWrapper(String username, String password, String last_name, String first_name){
        super(username,password,last_name,first_name);
        this.next_appointment = null;
        this.patient_id = null;
    }

    public PatientWrapper(String username, String password, String last_name, String first_name, Date next_appointment, int patient_id){
        super(username,password,last_name,first_name);
        this.next_appointment = next_appointment;
        this.patient_id = patient_id;
    }

    public void saveChanges(){
        try{
            Connection con = Main.getDatabaseManager().getConnection();
            PreparedStatement stmt = con.prepareStatement(Queries.SAVE_PATIENTS);
            stmt.setString(1,this.getUsername());
            stmt.setString(2,this.getPassword());
            stmt.setString(3,this.getLast_name());
            stmt.setString(4,this.getFirst_name());
            stmt.setDate(5,this.next_appointment);
            stmt.execute();
            if(patient_id != null){
                stmt = con.prepareStatement(Queries.ADD_PATIENT_ID);
                stmt.setInt(1,this.patient_id);
                stmt.setString(2,this.getUsername());
            }
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        Main.getUsernameHandler().reloadPatientUsers();
    }
}
