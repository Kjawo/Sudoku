package sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardCopy = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
//    Difficulty difficulty = easy;
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;

    @FXML
    private void initialize() throws CloneNotSupportedException {
        solver.solve(sudokuBoard);
        sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choiceWindow.fxml"));
        ChoiceWindowController choiceWindowController = fxmlLoader.getController();
//        String difficulty = choiceWindowController.getDifficulty();
        sudokuBoard.adjustToLevel(new Level(5));
//        difficultyLevel.chooseLevel(sudokuBoard, ChoiceWindowController);

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
            }
        }
    }
    public void onActionButtonCheck(ActionEvent actionEvent) {
        String popUpText;
        if (sudokuBoard.checkBoard(true)) {
            popUpText = "You win";
        } else {
            popUpText = "You loose";
        }
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(popUpText));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
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
