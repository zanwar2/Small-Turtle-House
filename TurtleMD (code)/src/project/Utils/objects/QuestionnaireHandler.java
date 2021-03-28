package project.Utils.objects;

import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.storage.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionnaireHandler {

    private Integer patient_id;
    private int temp;
    private boolean headache;
    private boolean mucus;
    private boolean cough;
    private boolean lymph;
    private boolean sore;
    private boolean nausea;
    private boolean cold;
    private boolean flu;
    private boolean bronchitis;

    public QuestionnaireHandler(int temp, boolean headache, boolean mucus, boolean cough, boolean lymph, boolean sore, boolean nausea){
        this.temp = temp;
        this.headache = headache;
        this.mucus = mucus;
        this.cough = cough;
        this.lymph = lymph;
        this.sore = sore;
        this.nausea = nausea;

        boolean[] diagnosis = diagnosis();
        this.cold = diagnosis[0];
        this.flu = diagnosis[1];
        this.bronchitis = diagnosis[2];

        PatientWrapper wrapper = (PatientWrapper) Main.getUserWrapper();
        this.patient_id = wrapper.getPatient_id();
        if(patient_id == null) {
            try {
                PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.GET_LAST_PATIENT_ID);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                this.patient_id = rs.getInt(1) + 1;
                wrapper.setPatient_id(this.patient_id);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public QuestionnaireHandler(Integer patient_id, int temp, boolean headache, boolean mucus, boolean cough, boolean lymph, boolean sore, boolean nausea, boolean cold, boolean flu, boolean bronchitis) {
        this.patient_id = patient_id;
        this.temp = temp;
        this.headache = headache;
        this.mucus = mucus;
        this.cough = cough;
        this.lymph = lymph;
        this.sore = sore;
        this.nausea = nausea;
        this.cold = cold;
        this.flu = flu;
        this.bronchitis = bronchitis;
    }

    public boolean[] diagnosis(){
        //implement diagnosis thingy
        return new boolean[]{false, false, false};
    }

    public void saveChanges() throws SQLException {
        PreparedStatement stmt = Main.getDatabaseManager().getConnection().prepareStatement(Queries.SAVE_QUESTIONNAIRE);

        stmt.setInt(1, patient_id);
        stmt.setInt(2, temp);
        stmt.setBoolean(3, headache);
        stmt.setBoolean(4, cough);
        stmt.setBoolean(5, mucus);
        stmt.setBoolean(6, lymph);
        stmt.setBoolean(7, sore);
        stmt.setBoolean(8, nausea);
        stmt.setBoolean(9, cold);
        stmt.setBoolean(10, flu);
        stmt.setBoolean(11, bronchitis);

        stmt.execute();

        stmt.close();
    }

    public int getTemp() {
        return temp;
    }

    public boolean getHeadahe() {
        return headache;
    }

    public boolean getCough() {
        return cough;
    }

    public boolean getMucus() {
        return mucus;
    }

    public boolean getLymph() {
        return lymph;
    }

    public boolean getSore() {
        return sore;
    }

    public boolean getNausea() {
        return nausea;
    }

    public boolean getCold() {
        return cold;
    }

    public boolean getFlu() {
        return flu;
    }

    public boolean getBronchitis() {
        return bronchitis;
    }

}
