package utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.AdministratorService;
import service.CustomerService;

import javax.persistence.NoResultException;
import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String mondaySchedule="12:00 - 18:00",tuesdaySchedule="12:00 - 18:00", wednesdaySchedule="12:00 - 18:00", thurdsaySchedule="12:00 - 18:00",fridaySchedule="12:00 - 16:00",saturdaySchedule="closed",sundaySchedule="closed";
    public static String address = "Complex Studențesc Observator, Strada Observatorului 34, Cluj-Napoca 400066";
    public static String email = "cantina@staff.utcluj.com";
    public static String phoneNumber = "0264 594 930";
    public static String notifications = " Vineri nu se poate realiza plata cu cardul";
    public static StringProperty totalPrice= new SimpleStringProperty("0");

    public static boolean isFirstNameValid(String firstName){
        return firstName != null && !firstName.equals("") && firstName.matches("[A-Z][a-z]*");
    }

    public static boolean isLastNameValid(String lastName){
        return lastName != null && !lastName.equals("") && lastName.matches("[A-Z][a-z]*");
    }

    public static boolean isEmailValid(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber){
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static boolean isAdminUsernameValid(String username){
        if(username == null || username.equals("")){
            return false;
        } else {
            AdministratorService administratorService = new AdministratorService();

            try{
                administratorService.getAdminByUsername(username);
                return false;
            } catch(NoResultException e){
                return true;
            }
        }
    }

    public static boolean isPasswordValid(String password){
        if(password.length()<6) {
            return false;
        }
        else {
            boolean ok1=false;
            boolean ok2=false;
            boolean ok3=false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (Character.isUpperCase(c)) ok1 = true;
                if (Character.isLowerCase(c)) ok2 = true;
                if (Character.isDigit(c)) ok3 = true;
            }
            return ok1 & ok2 & ok3;
        }
    }

    public static boolean arePasswordsMatching(String password1, String password2){
        return password1.equals(password2);
    }

    public static boolean isCategoryValid(String category){
        return category != null && !category.equals("");
    }

    public static boolean isProductNameValid(String name){
        return name != null && !name.equals("");
    }

    public static boolean isPriceValid(Double price){
        if (price == null)
            return false;
        return price > 0;
    }

    public static boolean isQuantityValid(Integer quantity){
        if (quantity == null)
            return false;
        return quantity >= 0;
    }

    public static boolean isWeightValid(Integer weight){
        if (weight == null)
            return false;
        return weight > 0;
    }

    public static boolean isUrlValid(String url, String category){
        if (category.equals("Fel principal"))
            category = "FEL-PRINCIPAL";
        if (url == null || url.length() < 13 + category.length())
            return false;
        return url.startsWith("/AssetsBis/" + category.toUpperCase() + "/");
    }

    public static void displayOkMessage(String message,String title, String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle(title);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.setGraphic(imageView);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    public static void displayWarningMessage(String message,String title, String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle(title);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.setGraphic(imageView);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
