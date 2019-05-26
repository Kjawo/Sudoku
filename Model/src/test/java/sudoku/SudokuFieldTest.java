package sudoku;

import org.junit.Assert;
import org.junit.Test;
import sudoku.SudokuField;

public class SudokuFieldTest {

    @Test
    public void equalsTest() {
        SudokuField f1 = new SudokuField(2);
        SudokuField f2 = new SudokuField(2);
        Assert.assertTrue(f1.equals(f2));
        Assert.assertTrue(f2.equals(f1));
        SudokuField f3 = new SudokuField(4);
        SudokuField f4 = new SudokuField(5);
        Assert.assertFalse(f3.equals(f4));
        Assert.assertFalse(f4.equals(f3));
    }

    @Test
    public void hashCodeTest() {
        SudokuField f1 = new SudokuField(2);
        SudokuField f2 = new SudokuField(2);
        Assert.assertEquals(f1.hashCode(), f2.hashCode());
        SudokuField f3 = new SudokuField(4);
        SudokuField f4 = new SudokuField(5);
        Assert.assertNotEquals(f3.hashCode(), f4.hashCode());
    }

    @Test
    public void toStringTest() {
        SudokuField f1 = new SudokuField(2);
        Assert.assertEquals(f1.toString(), "2");
    }

    @Test
    public void comparableTest() {
        SudokuField f1 = new SudokuField(2);
        SudokuField f2 = new SudokuField(2);
        Assert.assertEquals(f1.compareTo(f2), 0);
        f2.setFieldValue(3);
        Assert.assertEquals(f1.compareTo(f2), -1);
        f2.setFieldValue(1);
        Assert.assertEquals(f1.compareTo(f2), 1);
    }

    @Test
    public void cloneableTest() {
        SudokuField f1 = new SudokuField(2);

        SudokuField f2 = null;
        try {
            f2 = f1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(f1.compareTo(f2), 0);

    }
}