package project.applications.controllers.general;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.Main;
import project.Utils.objects.Wrappers.PatientWrapper;
import project.Utils.objects.Wrappers.UserWrapper;
import project.Utils.storage.Queries;
import project.Utils.objects.Wrappers.StaffWrapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    //Controller classes will connect to the .fxml file and provide methods for the events to implement


    //@FXML allows you to register fields directly from .fxml file
    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label usernameLabel;

    public void staffLoginAction(MouseEvent event) throws IOException {

        //Set username to the username input
        String username = this.userTxt.getText();

        //Case: Username Does Not Exist
        if(!Main.getUsernameHandler().containsUser(username, true)){
            resultLabel.setText("Login Failed!");
            usernameLabel.setText("Incorrect Username");
            return;
        }

        UserWrapper wrapper = null;
        try{
            //Connect to database
            Connection con = Main.getDatabaseManager().getConnection();

            //Prepare Query and define ResultSet
            PreparedStatement stmt = con.prepareStatement(Queries.GET_STAFF);
            stmt.setString(1,username);
            ResultSet rS = stmt.executeQuery();

            //Get necessary information from ResultSet
            rS.next();
            String password = rS.getString(2);
            String last_name = rS.getString(3);
            String first_name = rS.getString(4);

            //Create StaffWrapper from information given to avoid continually calling on the database
            wrapper = new StaffWrapper(username, password, last_name, first_name);

            //Always close your statements
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Case: Password Does Not Match
        if(!wrapper.matchPass(passTxt.getText())){
            resultLabel.setText("Login Failed!");
            usernameLabel.setText("Incorrect Password");
            return;
        }

        //Set UserWrapper from Main class
        Main.setUserWrapper(wrapper);
        //Set color to lime because success! Default color red
        resultLabel.setStyle("-fx-text-fill: LIME");
        usernameLabel.setStyle("-fx-text-fill: LIME");
        resultLabel.setText("Login Succeeded to: ");
        usernameLabel.setText(username);


        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/staff/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Home Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }





    public void loginAction(MouseEvent event) throws IOException{
        //Set username to the username input
        String username = this.userTxt.getText();

        //Case: Username Does Not Exist
        if(!Main.getUsernameHandler().containsUser(username, false)){
            resultLabel.setText("Login Failed!");
            usernameLabel.setText("Incorrect Username");
            return;
        }

        UserWrapper wrapper = null;
        try{
            //Connect to database
            Connection con = Main.getDatabaseManager().getConnection();

            //Prepare Query and define ResultSet
            PreparedStatement stmt = con.prepareStatement(Queries.GET_PATIENT);
            stmt.setString(1,username);
            ResultSet rS = stmt.executeQuery();

            //Get necessary information from ResultSet
            rS.next();
            String password = rS.getString(2);
            String last_name = rS.getString(3);
            String first_name = rS.getString(4);

            //Create StaffWrapper from information given to avoid continually calling on the database
            wrapper = new PatientWrapper(username, password, last_name, first_name);

            //Always close your statements
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Case: Password Does Not Match
        if(!wrapper.matchPass(passTxt.getText())){
            resultLabel.setText("Login Failed!");
            usernameLabel.setText("Incorrect Password");
            return;
        }

        //Set UserWrapper from Main class
        Main.setUserWrapper(wrapper);
        //Set color to lime because success! Default color red
        resultLabel.setStyle("-fx-text-fill: LIME");
        usernameLabel.setStyle("-fx-text-fill: LIME");
        resultLabel.setText("Login Succeeded to: ");
        usernameLabel.setText(username);


        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/homescreen.fxml"));
        Main.getPrimaryStage().setTitle("Home Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }
}
