package controller;

import dto.CustomerDto;
import entity.Customer;
import exception.*;
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

import javafx.event.ActionEvent;
import mapper.CustomerMapper;
import service.CustomerService;
import utils.ApplicationUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRegisterController implements Initializable {
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

        CustomerService customerService = new CustomerService();
        boolean validPassword = ApplicationUtils.arePasswordsMatching(passwordTextField.getText(), confirmPasswordTextField.getText());

        boolean ok=true;
        if (validPassword){
            try {
                Customer customer = new Customer(firstnameTextField.getText(), lastnameTextField.getText(), emailTextField.getText(), phoneTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
                CustomerDto customerDto = CustomerMapper.entityToDto(customer);
                customerService.addCustomer(customerDto);

            } catch (InvalidFirstNameException e) {
                firstNameMessage.setText(e.getMessage());
                ok=false;
            } catch (InvalidLastNameException e){
                lastNameMessage.setText(e.getMessage());
                ok=false;

            } catch (InvalidEmailException e){
                emailMessage.setText(e.getMessage());
                ok=false;

            } catch (InvalidPhoneNumberException e){
                phoneNumberMessage.setText(e.getMessage());
                ok=false;

            } catch (InvalidUsernameException e){
                usernameMessage.setText(e.getMessage());
                ok=false;

            } catch (InvalidPasswordException e){
                passwordMessage.setText(e.getMessage());
                ok=false;

            }
        } else {
            passwordConfirmLabel.setText("Try again, passwords are not matching!");
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
            ok=false;
        }

        if(ok) {
            ApplicationUtils.displayOkMessage("You registered successfully","Profile- info","Assets/ok.png");
            goToMain(event);
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        goToMain(event);
    }

    public void goToMain(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/main_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene customerScene= new Scene(root, 593,400);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(customerScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
