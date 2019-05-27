package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Locale.setDefault(Locale.forLanguageTag("en"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        Parent root = FXMLLoader.load(getClass().getResource("choiceWindow.fxml"), bundle);

        primaryStage.setTitle(bundle.getString("tittle"));
        primaryStage.setScene(new Scene(root, 600, 400));

        Authors auth = new Authors();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Authors");
        alert.setContentText("" + auth.getString("author1") + "\n" + auth.getString("author2"));
        alert.show();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
