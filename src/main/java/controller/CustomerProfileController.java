package controller;

import dto.CustomerDto;
import entity.Customer;
import exception.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mapper.CustomerMapper;
import service.CustomerService;
import utils.ApplicationUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CustomerProfileController implements Initializable {

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
    private Label customerNameLabel;

    private CustomerDto customer;

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

    private final static Logger log = Logger.getLogger(CustomerProfileController.class.getName());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer = CustomerLoginController.customer;
        customerNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());

        firstnameTextField.setText(customer.getFirstName());
        lastnameTextField.setText(customer.getLastName());
        emailTextField.setText(customer.getEmailAddress());
        phoneNumberTextField.setText(customer.getPhoneNumber());
        usernameTextField.setText(customer.getUsername());
        passwordTextField.setText(customer.getPassword());
        confirmPasswordTextField.setText(customer.getPassword());
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
        Customer updatedCustomer = new Customer();
        validPassword = ApplicationUtils.arePasswordsMatching(passwordTextField.getText(), confirmPasswordTextField.getText());

        if (validPassword){
            try {
                updatedCustomer.setId(customer.getId());
                updatedCustomer.setFirstName(firstnameTextField.getText());
                updatedCustomer.setLastName(lastnameTextField.getText());
                updatedCustomer.setUsername(customer.getUsername());
                updatedCustomer.setPassword(passwordTextField.getText());
                updatedCustomer.setEmailAddress(emailTextField.getText());
                updatedCustomer.setPhoneNumber(phoneNumberTextField.getText());

                CustomerService customerService = new CustomerService();
                CustomerLoginController.customer = CustomerMapper.entityToDto(updatedCustomer);
                customerService.updateCustomer(CustomerMapper.entityToDto(updatedCustomer));

                goToCustomerMenu(event);
            } catch (InvalidFirstNameException e) {
                firstNameMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidLastNameException e){
                lastNameMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidEmailException e){
                emailMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidPhoneNumberException e){
                phoneNumberMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidPasswordException e){
                passwordMessage.setText(e.getMessage());
                ok = false;
            }
        } else {
            passwordConfirmLabel.setText("Try again, passwords are not matching!");
            passwordTextField.setText("");
            confirmPasswordTextField.setText("");
            ok = false;
        }

        if(ok) {
            ApplicationUtils.displayOkMessage("The details of your profile has been modified!","Profile - info","Assets/ok.png");
            log.info("Profile edited successfully");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        goToCustomerMenu(event);
    }

    private void goToCustomerMenu(ActionEvent event){
        try{
            URL url = new File("src/main/java/gui/customer_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene customerLoginScene= new Scene(root, 947,725);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(customerLoginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
