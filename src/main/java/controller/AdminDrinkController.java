package controller;

import dto.ProductDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.ProductRepo;
import service.EmailService;
import service.ProductService;
import utils.ApplicationUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class AdminDrinkController implements Initializable {

    @FXML
    private ImageView searchLogo, logoView;


    public TableColumn<ProductDto, String> imageColumn;
    public TableColumn<ProductDto, String> imageColumn1;
    public TableColumn<ProductDto, String> nameColumn;
    public TableColumn<ProductDto, String> quantityColumn;
    public TableColumn<ProductDto, String> priceColumn;
    public TableColumn<ProductDto, String> weightColumn;
    public TableView<ProductDto> TableDrinks;
    public Button searchButton;
    public TextField textFieldSearch;
    public AnchorPane adminDrinkView;

    ObservableList<ProductDto> data = FXCollections.observableArrayList();
    ProductService prodServ = new ProductService();

    private final static Logger log = Logger.getLogger(AdminDrinkController.class.getName());

    protected static ProductDto product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File searchFile = new File("Assets/search.png");
        Image searchImage = new Image(searchFile.toURI().toString());
        searchLogo.setImage(searchImage);

        File logoFile = new File("Assets/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoView.setImage(logoImage);

        List<ProductDto> listProd = prodServ.getProdByCategory("Drink");

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
        TableDrinks.setItems(dataList);
    }


    public void searchButtonOnAction(ActionEvent actionEvent) {

        ObservableList<ProductDto> newData = FXCollections.observableArrayList();
        String search = textFieldSearch.getText().toLowerCase();
        for (ProductDto product : data) {

            if (product.getName().toLowerCase().contains(search) || product.getCategory().toLowerCase().contains(search))
                newData.add(product);
        }
        populateTableView(newData);
    }

    public void deleteSelectedItemButtonOnAction(ActionEvent actionEvent) {
        ProductDto prodDto = TableDrinks.getSelectionModel().getSelectedItem();
        data.remove(prodDto);
        prodServ.removeProduct(prodDto.getId());
        populateTableView(data);
        // E
        ApplicationUtils.displayOkMessage("The selected product has been deleted!","Product - info","Assets/ok.png");
        log.info("Product successfully deleted");
    }

    public void addNewDrinkButtonOnAction(ActionEvent event) {
        try {
            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            AdminMenu myAdmin = loader.getController();

            URL url_drink = new File("src/main/java/gui/admin_add_drink.fxml").toURI().toURL();
            Parent root_drink = FXMLLoader.load(url_drink);

            myAdmin.getAdminMainPane().getChildren().setAll(root_drink.getChildrenUnmodifiable());

            Scene mainMenuScene = new Scene(root, 980, 670);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void editSelectedItemButtonOnAction(ActionEvent event) {
        try {
            product = TableDrinks.getSelectionModel().getSelectedItem();


            URL url = new File("src/main/java/gui/admin_menu.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            AdminMenu myAdmin = (AdminMenu) loader.getController();

            URL url_drink = new File("src/main/java/gui/admin_edit_drink.fxml").toURI().toURL();
            Parent root_drink = FXMLLoader.load(url_drink);

            myAdmin.getAdminMainPane().getChildren().setAll(root_drink.getChildrenUnmodifiable());

            Scene mainMenuScene = new Scene(root, 980, 670);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(mainMenuScene);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
