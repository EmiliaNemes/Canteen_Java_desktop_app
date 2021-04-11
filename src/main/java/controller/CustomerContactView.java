package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import utils.ApplicationUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerContactView implements Initializable {

    @FXML
    private Text phoneNumberText,emailText,addressText,notificationsText,mondayText,tuesdayText,wednesdayText,thursdayText,fridayText,saturdayText,sundayText;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneNumberText.setText(ApplicationUtils.phoneNumber);
        emailText.setText(ApplicationUtils.email);
        addressText.setText(ApplicationUtils.address);
        notificationsText.setText(ApplicationUtils.notifications);
        mondayText.setText(ApplicationUtils.mondaySchedule);
        tuesdayText.setText(ApplicationUtils.tuesdaySchedule);
        wednesdayText.setText(ApplicationUtils.wednesdaySchedule);
        thursdayText.setText(ApplicationUtils.thurdsaySchedule);
        fridayText.setText(ApplicationUtils.fridaySchedule);
        saturdayText.setText(ApplicationUtils.saturdaySchedule);
        sundayText.setText(ApplicationUtils.sundaySchedule);
    }
}
