package controller;

import dto.CustomerDto;
import dto.ProductDto;
import exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CustomerMenuController implements Initializable {

    @FXML
    private ImageView foodLogo, editProfileLogo, logoutLogo, drinkLogo, contactLogo, foodBackground, customerLogo;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label totalOrderLabel;

    @FXML
    private AnchorPane customerMainPane;
    @FXML
    public TableColumn<ProductDto, String> nameColumn;
    @FXML
    public TableColumn<ProductDto, String> quantityColumn;
    @FXML
    public TableColumn<ProductDto, String> priceColumn;
    @FXML
    public TableView<ProductDto> orderTable;

    private final static Logger log = Logger.getLogger(CustomerMenuController.class.getName());

    private CustomerDto customer;
    private static final ObservableList<ProductDto> orderedProducts = FXCollections.observableArrayList();

    public ObservableList<ProductDto> getOrderedProducts() {
        return orderedProducts;
    }

    public void updateTable() {
        orderTable.setItems(orderedProducts);
    }

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

        File foodBackgroundFile = new File("Assets/pizza.jpg");
        Image foodBackgroundImage = new Image(foodBackgroundFile.toURI().toString());
        foodBackground.setImage(foodBackgroundImage);

        File adminFile = new File("Assets/admin.png");
        Image adminImage = new Image(adminFile.toURI().toString());
        customerLogo.setImage(adminImage);

        customer = CustomerLoginController.customer;
        customerNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());

        totalOrderLabel.textProperty().bind(ApplicationUtils.totalPrice);

        updateTable();
    }

    @FXML
    public void logoutLabelOnAction(Event event) {
        try {
            cancel();
            URL url = new File("src/main/java/gui/main_menu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            Scene mainMenuScene = new Scene(root, 593, 400);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editProfileLabelOnAction(Event event) {
        try {
            URL url = new File("src/main/java/gui/customer_profile_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            customerMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void foodMenuOnAction(Event event) {
        try {
            URL url = new File("src/main/java/gui/customer_food.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            customerMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drinkMenuOnAction(Event event) {
        try {
            URL url = new File("src/main/java/gui/customer_drink_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);

            customerMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contactMenuOnAction(Event event) {
        try {
            URL url = new File("src/main/java/gui/customer_contact_view.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            customerMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addQuantityOnAction(ActionEvent event) throws InvalidFastingItemException, InvalidPriceException, InvalidCategoryException, InvalidWeightException, InvalidQuantityException, InvalidProductNameException, InvalidUrlException {
        ProductDto product = orderTable.getSelectionModel().getSelectedItem();
        if (product == null) {
            ApplicationUtils.displayWarningMessage("No product was selected in order to update the quantity", "Order - warning", "Assets/warning.png");
            log.warning("No selected product");
        } else {
            ProductService foodService = new ProductService();
            ProductDto toUpdate = foodService.getProdByName(product.getName());

            if (toUpdate.getQuantity() != 0) {

                product.setQuantity(product.getQuantity() + 1);
                ObservableList<ProductDto> copy = FXCollections.observableArrayList(orderedProducts);
                orderedProducts.clear();
                orderedProducts.addAll(copy);

                toUpdate.setQuantity(toUpdate.getQuantity() - 1);
                foodService.updateProduct(toUpdate);
                ApplicationUtils.totalPrice.setValue(getTotalPrice());
            } else {
                ApplicationUtils.displayWarningMessage("Not enough products in stock", "Order - warning", "Assets/warning.png");
                log.warning("Not enough products in stock");
            }
        }
    }

    public void decreaseQuantityOnAction(ActionEvent event) throws InvalidFastingItemException, InvalidPriceException, InvalidCategoryException, InvalidWeightException, InvalidQuantityException, InvalidProductNameException, InvalidUrlException {
        ProductDto product = orderTable.getSelectionModel().getSelectedItem();
        if (product == null) {
            ApplicationUtils.displayWarningMessage("No product was selected in order to update the quantity", "Order - warning", "Assets/warning.png");
            log.warning("No selected product");
        } else {
            ProductService foodService = new ProductService();
            ProductDto toUpdate = foodService.getProdByName(product.getName());

            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
            } else if (product.getQuantity() == 1) {
                orderedProducts.remove(product);
            }

            ObservableList<ProductDto> copy = FXCollections.observableArrayList(orderedProducts);
            orderedProducts.clear();
            orderedProducts.addAll(copy);

            toUpdate.setQuantity(toUpdate.getQuantity() + 1);
            foodService.updateProduct(toUpdate);
            ApplicationUtils.totalPrice.setValue(getTotalPrice());
        }
    }

    public void proceedOnAction(ActionEvent event) {
        if (orderedProducts.size() != 0) {
            try {
                URL url = new File("src/main/java/gui/customer_order.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);

                customerMainPane.getChildren().setAll(root.getChildrenUnmodifiable());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ApplicationUtils.displayWarningMessage("No product was selected in order to update the quantity", "Order - warning", "Assets/warning.png");
            log.warning("No products added to order");
        }
    }

    public void cancelOnAction(ActionEvent event) throws InvalidFastingItemException, InvalidQuantityException, InvalidUrlException, InvalidPriceException, InvalidWeightException, InvalidCategoryException, InvalidProductNameException {
        if (orderedProducts.size() == 0) {
            ApplicationUtils.displayWarningMessage("Your order is empty, you don't have what to cancel!", "Order - warning", "Assets/warning.png");
        } else {
            cancel();
        }
    }

    public void cancel() throws InvalidFastingItemException, InvalidPriceException, InvalidCategoryException, InvalidWeightException, InvalidQuantityException, InvalidProductNameException, InvalidUrlException {
        ProductService foodService = new ProductService();

        for (ProductDto product : orderedProducts) {
            ProductDto update = foodService.getProdByName(product.getName());
            update.setQuantity(update.getQuantity() + product.getQuantity());
            foodService.updateProduct(update);

        }
        ApplicationUtils.totalPrice.setValue("0");
        orderedProducts.clear();
        log.info("Order safely cancelled");
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getTotalPrice() {
        float total = 0;
        for (ProductDto product : orderedProducts) {
            total += product.getQuantity() * product.getPrice();
        }
        return String.valueOf(total);
    }

    public AnchorPane getCustomerMainPane() {
        return customerMainPane;
    }
}
