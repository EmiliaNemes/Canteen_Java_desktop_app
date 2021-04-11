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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mapper.AdministratorMapper;
import service.AdministratorService;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminAddProductController {

        public ComboBox<String> category;
        public TextField description;
        public ComboBox<String> fasting;
        public TextField price;
        public TextField quantity;
        public TextField weight;
        public TextField image;
        public TextField prodName;

    @FXML
    private Label productNameMessage;

    @FXML
    private Label categoryMessage;

    @FXML
    private Label fastingItemMessage;

    @FXML
    private Label priceMessage;

    @FXML
    private Label quantityMessage;

    @FXML
    private Label weightMessage;

    @FXML
    private Label imageURLMessage;

    public void cancelButtonOnAction(ActionEvent event){
        goToAdminFoodMenu(event);
    }

    private void goToAdminFoodMenu(ActionEvent event) {
        try {
         URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
         FXMLLoader loader = new FXMLLoader(url);
         Parent root = loader.load();

         AdminMenu myAdmin = loader.getController();

         URL url_drink = new File("src/main/java/gui/admin_food_view.fxml").toURI().toURL();
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

            categoryMessage.setText("");
            productNameMessage.setText("");
            fastingItemMessage.setText("");
            priceMessage.setText("");
            quantityMessage.setText("");
            weightMessage.setText("");
            imageURLMessage.setText("");

            try {
                newProduct.setName(prodName.getText());
                newProduct.setCategory(category.getValue());
                newProduct.setDescription(description.getText());
                newProduct.setFastingItem(fasting.getValue());
                newProduct.setPrice(Double.parseDouble(price.getText()));
                newProduct.setQuantity(Integer.parseInt(quantity.getText()));
                newProduct.setWeight(Integer.parseInt(weight.getText()));
                newProduct.setImageURL(image.getText());

                ProductService productService = new ProductService();
                productService.addProduct(newProduct);

            } catch (InvalidCategoryException e) {
                categoryMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidProductNameException e) {
                productNameMessage.setText(e.getMessage());
                ok = false;
            } catch (InvalidFastingItemException e) {
                fastingItemMessage.setText(e.getMessage());
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
                ApplicationUtils.displayOkMessage("A new product has been added!","Product - info","Assets/ok.png");
                goToAdminFoodMenu(actionEvent);
            }
    }


}
