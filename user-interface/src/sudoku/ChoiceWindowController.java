package sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ChoiceWindowController {


    public ToggleGroup radio_group;

    private String difficulty = "easy";

    public String getDifficulty() {
        return difficulty;
    }

    public void PlayAction(ActionEvent actionEvent) {
        try {
            difficulty = radio_group.getSelectedToggle().getUserData().toString();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.sizeToScene();
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
