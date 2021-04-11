package controller;

import dto.AdministratorDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenu implements Initializable {
    @FXML
    private ImageView foodLogo,editProfileLogo,logoutLogo,drinkLogo,contactLogo,foodBackground,adminLogo;

    @FXML
    private Label adminNameLabel;

    @FXML
    private AnchorPane adminMainPane;

    private AdministratorDto administrator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File foodFile = new File("Assets/food.png");
        Image foodImage = new Image(foodFile.toURI().toString());
        foodLogo.setImage(foodImage);

        File editProfileFile = new File("Assets/edit.png");
        Image editProfileImage = new Image(editProfileFile.toURI().toString());
        editProfileLogo.setImage(editProfileImage);

        File logoutFile = new File("Assets/logout.png");
        Image logoutImage = new Image(logoutFile.toURI().toString());
        logoutLogo.setImage(logoutImage);


        File drinkFile = new File("Assets/drink.png");
        Image drinkImage = new Image(drinkFile.toURI().toString());
        drinkLogo.setImage(drinkImage);


        File contactFile = new File("Assets/contact.png");
        Image contactImage = new Image(contactFile.toURI().toString());
        contactLogo.setImage(contactImage);

        File foodBackgroundFile = new File("Assets/food_background.jpg");
        Image foodBackgroundImage = new Image(foodBackgroundFile.toURI().toString());
        foodBackground.setImage(foodBackgroundImage);

        File adminFile = new File("Assets/admin.png");
        Image adminImage = new Image(adminFile.toURI().toString());
        adminLogo.setImage(adminImage);

        administrator = AdminLoginController.administrator;
        adminNameLabel.setText(administrator.getFirstName() + " " + administrator.getLastName());
    }

    @FXML
    public void logoutLabelOnAction(Event event){
        try{
            URL url = new File("src/main/java/gui/main_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene mainMenuScene= new Scene(root, 593,400);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void editProfileLabelOnAction(Event event){
        try {
            URL url = new File("src/main/java/gui/admin_profile_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

           adminMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //action event nu merge decat pe butoane- la label links- face Event din javafx.event.*;
    public void foodMenuOnAction(Event event){
        try {
            URL url = new File("src/main/java/gui/admin_food_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            adminMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drinkMenuOnAction(Event event){
        try {
            URL url = new File("src/main/java/gui/admin_drink_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            adminMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contactMenuOnAction(Event event){
        try {
            URL url = new File("src/main/java/gui/admin_contact_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            adminMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AdministratorDto getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDto administrator) {
        this.administrator = administrator;
    }


    public AnchorPane getAdminMainPane() {
        return adminMainPane;
    }

    public void setAdminMainPane(AnchorPane adminMainPane) {
        this.adminMainPane = adminMainPane;
    }
}
