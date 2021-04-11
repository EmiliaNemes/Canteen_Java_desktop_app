package controller;

import dto.AdministratorDto;
import dto.ProductDto;
import exception.InvalidUrlException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.ProductRepo;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminFoodController implements Initializable {
    //  public TableColumn<ProductDto, String> imageColumn;
    public TableColumn<ProductDto, String> imageColumn1;
    public TableColumn<ProductDto, String> nameColumn;
    public TableColumn<ProductDto, String> categoryColumn;
    public TableColumn<ProductDto, String> descriptionColumn;
    public TableColumn<ProductDto, String> quantityColumn;
    public TableColumn<ProductDto, String> priceColumn;
    public TableColumn<ProductDto, String> fastingColumn;
    public TableColumn<ProductDto, String> weightColumn;
    public TableView<ProductDto> foodTable;
    public Button searchButton;
    public TextField textFieldSearch;
    public AnchorPane adminFoodView;

    ObservableList<ProductDto> data = FXCollections.observableArrayList();

    ProductService prodServ = new ProductService();

    protected static ProductDto product;

    @FXML
    private ImageView searchLogo, logoView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File searchFile = new File("Assets/search.png");
        Image searchImage = new Image(searchFile.toURI().toString());
        searchLogo.setImage(searchImage);

        File logoFile = new File("Assets/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoView.setImage(logoImage);
        List<ProductDto> listProd = prodServ.getAllFoods();

        for(ProductDto p: listProd) {
            try {
            String urlPhoto = p.getImageURL();
            ImageView photo = new ImageView(new Image(this.getClass().getResourceAsStream(urlPhoto)));
            p.setImage(photo);
            imageColumn1.setPrefWidth(80);
            imageColumn1.setCellValueFactory(new PropertyValueFactory<>("image"));
            }
            catch (NullPointerException | IllegalArgumentException e){
                ProductRepo pr = new ProductRepo();
                pr.removeProduct(p.getId());
            }
        }

        data = FXCollections.observableArrayList(listProd);
        populateTableView(data);

    }

    private void populateTableView(ObservableList<ProductDto> dataList) {
        foodTable.setItems(dataList);
    }

    public void searchButtonOnAction(ActionEvent event){
        ObservableList<ProductDto> newData = FXCollections.observableArrayList();
        String search = textFieldSearch.getText().toLowerCase();
        for (ProductDto product : data) {
            if (product.getName().toLowerCase().contains(search) || product.getCategory().toLowerCase().contains(search))
                newData.add(product);
        }
        populateTableView(newData);
    }

    public void deleteSelectedItemButtonOnAction(ActionEvent actionEvent) {
        ProductDto prodDto = foodTable.getSelectionModel().getSelectedItem();
        prodServ.removeProduct(prodDto.getId());
        data.remove(prodDto);
        populateTableView(data);
        // E
        ApplicationUtils.displayOkMessage("The selected product has been deleted!","Product- info","Assets/ok.png");
    }

    public void editSelectedItemButtonOnAction(ActionEvent event) {
        try {
            product = foodTable.getSelectionModel().getSelectedItem();
            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            AdminMenu myAdmin = (AdminMenu) loader.getController();

            URL url_drink = new File("src/main/java/gui/admin_edit_product.fxml").toURI().toURL();
            Parent root_drink = FXMLLoader.load(url_drink);

            myAdmin.getAdminMainPane().getChildren().setAll(root_drink.getChildrenUnmodifiable());

            Scene mainMenuScene= new Scene(root, 980,670);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewProductButtonOnAction(ActionEvent event) {
        try {
            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            AdminMenu myAdmin = (AdminMenu) loader.getController();

            URL url_drink = new File("src/main/java/gui/admin_add_product.fxml").toURI().toURL();
            Parent root_drink = FXMLLoader.load(url_drink);

            myAdmin.getAdminMainPane().getChildren().setAll(root_drink.getChildrenUnmodifiable());

            Scene mainMenuScene= new Scene(root, 980,670);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
