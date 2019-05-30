package sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChoiceWindowController {


    public ToggleGroup radio_group;
    public ComboBox comboBoxLang;

    private static Difficulty difficulty;

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public void PlayAction(ActionEvent actionEvent) {
        try {

            List<Toggle> toggles = radio_group.getToggles();
            toggles.get(0).setUserData(Difficulty.easy);
            toggles.get(1).setUserData(Difficulty.medium);
            toggles.get(2).setUserData(Difficulty.hard);

            difficulty = (Difficulty) radio_group.getSelectedToggle().getUserData();

            System.out.println(radio_group.getSelectedToggle().getUserData());

            ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"), bundle);

            Parent root1 = fxmlLoader.load();

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

    public void showAuthors(ActionEvent actionEvent) {
        Authors auth = new Authors();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Authors");
        alert.setContentText("" + auth.getString("author1") + "\n" + auth.getString("author2"));
        alert.show();
    }
}
