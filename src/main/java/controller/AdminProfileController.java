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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mapper.AdministratorMapper;
import service.AdministratorService;
import utils.ApplicationUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminProfileController implements Initializable {

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Label passwordConfirmLabel;

    @FXML
    private Label adminNameLabel;

    private AdministratorDto administrator;

    @FXML
    private Label passwordMessage;

    @FXML
    private Label firstNameMessage;

    @FXML
    private Label lastNameMessage;

    @FXML
    private Label emailMessage;

    @FXML
    private Label phoneNumberMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        administrator = AdminLoginController.administrator;
        adminNameLabel.setText(administrator.getFirstName() + " " + administrator.getLastName());

        firstnameTextField.setText(administrator.getFirstName());
        lastnameTextField.setText(administrator.getLastName());
        emailTextField.setText(administrator.getEmailAddress());
        phoneNumberTextField.setText(administrator.getPhoneNumber());
        usernameTextField.setText(administrator.getUsername());
        passwordTextField.setText(administrator.getPassword());
        confirmPasswordTextField.setText(administrator.getPassword());
    }

    public void saveButtonOnAction(ActionEvent event){

        firstNameMessage.setText("");
        lastNameMessage.setText("");
        phoneNumberMessage.setText("");
        emailMessage.setText("");
        passwordMessage.setText("");
        passwordConfirmLabel.setText("");

        boolean validPassword;
        boolean ok = true;
        Administrator updatedAdministrator = new Administrator();
        validPassword = ApplicationUtils.arePasswordsMatching(passwordTextField.getText(), confirmPasswordTextField.getText());

        if (validPassword) {
            try {
                updatedAdministrator.setId(administrator.getId());
                updatedAdministrator.setFirstName(firstnameTextField.getText());
                updatedAdministrator.setLastName(lastnameTextField.getText());
                updatedAdministrator.setUsername(administrator.getUsername());
                updatedAdministrator.setPassword(passwordTextField.getText());
                updatedAdministrator.setEmailAddress(emailTextField.getText());
                updatedAdministrator.setPhoneNumber(phoneNumberTextField.getText());

                AdministratorService administratorService = new AdministratorService();
                AdminLoginController.administrator = AdministratorMapper.entityToDto(updatedAdministrator);
                administratorService.updateAdministrator(AdministratorMapper.entityToDto(updatedAdministrator));

                goToAdminMenu(event);

            } catch (InvalidFirstNameException e) {
                firstNameMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidLastNameException e) {
                lastNameMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidEmailException e) {
                emailMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidPhoneNumberException e) {
                phoneNumberMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidPasswordException e) {
                passwordMessage.setText(e.getMessage());
                ok = false;
            }
        }
        else {
            passwordConfirmLabel.setText("Try again, passwords are not matching!");
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
            ok = false;
        }

        // E
        if(ok) {
            ApplicationUtils.displayOkMessage("The details of your profile has been modified!","Profile- info","Assets/ok.png");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        goToAdminMenu(event);
    }

    private void goToAdminMenu(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene adminLoginScene= new Scene(root, 980,670);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(adminLoginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
