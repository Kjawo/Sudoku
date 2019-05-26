package sudoku;

import org.junit.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuSolver;

import static junit.framework.TestCase.assertEquals;

public class BacktrackingSudokuSolverTest {

    @Test
    public void solveTest() {
        SudokuBoard instance = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(instance);
        assertEquals(instance.checkBoard(true), true);
    }
}