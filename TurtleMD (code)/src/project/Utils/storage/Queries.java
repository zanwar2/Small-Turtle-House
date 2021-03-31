package project.Utils.storage;

public class Queries {

    /** Creating a table to store data for the patients questionnaire. This specific table contains all the users
     * symptoms they will be submitting to the database.**/
    public static String CREATE_QUESTIONNAIRE_TABLE = "CREATE TABLE IF NOT EXISTS pre_app_quest (" +
            "patient_id INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "rounded_temp INTEGER," +
            "headaches BOOLEAN," +
            "coughing BOOLEAN," +
            "mucus BOOLEAN," +
            "swollen_lymphnodes BOOLEAN," +
            "soreness BOOLEAN," +
            "nausea BOOLEAN," +
            "common_cold BOOLEAN," +
            "flu BOOLEAN," +
            "bronchitis BOOLEAN );";

    /** Creating a table for the patients personal and account information to be stored. This specific table will store
     * the users account username, password, last name, first name, their next appointment time, and their patient id. **/
    public static String CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS user_data (" +
            "username VARCHAR(64) PRIMARY KEY," +
            "password VARCHAR(16)," +
            "last_name VARCHAR(32)," +
            "first_name VARCHAR(32)," +
            "next_appointment TIMESTAMP," +
            "patient_id INTEGER );";

    /** Creating a table for the medical staffs personal and account information to be stored. This table will store
     * the staffs account username, password, last name, and first name. **/
    public static String CREATE_STAFF_TABLE = "CREATE TABLE IF NOT EXISTS staff_data (" +
            "username VARCHAR(64) PRIMARY KEY," +
            "password VARCHAR(16)," +
            "last_name VARCHAR(32)," +
            "first_name VARCHAR(32) );";

    /** Creating a table to maintain the scheduling times. This contains a specific day and various times from 9AM
     * through 5PM. **/
    public static String CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS master_schedule (" +
            "day DATE PRIMARY KEY," +
            "9AM_available BOOLEAN DEFAULT TRUE," +
            "10AM_available BOOLEAN DEFAULT TRUE," +
            "11AM_available BOOLEAN DEFAULT TRUE," +
            "12PM_available BOOLEAN DEFAULT TRUE," +
            "1PM_available BOOLEAN DEFAULT TRUE," +
            "2PM_available BOOLEAN DEFAULT TRUE," +
            "3PM_available BOOLEAN DEFAULT TRUE," +
            "4PM_available BOOLEAN DEFAULT TRUE," +
            "5PM_available BOOLEAN DEFAULT TRUE );";


    /** Various queries created to update information as it is submitted by the user. These are used by various methods
     * throughout the code in order to properly update specific fields. **/

    //Used in the StaffWrapper class to save the staffs information into the staff table to the database.
    public static String SAVE_STAFF = "REPLACE INTO staff_data (username, password, last_name, first_name) VALUES(?,?,?,?);";

    //Used in the UsernameHandler class to look for the username of the staff member in the database.
    public static String GET_STAFF_USERNAMES = "SELECT username FROM staff_data;";

    //Used in the LoginController class to validate whether the username does or does not exist while the staff attempts to log in.
    public static String GET_STAFF = "SELECT * FROM staff_data WHERE username = ?;";

    //Used in the PatientWrapper class to add a patients id if their patient id is null.
    public static String ADD_PATIENT_ID = "UPDATE user_data SET patient_id = ? WHERE username = ?;";

    //Used in the PatientWrapper class to save the patients information to the patient table in the database.
    public static String SAVE_PATIENTS = "REPLACE INTO user_data (username, password, last_name, first_name, next_appointment) VALUES(?,?,?,?,?);";

    //Used in the UsernameHandler class to look for the username of the patient in the database.
    public static String GET_PATIENT_USERNAMES = "SELECT username FROM user_data;";

    //Used in the LoginController class to validate whether the username does or does not exist while the patient attempts to log in.
    public static String GET_PATIENT = "SELECT * FROM user_data WHERE username = ?;";

    //Used in the QuestionnaireHandler class to specify a users questionnaire results.
    public static String GET_LAST_PATIENT_ID = "SELECT * FROM pre_app_quest ORDER BY patient_id DESC LIMIT 1;";

    //Used in the PatientWrapper class grab a specifc patients questionnaire
    public static String GET_QUESTIONNAIRE = "SELECT * FROM pre_app_quest WHERE patient_id = ?;";

    //Used in the QuestionnaireHandler class to update the patients results to their information in the database.
    public static String SAVE_QUESTIONNAIRE = "REPLACE INTO pre_app_quest (patient_id, rounded_temp, headaches, coughing, mucus, swollen_lymphnodes, soreness, nausea, common_cold, flu, bronchitis) VALUES(?,?,?,?,?,?,?,?,?,?,?);";

    //Used in the ScheduleHandler class to select the day of the patients appointment.
    public static String GET_DAY = "SELECT * FROM master_schedule WHERE day = ?;";

    //Used in the ScheduleHandler class to save and update the day the of the patients appointment.
    public static String SAVE_DAY = "REPLACE INTO master_schedule (day, 9AM_available, 10AM_available, 11AM_available, 12PM_available, 1PM_available, 2PM_available, 3PM_available, 4PM_available, 5PM_available) VALUES(?,?,?,?,?,?,?,?,?,?);";

    //Used in the StaffWrapper class to display the next scheduled appointment for a staff memeber.
    public static String GET_NEXT_APPOINTMENT = "SELECT * FROM user_data WHERE next_appointment IS NOT NULL ORDER BY next_appointment LIMIT 1;";

    //Used in the viewTimeController class to view the time the patient has scheduled
    public static String GET_PATIENT_BY_TIME = "SELECT * FROM user_data WHERE next_appointment = ?;";

    //Used in the NextAppointmentController class to remove a patients
    public static String REMOVE_FROM_MASTER_SCHEDULE = "DELETE FROM master_schedule WHERE day = ?;";

}

