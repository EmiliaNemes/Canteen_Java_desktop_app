package controller;

import dto.AdministratorDto;
import exception.CustomExceptionMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.AdministratorService;

import javax.persistence.NoResultException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {
    // !!! close button action

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    protected static AdministratorDto administrator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Assets/login.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Assets/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }


    public void loginButtonOnAction(ActionEvent event){
        AdministratorService administratorService = new AdministratorService();

        try {
            administrator = administratorService.getAdminByUsernameAndPassword(usernameTextField.getText(), passwordTextField.getText());
            System.out.println("Admin exists!");

            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminMenuScene= new Scene(root, 980,670);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminMenuScene);

        } catch(NoResultException e){
            loginMessageLabel.setText(CustomExceptionMessages.INVALID_USERNAME_PASSWORD_MESSAGE);
        } catch (Exception e){
    e.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/main_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminLoginScene=new Scene(root, 593, 403);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminLoginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AdministratorDto getAdministrator() {
        return administrator;
    }

}
