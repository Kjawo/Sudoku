package sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class ChoiceWindowController {


    public ToggleGroup radio_group;
    public ComboBox comboBoxLang;
    public CheckBox dbCheckbox;


    private static Difficulty difficulty;

    public static SudokuBoard getLoadedSudokuBoard() {
        return loadedSudokuBoard;
    }

    private static SudokuBoard loadedSudokuBoard;

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

            //System.out.println(radio_group.getSelectedToggle().getUserData());

            Main.logger.info("Difficulty level changed to: ".concat(radio_group.getSelectedToggle().getUserData().toString()));

            ResourceBundle bundle = ResourceBundle.getBundle("messages", Main.getLocale());
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

    public void langAction(ActionEvent actionEvent) throws Exception {

        //System.out.println(comboBoxLang.getValue().toString());

        Main.logger.info("Language changed to: ".concat(comboBoxLang.getValue().toString()));

        if (comboBoxLang.getValue().toString().equals("English")) {
            if (!Main.getLocale().toString().equals("en")) {
                Main.setLocale(new Locale("en")); // change to english
                Main.stage.close();
                Main reload = new Main();
                reload.reload();
            }


        } else if (comboBoxLang.getValue().toString().equals("Polski")) {
            if (!Main.getLocale().toString().equals("pl")) {
                Main.setLocale(new Locale("pl")); // change to polish
                Main.stage.close();
                Main reload = new Main();
                reload.reload();
            }
        }
    }

    public void showAuthors(ActionEvent actionEvent) {
        Authors auth = new Authors();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Main.getLocale());
        ButtonType applyButton = new ButtonType(bundle.getString("apply"), ButtonBar.ButtonData.APPLY);
        Alert alert = new Alert(Alert.AlertType.NONE, "", applyButton);
        alert.setTitle(bundle.getString("authors"));
        alert.setContentText("" + auth.getString("author1") + "\n" + auth.getString("author2"));
        alert.show();
    }

    public void loadButton(ActionEvent actionEvent) {
        if(dbCheckbox.isSelected()) {
            // Create the new dialog
            TextFieldInputDialog dialog = new TextFieldInputDialog();
            dialog.setHeaderText(null);
            dialog.setGraphic(null);

            // Show the dialog and capture the result.
            Optional result = dialog.showAndWait();

            // If the "Okay" button was clicked, the result will contain our String in the get() method
            if (result.isPresent()) {
                try {
                    JdbcSudokuBoardDao dbDao = SudokuBoardDaoFactory.getJdbcDao(result.get().toString());
                    loadedSudokuBoard = dbDao.read();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            FileChooser fileChooser = new FileChooser();
            try {
                File file = fileChooser.showOpenDialog(null);
                FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao(file.getName());
                loadedSudokuBoard = fileSudokuBoardDao.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
