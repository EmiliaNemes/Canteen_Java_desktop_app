package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.EmailService;
import utils.ApplicationUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminContactController implements Initializable {

    @FXML
    private TextField phoneNumberTextField,emailTextField,notificationsTextField,mondayTextField, tuesdayTextField, wednesdayTextField,thursdayTextField,fridayTextField,saturdayTextField,sundayTextField;

    private final static Logger log = Logger.getLogger(AdminContactController.class.getName());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneNumberTextField.setText(ApplicationUtils.phoneNumber);
    emailTextField.setText(ApplicationUtils.email);
    notificationsTextField.setText(ApplicationUtils.notifications);
    mondayTextField.setText(ApplicationUtils.mondaySchedule);
    tuesdayTextField.setText(ApplicationUtils.tuesdaySchedule);
    wednesdayTextField.setText(ApplicationUtils.wednesdaySchedule);
    thursdayTextField.setText(ApplicationUtils.thurdsaySchedule);
    fridayTextField.setText(ApplicationUtils.fridaySchedule);
    saturdayTextField.setText(ApplicationUtils.saturdaySchedule);
    sundayTextField.setText(ApplicationUtils.sundaySchedule);
    }

    public void saveButtonOnAction(ActionEvent event){
        ApplicationUtils.phoneNumber = phoneNumberTextField.getText();
        ApplicationUtils.email = emailTextField.getText();
        ApplicationUtils.notifications= notificationsTextField.getText();
        ApplicationUtils.mondaySchedule= mondayTextField.getText();
        ApplicationUtils.tuesdaySchedule = tuesdayTextField.getText();
        ApplicationUtils.wednesdaySchedule= wednesdayTextField.getText();
        ApplicationUtils.thurdsaySchedule = thursdayTextField.getText();
        ApplicationUtils.fridaySchedule= fridayTextField.getText();
        ApplicationUtils.saturdaySchedule = saturdayTextField.getText();
        ApplicationUtils.sundaySchedule = sundayTextField.getText();

        goToAdminMenu(event);
        // E
        ApplicationUtils.displayOkMessage("The modifications has been saved!","Contact - cantina", "Assets/ok.png");
        log.info("Contacts edited successfully");
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
