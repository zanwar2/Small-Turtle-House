package main.java.Users;

public abstract class User {

    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;

    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVariables(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    public boolean login(){
        //compare username to usernames in database
        //if username doesn't exist, return false
        //compare password to password associated with this username
        //if password is wrong, return false
        return true;
    }

    public void failLogin() {
        String newUsername = "";
        String newPassword = "";
        //prompt user to re enter username and password
        this.setVariables(newUsername, newPassword);
    }

    public abstract boolean createUser();
}
