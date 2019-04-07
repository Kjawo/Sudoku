import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {


    private ArrayList<ArrayList<SudokuField>> board;

    public ArrayList<ArrayList<SudokuField>> getBoard() {
        ArrayList<ArrayList<SudokuField>> boardCopy = createEmptyArrayListOfArrayListOfSudokuField();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCopy.get(i).get(j).setFieldValue(board.get(i).get(j).getFieldValue());
            }
        }
        return boardCopy;
    }

    public boolean checkBoard(boolean considerZeroes) {
        for (int i = 0; i < 9; i++) {
                if (!getRow(i).verify(considerZeroes) || !getColumn(i).verify(considerZeroes)) {
                    return false;
                }
            }

        for (int i = 0; i < (9 / 3); i++) {
        for (int j = 0; j < (9 / 3); j++) {
            if (!getBox(3 * i, 3 * j).verify(considerZeroes)) {
                return false;
            }
        }
    }

        return true;
    }

    public int get(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        board.get(x).get(y).setFieldValue(value);
    }

    public SudokuRow getRow(final int x) {

        List<SudokuField> row = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            //row.add(new SudokuField());
            row.set(i, board.get(x).get(i));
        }

        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(final int y) {

        List<SudokuField> column = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
           // column.add(new SudokuField());
            column.set(i, board.get(i).get(y));
        }

        return new SudokuColumn(column);
    }

    public SudokuBox getBox(final int x, final int y) {

        List<SudokuField> box = Arrays.asList(new SudokuField[9]);
        int boxStartX = (x / 3) * 3;
        int boxStartY = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //box.add(new SudokuField());
                box.set( (3 * i + j), board.get(boxStartX + i).get(boxStartY + j));
            }
        }

        return new SudokuBox(box);
    }

    private ArrayList<ArrayList<SudokuField>> createEmptyArrayListOfArrayListOfSudokuField() {
        ArrayList<ArrayList<SudokuField>> tmpBoard = new ArrayList<ArrayList<SudokuField>>(9);
        for (int i = 0; i < 9; i++) {
            tmpBoard.add(new ArrayList<SudokuField>(9));
            for (int j = 0; j < 9; j++) {
                tmpBoard.get(i).add(new SudokuField());
//                tmpBoard.get(i).set(j, new SudokuField());
            }
        }
        return tmpBoard;
    }


    SudokuBoard() {
        board = createEmptyArrayListOfArrayListOfSudokuField();
    }

}