package project.Utils.objects.Wrappers;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.*;

public class PatientWrapper extends UserWrapper {

    private Timestamp next_appointment;
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

    public PatientWrapper(String username, String password, String last_name, String first_name, Timestamp next_appointment, int patient_id){
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
            stmt.setTimestamp(5,this.next_appointment);
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

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Timestamp getNext_appointment() {
        return next_appointment;
    }

    public void setNext_appointment(Timestamp next_appointment) {
        this.next_appointment = next_appointment;
    }

}
