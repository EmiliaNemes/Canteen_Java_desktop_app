package controller;

import dto.CustomerDto;
import exception.CustomExceptionMessages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView ;
import javafx.scene.image.Image;
import service.CustomerService;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CustomerLoginController implements Initializable {

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

    private final static Logger log = Logger.getLogger(CustomerLoginController.class.getName());

    protected static CustomerDto customer;

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
        CustomerService customerService = new CustomerService();
        try {
            customer= customerService.getCustomerByUsernameAndPassword(usernameTextField.getText(), passwordTextField.getText());
            URL url = new File("src/main/java/gui/customer_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminMenuScene= new Scene(root, 947,725);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminMenuScene);
            log.info("Customer found");

        } catch(NoResultException | MalformedURLException e){
            log.warning("Invalid username or password");
            loginMessageLabel.setText(CustomExceptionMessages.INVALID_USERNAME_PASSWORD_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/main_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminLoginScene= new Scene(root, 593,400);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminLoginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
