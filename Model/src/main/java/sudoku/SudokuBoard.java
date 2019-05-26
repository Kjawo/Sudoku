package sudoku;

import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuBoard implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;


    private List<List<SudokuField>> board;

    public List<List<SudokuField>> getBoard() {
        List<List<SudokuField>> boardCopy = createEmptyBoard();
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
            //row.add(new sudoku.SudokuField());
            row.set(i, board.get(x).get(i));
        }

        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(final int y) {

        List<SudokuField> column = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
           // column.add(new sudoku.SudokuField());
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
                //box.add(new sudoku.SudokuField());
                box.set((3 * i + j), board.get(boxStartX + i).get(boxStartY + j));
            }
        }

        return new SudokuBox(box);
    }

    private List<SudokuField> createEmptyRow() {
        return Arrays.asList(new SudokuField[9]);
    }

    private List<List<SudokuField>> createEmptyBoard() {
        List<List<SudokuField>> tmpBoard = Arrays.asList(createEmptyRow(), createEmptyRow(), createEmptyRow(),
                createEmptyRow(),createEmptyRow(),createEmptyRow(),createEmptyRow(),createEmptyRow(),createEmptyRow());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmpBoard.get(i).set(j, new SudokuField());
            }
        }
        return tmpBoard;
    }


    SudokuBoard() {
        board = createEmptyBoard();
    }

    SudokuBoard(List<List<SudokuField>> _board) {
        board = _board;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("Board", board)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

    @Override
    protected SudokuBoard clone() throws CloneNotSupportedException {
        return new SudokuBoard(board);
    }

    private void hideField(int x, int y) {
        set(x, y, 0);
    }

    public void adjustToLevel(Level lvl) {
        for(int i = 0; i < lvl.getNumberOfFieldsToRemove(); i++) {
            int randomX = ThreadLocalRandom.current().nextInt(0, 9);
            int randomY = ThreadLocalRandom.current().nextInt(0, 9);
            if(get(randomX, randomY) != 0) {
                set(randomX, randomY, 0);
            }
        }
    }
}