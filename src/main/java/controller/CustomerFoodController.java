package controller;

import dto.ProductDto;
import entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import mapper.ProductMapper;
import service.ProductService;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFoodController implements Initializable {

    @FXML
    private GridPane gridPane;

    private List<Product> getData(){

        List<ProductDto> productDtos;
        ProductService productService = new ProductService();
        productDtos = productService.getAllFoods();

        List<Product> products = new ArrayList<>();
        Product product;

        for(ProductDto productDto: productDtos){
            product = ProductMapper.dtoToEntity(productDto);

            String url= product.getImageURL();
            String[] bits =url.split("/");
            String lastOne = bits[bits.length-1];
            String photoPath = "Assets/"+lastOne;

            product.setImageURL(photoPath);
            products.add(product);
        }
        return products;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Product> products = getData();
        int column = 0;
        int row = 3;

        try {
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/gui/food_item.fxml").toURI().toURL());
                AnchorPane anchorPane = fxmlLoader.load();

                FoodItemController foodItemController = fxmlLoader.getController();
                foodItemController.setData(product);

                if (column == 2) {
                    column = 0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
