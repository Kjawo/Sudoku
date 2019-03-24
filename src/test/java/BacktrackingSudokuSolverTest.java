import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class BacktrackingSudokuSolverTest {

    @Test
    public void solveTest() {
        SudokuBoard instance = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(instance);
        int[][] board = instance.getBoard();
        for (int i = 0; i < 9; i++) { //first check in rows and columns
            List<Integer> column = new ArrayList<>();
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (row.contains(board[i][j])) {
                    fail("Number is duplicated in row");
                }
                row.add(board[i][j]);
                if (column.contains(board[j][i])) {
                    fail("Number is duplicated in column");
                }
                column.add(board[j][i]);
            }
        }
        List<Integer> matrixInArray = new ArrayList<>();
        for (int i = 0; i < 9; i++) { //transform matrix board into array
            for (int j = 0; j < 9; j++) {
                matrixInArray.add(board[i][j]);
            }
        }
        int last = 0;
        for (int i = 0; i < 9; i++) { //now check in each square
            List<Integer> square = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int position = k + j * 9 + i * 3 + last; // position of numbers in square
                    if (square.contains(matrixInArray.get(position))) {
                        fail("Number is duplicated in square"); //if number is duplicated in square
                    }
                    square.add(matrixInArray.get(position));
                }
            }
            if (i % 3 == 2) {
                last += 18; // number for "jump" to square below "row" of square
            }
        }
    }
}