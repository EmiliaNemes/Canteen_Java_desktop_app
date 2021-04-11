package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class ApplicationStart extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = new File("src/main/java/gui/main_menu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setTitle("Welcome to Cantina UTCN!");
        primaryStage.setX(350);
        primaryStage.setY(50);
        primaryStage.setScene(new Scene(root, 593, 403));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
