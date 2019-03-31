import org.junit.Test;

import static org.junit.Assert.*;

public class FieldArrayTest {

    @Test
    public void verifyCheck1() {
        SudokuField[] sf = new SudokuField[9];
        for(int i = 0; i < 9; i++) {
            sf[i] = new SudokuField();
            sf[i].setFieldValue(i);
        }
        FieldArray fa = new FieldArray(sf);
        assertEquals(true, fa.verify());
    }

    @Test
    public void verifyCheck2() {
        SudokuField[] sf = new SudokuField[9];
        for(int i = 0; i < 9; i++) {
            sf[i] = new SudokuField();
            sf[i].setFieldValue(1);
        }
        FieldArray fa = new FieldArray(sf);
        assertEquals(false, fa.verify());
    }
}