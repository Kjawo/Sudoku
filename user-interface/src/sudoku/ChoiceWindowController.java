package sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChoiceWindowController {


    public ToggleGroup radio_group;
    public ComboBox comboBoxLang;

    private String difficulty = "easy";

    public String getDifficulty() {
        return difficulty;
    }

    public void PlayAction(ActionEvent actionEvent) {
        try {
            difficulty = radio_group.getSelectedToggle().getUserData().toString();
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"), bundle);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.sizeToScene();
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void langAction(ActionEvent actionEvent) {

        System.out.println(comboBoxLang.getValue().toString());
        if(comboBoxLang.getValue().toString().equals("English")) {
            Locale.setDefault(Locale.forLanguageTag("en"));
        } else if(comboBoxLang.getValue().toString().equals("Polski")) {
            Locale.setDefault(Locale.forLanguageTag("pl"));
        }
    }

}
