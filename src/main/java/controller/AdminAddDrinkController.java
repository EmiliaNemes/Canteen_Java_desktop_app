package controller;

import dto.ProductDto;
import exception.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class AdminAddDrinkController {


    public TextField price;
    public TextField quantity;
    public TextField weight;
    public TextField image;
    public TextField prodName;

    @FXML
    private Label drinkNameMessage;

    @FXML
    private Label priceMessage;

    @FXML
    private Label quantityMessage;

    @FXML
    private Label weightMessage;

    @FXML
    private Label imageURLMessage;

    public AdminAddDrinkController() {
    }

    public void cancelButtonOnAction(ActionEvent event){
        goToAdminDrinkMenu(event);
    }

    private void goToAdminDrinkMenu(ActionEvent event){
        try {
            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            AdminMenu myAdmin = loader.getController();

            URL url_drink = new File("src/main/java/gui/admin_drink_view.fxml").toURI().toURL();
            Parent root_drink = FXMLLoader.load(url_drink);

            myAdmin.getAdminMainPane().getChildren().setAll(root_drink.getChildrenUnmodifiable());

            Scene mainMenuScene= new Scene(root, 980,670);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) throws InvalidFastingItemException, InvalidPriceException, InvalidCategoryException, InvalidWeightException, InvalidQuantityException, InvalidProductNameException, InvalidUrlException {
        ProductDto newProduct = new ProductDto();
        boolean ok = true;

        drinkNameMessage.setText("");
        priceMessage.setText("");
        quantityMessage.setText("");
        weightMessage.setText("");
        imageURLMessage.setText("");

        try {
            newProduct.setName(prodName.getText());
            newProduct.setCategory("Drink");
            newProduct.setDescription("");
            newProduct.setFastingItem(true);
            newProduct.setPrice(Double.parseDouble(price.getText()));
            newProduct.setQuantity(Integer.parseInt(quantity.getText()));
            newProduct.setWeight(Integer.parseInt(weight.getText()));
            newProduct.setImageURL(image.getText());

            ProductService productService = new ProductService();
            productService.addProduct(newProduct);

        } catch (InvalidProductNameException e) {
            drinkNameMessage.setText(e.getMessage());
            ok = false;
        } catch (InvalidPriceException e) {
            priceMessage.setText(e.getMessage());
            ok = false;
        } catch (InvalidQuantityException e) {
            quantityMessage.setText(e.getMessage());
            ok = false;
        } catch (InvalidWeightException e) {
            weightMessage.setText(e.getMessage());
            ok = false;
        } catch (InvalidUrlException e) {
            imageURLMessage.setText(e.getMessage());
            ok = false;
        }

        if(ok) {
            goToAdminDrinkMenu(actionEvent);
            // E
            ApplicationUtils.displayOkMessage("A new product has been added!","Product - info","Assets/ok.png");

        }

        }


}
