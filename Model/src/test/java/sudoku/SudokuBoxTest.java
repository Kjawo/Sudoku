package sudoku;

import org.junit.Test;
import sudoku.SudokuBox;
import sudoku.SudokuField;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SudokuBoxTest {

    @Test
    public void cloneableTest() {
        List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[9]);

        for(int i = 0; i < 9; i++) {
            sudokuFields.set(i, new SudokuField(i+1));
        }

        SudokuBox f1 = new SudokuBox(sudokuFields);

        SudokuBox f2 = null;
        try {
            f2 = f1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assertEquals(f1, f2);

    }
}