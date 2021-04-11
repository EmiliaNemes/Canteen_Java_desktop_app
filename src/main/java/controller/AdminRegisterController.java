package controller;

import dto.AdministratorDto;
import entity.Administrator;
import exception.*;
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
import mapper.AdministratorMapper;
import service.AdministratorService;
import utils.ApplicationUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminRegisterController implements Initializable {
    @FXML
    private ImageView registerImageView;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Label passwordConfirmLabel;

    @FXML
    private Label registrationMessageLabel;

    private boolean validPassword;

    @FXML
    private Label firstNameMessage;

    @FXML
    private Label lastNameMessage;

    @FXML
    private Label emailMessage;

    @FXML
    private Label phoneNumberMessage;

    @FXML
    private Label usernameMessage;

    @FXML
    private Label passwordMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File registerFile = new File("Assets/register.png");
        Image registerImage = new Image(registerFile.toURI().toString());
        registerImageView.setImage(registerImage);
    }

    public void registerButtonOnAction(ActionEvent event){
        firstNameMessage.setText("");
        lastNameMessage.setText("");
        phoneNumberMessage.setText("");
        emailMessage.setText("");
        usernameMessage.setText("");
        passwordMessage.setText("");

        AdministratorService administratorService = new AdministratorService();
        validPassword = ApplicationUtils.arePasswordsMatching(passwordTextField.getText(), confirmPasswordTextField.getText());

        if (validPassword){
           Administrator administrator = new Administrator(firstnameTextField.getText(), lastnameTextField.getText(), emailTextField.getText(), phoneTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
           AdministratorDto administratorDto = AdministratorMapper.entityToDto(administrator);
           try{
               administratorService.addAdmin(administratorDto);
               goToMain(event);
               ApplicationUtils.displayOkMessage("You have registered successfully!","Profile - Info","Assets/ok.png");
           } catch (InvalidFirstNameException e) {
               firstNameMessage.setText(e.getMessage());
           } catch (InvalidLastNameException e){
               lastNameMessage.setText(e.getMessage());
           } catch (InvalidEmailException e){
               emailMessage.setText(e.getMessage());
           } catch (InvalidPhoneNumberException e){
               phoneNumberMessage.setText(e.getMessage());
           } catch (InvalidUsernameException e){
               usernameMessage.setText(e.getMessage());
           } catch (InvalidPasswordException e){
               passwordMessage.setText(e.getMessage());
           }
        } else {
            passwordConfirmLabel.setText("Try again, passwords are not matching!");
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
            }

         }

    public void cancelButtonOnAction(ActionEvent event){
        goToMain(event);
    }
    public void goToMain(ActionEvent event){
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

    public String getFirstnameTextField() {
        return firstnameTextField.getText();
    }

    public void setFirstnameTextField(TextField firstnameTextField) {
        this.firstnameTextField = firstnameTextField;
    }

    public String getLastnameTextField() {
        return lastnameTextField.getText();
    }

    public void setLastnameTextField(TextField lastnameTextField) {
        this.lastnameTextField = lastnameTextField;
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public void setEmailTextField(TextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public String getPhoneTextField() {
        return phoneTextField.getText();
    }

    public void setPhoneTextField(TextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    public String getUsernameTextField() {
        return usernameTextField.getText();
    }

    public void setUsernameTextField(TextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public String getPasswordTextField() {
        return passwordTextField.getText();
    }

    public void setPasswordTextField(PasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

}
