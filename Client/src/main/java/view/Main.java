package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String fxmlFile = "/fx/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add("/fx/style.css");
        primaryStage.show();

        // Создаем модальное окно для log in
        LogWindow logWindow = new LogWindow();
        logWindow.launchWindow();

    }


    public static void main(String[] args) throws IOException {
        launch(args);

    }

}
