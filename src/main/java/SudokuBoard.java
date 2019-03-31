import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    private SudokuField[][] board;

    public SudokuField[][] getBoard() {
        SudokuField[][] boardCopy = new SudokuField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCopy[i][j].setFieldValue( board[i][j].getFieldValue() );
            }
        }
        return boardCopy;
    }

    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
                if (!getRow(i).verify() || !getColumn(i).verify()) {
                    return false;
                }
            }

        for (int i = 0; i < (9 / 3); i++) {
        for (int j = 0; j < (9 / 3); j++) {
            if (!getBox(3 * i, 3 * j).verify()) {
                return false;
            }
        }
    }

        return true;
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public SudokuRow getRow(final int x) {

        SudokuField[] row = new SudokuField[9];

        for (int i = 0; i < row.length; i++) {
            row[i] = board[x][i];
        }

        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(final int y) {

        SudokuField[] column = new SudokuField[9];

        for (int i = 0; i < column.length; i++) {
            column[i] = board[i][y];
        }

        return new SudokuColumn(column);
    }

    public SudokuBox getBox(final int x, final int y) {

        SudokuField[] box = new SudokuField[9];
        int boxStartX = (x / 3) * 3;
        int boxStartY = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[3 * i + j] = board[boxStartX + i][boxStartY + j];
            }
        }

        return new SudokuBox(box);
    }

    SudokuBoard() {
        board = new SudokuField[9][9];
        for(int i = 0; i < 9; i++) {
            board[i] = new SudokuField[9];
            for(int j = 0; j < 9; j++){
                board[i][j] = new SudokuField();
            }
        }
    }

}