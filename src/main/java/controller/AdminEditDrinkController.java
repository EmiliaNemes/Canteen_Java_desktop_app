package controller;

import dto.ProductDto;
import exception.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminEditDrinkController implements Initializable {

    public TextField price;
    public TextField quantity;
    public TextField weight;
    public TextField image;
    public TextField prodName;
    ProductDto product;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        product = AdminDrinkController.product;

        prodName.setText(product.getName());
        price.setText(product.getPrice().toString());
        quantity.setText(product.getQuantity().toString());
        weight.setText(product.getWeight().toString());
        image.setText(product.getImageURL());

    }

    public void cancelButtonOnAction(ActionEvent event) {
        goToAdminDrinkMenu(event);
    }

    private void goToAdminDrinkMenu(ActionEvent event) {
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
        ProductDto updateProduct = new ProductDto();
        boolean ok = true;

        drinkNameMessage.setText("");
        priceMessage.setText("");
        quantityMessage.setText("");
        weightMessage.setText("");
        imageURLMessage.setText("");

        try {
            updateProduct.setId(product.getId());
            updateProduct.setName(prodName.getText());
            updateProduct.setCategory("Drink");
            updateProduct.setDescription("");
            updateProduct.setFastingItem(true);
            updateProduct.setPrice(Double.parseDouble(price.getText()));
            updateProduct.setQuantity(Integer.parseInt(quantity.getText()));
            updateProduct.setWeight(Integer.parseInt(weight.getText()));
            updateProduct.setImageURL(image.getText());

            ProductService productService = new ProductService();
            productService.updateProduct(updateProduct);

            goToAdminDrinkMenu(actionEvent);
            // E
            ApplicationUtils.displayOkMessage("Product has been updated!","Product - info","Assets/ok.png");


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

    }

}