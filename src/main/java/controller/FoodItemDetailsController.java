package controller;

import entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodItemDetailsController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label gramsLabel;

    @FXML
    private Label fastingLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;


    public void setProduct(Product product){

        String url = product.getImageURL();
        String[] bits =url.split("/");
        String lastOne = bits[bits.length-1];
        String photoPath = "/AssetsBis/"+product.getCategory().toUpperCase().replaceAll("\\s+","-")+"/"+lastOne;
        Image photo= new Image(getClass().getResourceAsStream(photoPath));
        imageView.setImage(photo);

        nameLabel.setText(product.getName());
        gramsLabel.setText(product.getWeight().toString() + " g");
        if(product.isFastingItem()){
            fastingLabel.setText("Yes");
        } else {
            fastingLabel.setText("No");
        }

        priceLabel.setText(product.getPrice().toString() + " lei");
        if(product.getDescription()==null)
            descriptionLabel.setText("None");
        else
        descriptionLabel.setText(product.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}