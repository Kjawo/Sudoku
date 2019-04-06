import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FieldArrayTest {

    @Test
    public void verifyCheck1() {
        ArrayList<SudokuField> sf = new ArrayList<SudokuField>(9);
        for(int i = 0; i < 9; i++) {
            sf.add(new SudokuField());
        }
        for(int i = 0; i < 9; i++) {
            sf.set(i, new SudokuField());
            sf.get(i).setFieldValue(i);
        }
        FieldArray fa = new FieldArray(sf);
        assertEquals(true, fa.verify(true));
    }

    @Test
    public void verifyCheck2() {
        ArrayList<SudokuField> sf = new ArrayList<SudokuField>(9);
        for(int i = 0; i < 9; i++) {
            sf.add(new SudokuField());
        }
        for(int i = 0; i < 9; i++) {
            sf.set(i, new SudokuField());
            sf.get(i).setFieldValue(1);
        }
        FieldArray fa = new FieldArray(sf);
        assertEquals(false, fa.verify(true));
    }
}