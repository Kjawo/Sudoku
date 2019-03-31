import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class BacktrackingSudokuSolverTest {

    @Test
    public void solveTest() {
        SudokuBoard instance = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(instance);

    }
}