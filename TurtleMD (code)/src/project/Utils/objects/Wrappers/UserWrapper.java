package project.Utils.objects.Wrappers;

import project.Main;
import project.Utils.storage.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserWrapper {


    private String username;
    private String password;
    private String last_name;
    private String first_name;

    public UserWrapper(String username, String password, String last_name, String first_name) {
        this.username = username;
        this.password = password;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public boolean matchPass(String password){
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

}
