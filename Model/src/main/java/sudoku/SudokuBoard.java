package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SudokuBoard implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public static final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);

    private List<List<SudokuField>> board;

    /**
     * Get copy of the board.
     * @return Copy of the board
     */
    public List<List<SudokuField>> getBoard() {
        List<List<SudokuField>> boardCopy = createEmptyBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCopy.get(i).get(j).setFieldValue(board.get(i).get(j).getFieldValue());
            }
        }
        return boardCopy;
    }

    public Boolean getIsEditable(int i, int j) {
        return isEditable[i][j];
    }

    private Boolean[][] isEditable = new Boolean[9][9];

    /**
     * Make field not editable.
     * @param i row
     * @param j column
     */
    public void makeNotEditable(int i, int j) {
        isEditable[i][j] = Boolean.FALSE;
    }

    /**
     * Check if the board is correctly filled.
     * @param considerZeroes Do consider 0?
     * @return true - board is correctly filled; false - board is incorrectly filled
     */
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

    /**
     * Get copy of the row.
     * @param x row number
     * @return return copy of the row
     */
    public SudokuRow getRow(final int x) {

        List<SudokuField> row = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            //row.add(new sudoku.SudokuField());
            row.set(i, board.get(x).get(i));
        }

        return new SudokuRow(row);
    }

    /**
     * Get copy of the column.
     * @param y column number
     * @return return copy of the column
     */
    public SudokuColumn getColumn(final int y) {

        List<SudokuField> column = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            // column.add(new sudoku.SudokuField());
            column.set(i, board.get(i).get(y));
        }

        return new SudokuColumn(column);
    }

    /**
     * Get copy of the box.
     * @param x boxStartX
     * @param y boxStartY
     * @return copy of the box
     */
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
        List<List<SudokuField>> tmpBoard =
                Arrays.asList(createEmptyRow(), createEmptyRow(), createEmptyRow(),
                              createEmptyRow(), createEmptyRow(), createEmptyRow(),
                              createEmptyRow(), createEmptyRow(), createEmptyRow());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmpBoard.get(i).set(j, new SudokuField());
            }
        }
        return tmpBoard;
    }


    SudokuBoard() {
        board = createEmptyBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isEditable[i][j] = true;
            }
        }
    }

    SudokuBoard(List<List<SudokuField>> board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                isEditable[i][j] = true;
            }
        }
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

    /**
     * Remove certain number of fields based on difficulty.
     * @param diff Difficulty level
     */
    public void adjustToLevel(Difficulty diff) {
        for (int i = 0; i < diff.numberOfFieldsToRemove; i++) {
            int randomX = ThreadLocalRandom.current().nextInt(0, 9);
            int randomY = ThreadLocalRandom.current().nextInt(0, 9);
            if (get(randomX, randomY) != 0) {
                set(randomX, randomY, 0);
            }
        }
    }
}