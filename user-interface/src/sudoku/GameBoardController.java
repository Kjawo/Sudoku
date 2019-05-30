package sudoku;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.apache.commons.lang3.builder.Diff;

import java.io.File;

import static sudoku.Difficulty.*;

public class GameBoardController {


    @FXML
    private GridPane sudokuBoardGrid;
    TextField[][] textFields = new TextField[9][9];


    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardCopy = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;

    private Difficulty difficulty;

    @FXML
    private void initialize() throws CloneNotSupportedException {
        solver.solve(sudokuBoard);
        difficulty = ChoiceWindowController.getDifficulty();
        sudokuBoard.adjustToLevel(difficulty);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                sudokuBoardGrid.add(textField, i, j);
                textFields[i][j] = textField;
            }
        }
    }

    private void textFieldBoardToInstance() {
        ObservableList<Node> children = sudokuBoardGrid.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
            }
        }
    }

    public void onActionButtonCheck(ActionEvent actionEvent) {
        try {
            textFieldBoardToInstance();
            String popUpText;
            if (sudokuBoard.checkBoard(true)) {
                popUpText = "You win";
            } else {
                popUpText = "You loose";
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Authors");
            alert.setContentText(popUpText);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Authors");
            alert.setContentText("Invalid input");
            alert.show();
        }
    }

    public void onActionButtonSave(ActionEvent actionEvent) {
        fileChooser = new FileChooser();
        try {
            File file = fileChooser.showSaveDialog(null);
            fileSudokuBoardDao = new FileSudokuBoardDao(file.getName());
            fileSudokuBoardDao.write(sudokuBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
