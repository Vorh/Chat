package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class LogWindow  {

    public  static void launchWindow() throws Exception{
        Stage stage = new Stage();
        String fxmlFile = "E:\\Set\\GitHub\\Chat\\Client\\src\\main\\resources\\fx\\logWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        InputStream stream = new FileInputStream(fxmlFile);
        Parent root = (Parent) loader.load(stream);
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.getScene().getStylesheets().add("/fx/style.css");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        // Закрытие приложение при нажатие на крестик
        stage.setOnCloseRequest(event ->{
            System.exit(12);
        });

        stage.show();
    }

}
