package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main extends Application {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Scene scene;
    public static Stage stage;
    private static Locale locale = new Locale("en");


    @Override
    public void start(Stage primaryStage) throws Exception{
        SudokuBoardDatabase d = new SudokuBoardDatabase("sudoku");

        d.connectionToDerby();

        //d.dropTable("boards");
        System.out.println(d.getAllTablesNames());
        System.out.println(d.checkIsTableExisting("boards"));
        d.createEmptyTable("boards");
        System.out.println(d.checkIsTableExisting("boards"));

       // Locale.setDefault(Locale.forLanguageTag("pl"));



        stage = primaryStage;

        reload();
//        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
//
//        Parent root = FXMLLoader.load(getClass().getResource("choiceWindow.fxml"), bundle);
//
//        primaryStage.setTitle(bundle.getString("tittle"));
//        primaryStage.setScene(new Scene(root));
//
//        primaryStage.sizeToScene();
//        primaryStage.show();
}


    public static void main(String[] args) {
        launch(args);
    }

    public void reload() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        Parent root = FXMLLoader.load(getClass().getResource("choiceWindow.fxml"), bundle);
        //stage.setTitle("GUI");
        stage.setTitle(bundle.getString("tittle"));
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();

    }

    public static void setLocale(Locale locale) {
        Main.locale = locale;
    }

    public static Locale getLocale() {
        return locale;
    }

}
