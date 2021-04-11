package controller;

import dto.ProductDto;
import entity.Product;
import exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mapper.ProductMapper;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;

public class FoodItemController {

    @FXML
    private ImageView foodItemView;

    @FXML
    private Label foodItemName;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label gramsLabel;

    @FXML
    private Label fastingLabel;

    @FXML
    private Label priceLabel;

    private Product product;

    @FXML
    void addOrderOnAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/gui/customer_menu.fxml").toURI().toURL());
            fxmlLoader.load();

            CustomerMenuController customerMenuController = fxmlLoader.getController();

            addToOrder(customerMenuController);


        } catch (IOException | InvalidFastingItemException | InvalidCategoryException | InvalidUrlException | InvalidQuantityException | InvalidWeightException | InvalidProductNameException | InvalidPriceException e) {
            e.printStackTrace();
        }
    }


    private void addToOrder(CustomerMenuController customerMenuController) throws InvalidFastingItemException, InvalidPriceException, InvalidCategoryException, InvalidWeightException, InvalidQuantityException, InvalidProductNameException, InvalidUrlException {
        ProductService foodService = new ProductService();
        Product productDB = ProductMapper.dtoToEntity( foodService.getProdByName(product.getName()));
        if(productDB.getQuantity()!=0){
            ProductDto orderedProduct= ProductMapper.entityToDto(productDB);
            orderedProduct.setQuantity(1);

            boolean ok=false;
            for(ProductDto update:customerMenuController.getOrderedProducts()){
                if(update.getName().equals(orderedProduct.getName())){
                    update.setQuantity(update.getQuantity()+1);
                    ok=true;
                    break;
                }
            }
            if(!ok)
                customerMenuController.getOrderedProducts().add(orderedProduct);
            else{
                ObservableList<ProductDto> copy=FXCollections.observableArrayList(customerMenuController.getOrderedProducts());

                customerMenuController.getOrderedProducts().clear();
                customerMenuController.getOrderedProducts().addAll(copy);
            }

            ApplicationUtils.totalPrice.setValue(customerMenuController.getTotalPrice());
            productDB.setQuantity(productDB.getQuantity()-1);
            foodService.updateProduct(ProductMapper.entityToDto(productDB));
        }else
        {
            ApplicationUtils.displayWarningMessage("No left products to add in your order!","Order - warning","Assets/warning.png");

            System.out.println("No products");
        }
    }


    @FXML
    void detailsOnAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/gui/food_item_details.fxml").toURI().toURL());
            Parent root = fxmlLoader.load();

            FoodItemDetailsController foodItemDetailsController = fxmlLoader.getController();
            foodItemDetailsController.setProduct(product);
            Stage stage = new Stage();

            stage.setTitle("Product Details");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(Product product){
        this.product = product;
        File foodFile = new File(product.getImageURL());
        Image foodImage = new Image(foodFile.toURI().toString());
        foodItemView.setImage(foodImage);
        foodItemName.setText(product.getName());
        categoryLabel.setText("#"+product.getCategory());
        gramsLabel.setText(product.getWeight() + " g");
        priceLabel.setText(product.getPrice() + " lei");

        if(product.isFastingItem()){
            fastingLabel.setText("Yes");
        } else{
            fastingLabel.setText("No");
        }

    }
}
