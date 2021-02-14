package main.java.Users;

public class Patient extends User {

    public Patient(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean createUser() {
        return false;
    }
}
