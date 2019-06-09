package sudoku;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameBoardController {

    @FXML
    private javafx.scene.control.CheckBox dbCheckbox2;

    @FXML
    private GridPane sudokuBoardGrid;
    TextField[][] textFields = new TextField[9][9];



    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser;

    private Difficulty difficulty;

    @FXML
    private void initialize() throws CloneNotSupportedException, EmptyBoardException {
        if(ChoiceWindowController.getLoadedSudokuBoard() == null) {
            sudokuBoard = new SudokuBoard();
            solver.solve(sudokuBoard);
            difficulty = ChoiceWindowController.getDifficulty();
            sudokuBoard.adjustToLevel(difficulty);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    TextField textField = new TextField();
                    textField.setMinSize(50, 50);
                    textField.setFont(Font.font(18));
                    if (sudokuBoard.get(i, j) != 0) {
                        sudokuBoard.makeNotEditable(i, j);
                        textField.setDisable(true);
                        textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                    }
                    sudokuBoardGrid.add(textField, i, j);
                    textFields[i][j] = textField;
                }
            }
        } else {
            sudokuBoard = ChoiceWindowController.getLoadedSudokuBoard();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    TextField textField = new TextField();
                    textField.setMinSize(50, 50);
                    textField.setFont(Font.font(18));
                    if (sudokuBoard.get(i, j) != 0) {
                        if(!sudokuBoard.getIsEditable(i, j)) {
                            textField.setDisable(true);
                        }
                        textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                    }
                   // else textField.setText("");
                    sudokuBoardGrid.add(textField, i, j);
                    textFields[i][j] = textField;
                }
            }
        }

        int counter = 0;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(textFields[i][j] == null) {
                    counter++;
                }
                else break;
            }
        }
        if(counter == 81) throw new EmptyBoardException("Sudoku Board is empty");


    }

    private void textFieldBoardToInstance() {
        ObservableList<Node> children = sudokuBoardGrid.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(textFields[i][j].getText() == null) {
                    sudokuBoard.set(i, j, 0);
                } else if(textFields[i][j].getText().equals("")) {
                    sudokuBoard.set(i, j, 0);
                } else {
                    sudokuBoard.set(i, j, Integer.parseInt(textFields[i][j].getText()));
                }
            }
        }
    }

    public void onActionButtonCheck(ActionEvent actionEvent) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", Main.getLocale());
        ButtonType applyButton = new ButtonType(bundle.getString("apply"), ButtonBar.ButtonData.APPLY);
        Alert alert = new Alert(Alert.AlertType.NONE, "", applyButton);
        alert.setTitle(bundle.getString("info"));
        try {
            textFieldBoardToInstance();
            String popUpText;

            if (sudokuBoard.checkBoard(true)) {
                popUpText = bundle.getString("win");
                Main.logger.info("Player won");
            } else {
                popUpText = bundle.getString("loss");
                Main.logger.info("Player lost");
            }

            alert.setContentText(popUpText);
            alert.show();
        } catch (Exception e) {
            alert.setTitle(bundle.getString("info"));
            alert.setContentText(bundle.getString("invalid"));
            Main.logger.info("Invalid input");
            alert.show();
        }
    }

    public void onActionButtonSave(ActionEvent actionEvent) {
        if (dbCheckbox2.isSelected()) {
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
                    dbDao.write(sudokuBoard);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            fileChooser = new FileChooser();
            try {
                textFieldBoardToInstance();
                File file = fileChooser.showSaveDialog(null);
                fileSudokuBoardDao = new FileSudokuBoardDao(file.getName());
                fileSudokuBoardDao.write(sudokuBoard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
