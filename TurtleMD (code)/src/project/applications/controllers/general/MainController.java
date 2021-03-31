package project.applications.controllers.general;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import project.Main;

import java.io.IOException;

/* MainController interacts with elements from the main.fxml file */
public class MainController {

    /* @FXML allows you to register fields directly from .fxml file */
    @FXML
    ImageView image;


    /* signupBtnAction() listens for signupBtn to be clicked. Proceeds to signup screen. */
    public void signupBtnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/patient/signupscreen.fxml"));
        Main.getPrimaryStage().setTitle("Signup Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    /* loginBtnAction() listens for loginBtnBtn to be clicked. Proceeds to login screen. */
    public void loginBtnAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("applications/resources/fxml/general/loginscreen.fxml"));
        Main.getPrimaryStage().setTitle("Login Screen");
        Main.getPrimaryStage().setScene(new Scene(root, root.prefWidth(500), root.prefHeight(500)));
    }

    /* Sets the image on the page */
    public void setImage(Image image) {
        this.image.setImage(image);
    }
}
