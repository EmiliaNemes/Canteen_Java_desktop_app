package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private ImageView customerView;

    @FXML
    private ImageView adminView;

    @FXML
    private ImageView logoView;

    @FXML
    private Button closeAppButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File customerFile = new File("Assets/user.png");
        Image customerImage = new Image(customerFile.toURI().toString());
        customerView.setImage(customerImage);

        File adminFile = new File("Assets/admin.png");
        Image adminImage = new Image(adminFile.toURI().toString());
        adminView.setImage(adminImage);

        File logoFile = new File("Assets/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoView.setImage(logoImage);
    }


    public void userLoginButtonOnAction(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/customer_login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminLoginScene= new Scene(root, 593,400);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminLoginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void userRegisterButtonOnAction(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/customer_register.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminRegisterScene= new Scene(root, 520,674);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setY(50);
            primaryStage.setScene(adminRegisterScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void adminLoginButtonOnAction(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/admin_login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminLoginScene= new Scene(root, 593,400);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminLoginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void adminRegisterButtonOnAction(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/admin_register.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminRegisterScene= new Scene(root, 520,674);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setY(50);
            primaryStage.setScene(adminRegisterScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeAppOnAction(Event event){
        Stage stage = (Stage) closeAppButton.getScene().getWindow();
        stage.close();
    }
}
