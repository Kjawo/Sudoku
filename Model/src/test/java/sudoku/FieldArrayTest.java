package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.FieldArray;
import sudoku.SudokuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FieldArrayTest {

    @Test
    public void verifyCheck1() {
        ArrayList<SudokuField> sf = new ArrayList<SudokuField>(9);
        for (int i = 0; i < 9; i++) {
            sf.add(new SudokuField());
        }
        for (int i = 0; i < 9; i++) {
            sf.set(i, new SudokuField());
            sf.get(i).setFieldValue(i);
        }
        FieldArray fa = new FieldArray(sf);
        assertTrue(fa.verify(false));
    }

    @Test
    public void verifyCheck2() {
        ArrayList<SudokuField> sf = new ArrayList<SudokuField>(9);
        for (int i = 0; i < 9; i++) {
            sf.add(new SudokuField());
        }
        for (int i = 0; i < 9; i++) {
            sf.set(i, new SudokuField());
            sf.get(i).setFieldValue(1);
        }
        FieldArray fa = new FieldArray(sf);
        assertEquals(false, fa.verify(true));
    }

    @Test
    public void equalsTest() {
        List<SudokuField> l1= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l1.set(i, new SudokuField(i));
        }
        FieldArray f1 = new FieldArray(l1);
        List<SudokuField> l2= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l2.set(i, new SudokuField(i));
        }
        FieldArray f2 = new FieldArray(l2);
        Assert.assertTrue(f1.equals(f2));
        Assert.assertTrue(f2.equals(f1));
        List<SudokuField> l3= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l3.set(i, new SudokuField(i));
        }
        FieldArray f3 = new FieldArray(l3);
        List<SudokuField> l4= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 8; i++) {
            l4.set(i, new SudokuField(i));
        }
        l4.set(8, new SudokuField(3));
        FieldArray f4 = new FieldArray(l4);
        Assert.assertFalse(f3.equals(f4));
        Assert.assertFalse(f4.equals(f3));
    }

    @Test
    public void hashCodeTest() {
        List<SudokuField> l1= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l1.set(i, new SudokuField(i));
        }
        FieldArray f1 = new FieldArray(l1);
        List<SudokuField> l2= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l2.set(i, new SudokuField(i));
        }
        FieldArray f2 = new FieldArray(l2);
        Assert.assertEquals(f1.hashCode(), f2.hashCode());

        List<SudokuField> l3= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l3.set(i, new SudokuField(i));
        }
        FieldArray f3 = new FieldArray(l3);
        List<SudokuField> l4= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 8; i++) {
            l4.set(i, new SudokuField(i));
        }
        l4.set(8, new SudokuField(3));
        FieldArray f4 = new FieldArray(l4);
        Assert.assertNotEquals(f3.hashCode(), f4.hashCode());
    }

    @Test
    public void toStringTest() {
        List<SudokuField> l1= Arrays.asList(new SudokuField[9]);
        for(int i = 0; i < 9; i++) {
            l1.set(i, new SudokuField(i));
        }
        FieldArray f1 = new FieldArray(l1);
        Assert.assertEquals(f1.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8]");
    }
}