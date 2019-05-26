package sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class GameBoardController {

    @FXML
    private GridPane sudokuBoardGrid;

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardCopy = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
//    private Difficulty difficulty = new Difficulty("easy");
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;

    @FXML
    private void initialize() throws CloneNotSupportedException {
        solver.solve(sudokuBoard);
        sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();

        

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
    }

    public void onActionButtonDatabase(ActionEvent actionEvent) {
    }

    public void onActionButtonFile(ActionEvent actionEvent) {
    }
}
