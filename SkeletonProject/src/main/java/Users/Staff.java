package main.java.Users;

public class Staff extends User{

    public Staff(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean createUser() {
        return false;
    }
}
