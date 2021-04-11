package controller;

import dto.CustomerDto;
import dto.ProductDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.EmailService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CustomerOrderController implements Initializable {

    @FXML
    private ImageView cartImageView, deliveryImageView, takeInImageView;

    @FXML
    private Label totalLabel, orderTimeLabel;

    @FXML
    private TextField addressTextField;

    @FXML
    private RadioButton deliveryButton, takeInButton;

    @FXML
    private CustomerDto customer;

    private final static Logger log = Logger.getLogger(CustomerOrderController.class.getName());

    @FXML
    private ObservableList<ProductDto> order;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File deliveryFile = new File("Assets/delivery.png");
        Image deliveryImage = new Image(deliveryFile.toURI().toString());
        deliveryImageView.setImage(deliveryImage);

        File cartFile = new File("Assets/order.png");
        Image cartImage = new Image(cartFile.toURI().toString());
        cartImageView.setImage(cartImage);

        File takeInFile = new File("Assets/logo.png");
        Image takeInImage = new Image(takeInFile.toURI().toString());
        takeInImageView.setImage(takeInImage);

        totalLabel.setText(ApplicationUtils.totalPrice.getValue() + " ron");
        int quantity = 0;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/gui/customer_menu.fxml").toURI().toURL());
            fxmlLoader.load();

            CustomerMenuController customerMenuController = fxmlLoader.getController();
            for (ProductDto prod : customerMenuController.getOrderedProducts()) {
                quantity += prod.getQuantity();
            }
            customer= customerMenuController.getCustomer();
            order= customerMenuController.getOrderedProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderTimeLabel.setText(quantity * 5 + " min");
        takeInButton.setSelected(true);
    }


    public void confirmOrderOnAction(ActionEvent event) {
            if (takeInButton.isSelected()) {
                ApplicationUtils.displayOkMessage(" Your order was sent successfully!","Order - info","Assets/delivery.png");

                new EmailService(customer.getEmailAddress(),customer.getFirstName()+" "+customer.getLastName(),"Take-in",order,totalLabel.getText(),orderTimeLabel.getText());
                log.info("Email sent successfully");
            } else {
                if (addressTextField.getText().length() < 8) {
                    ApplicationUtils.displayWarningMessage("Please, add a valid address from Cluj-Napoca!", "Order- warning", "Assets/delivery.png");
                    log.warning("Invalid address");
                } else {
                    ApplicationUtils.displayOkMessage(" Your order was sent successfully!","Order - info","Assets/delivery.png");
                    new EmailService(customer.getEmailAddress(),customer.getFirstName()+" "+customer.getLastName(),addressTextField.getText(),order,totalLabel.getText(),orderTimeLabel.getText());
                    log.info("Email sent successfully");
                }
            }
    }

    public void cancelOrderOnAction(ActionEvent event) {
        try {
            URL url = new File("src/main/java/gui/customer_menu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            CustomerMenuController myCustomer = loader.getController();

            URL url_products = new File("src/main/java/gui/customer_food.fxml").toURI().toURL();
            Parent root_products = FXMLLoader.load(url_products);

            myCustomer.getCustomerMainPane().getChildren().setAll(root_products.getChildrenUnmodifiable());

            Scene customerMenuScene = new Scene(root, 947, 725);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(customerMenuScene);

            log.info("Order safely cancelled");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deliveryOnAction(ActionEvent event) {

        takeInButton.setSelected(false);
        deliveryButton.setSelected(true);
        totalLabel.setText((Double.parseDouble(ApplicationUtils.totalPrice.getValue()) + 5) + " ron");
    }

    public void takeInOnAction(ActionEvent event) {
            deliveryButton.setSelected(false);
            takeInButton.setSelected(true);
            totalLabel.setText(Double.valueOf(ApplicationUtils.totalPrice.getValue()) + " ron");
    }


}
