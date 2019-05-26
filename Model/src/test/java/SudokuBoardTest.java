import junit.framework.TestCase;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;


public class SudokuBoardTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSetGet() {
        SudokuBoard instance = new SudokuBoard();
        instance.set(0, 0, 3);
        assertEquals(instance.get(0, 0), 3);
    }

    @Test
    public void testCheckBoard1() {
        SudokuBoard instance = new SudokuBoard();
        int goodBoard[][] = {

                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        ArrayList<ArrayList<SudokuField>> gB = new ArrayList<>(9);
        for (int[] ints : goodBoard) {
            ArrayList<SudokuField> list = new ArrayList<>(9);
            for (int i : ints) {
                list.add(new SudokuField(i));
            }
            gB.add(list);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance.set(i, j, goodBoard[i][j]);
            }
        }

        assertEquals(true, instance.checkBoard(true));
    }

    @Test
    public void testCheckBoard2() {
        SudokuBoard instance = new SudokuBoard();
        int wrongBoard[][] = {

                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 1, 6, 8, 5, 3, 7, 9, 1}, //1 twice
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance.set(i, j, wrongBoard[i][j]);
            }
        }
        assertEquals(false, instance.checkBoard(true));

    }


    @Test(expected = UnsupportedOperationException.class)
    public void testFixedList() {
        List<SudokuField> l1 = Arrays.asList(new SudokuField[9]);
        //ArrayList<SudokuField> l2 = new ArrayList<>();
        assertEquals(l1.size(), 9);
        l1.add(new SudokuField());
    }

    @Test
    public void equalsHashCodeTest() {
        SudokuBoard instance = new SudokuBoard();
        int goodBoard[][] = {

                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance.set(i, j, goodBoard[i][j]);
            }
        }

        SudokuBoard instance2 = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance2.set(i, j, goodBoard[i][j]);
            }
        }
        Assert.assertTrue(instance.equals(instance2));
        Assert.assertTrue(instance2.equals(instance));
        Assert.assertEquals(instance.hashCode(), instance2.hashCode());
    }

    @Test
    public void toStringTest() {
        SudokuBoard instance = new SudokuBoard();
        int goodBoard[][] = {

                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance.set(i, j, goodBoard[i][j]);
            }
        }
        Assert.assertEquals(instance.toString(), "[[5, 3, 4, 6, 7, 8, 9, 1, 2], [6, 7, 2, 1, 9, 5, 3, 4, 8], [1, 9, 8, 3, 4, 2, 5, 6, 7], [8, 5, 9, 7, 6, 1, 4, 2, 3], [4, 2, 6, 8, 5, 3, 7, 9, 1], [7, 1, 3, 9, 2, 4, 8, 5, 6], [9, 6, 1, 5, 3, 7, 2, 8, 4], [2, 8, 7, 4, 1, 9, 6, 3, 5], [3, 4, 5, 2, 8, 6, 1, 7, 9]]");
    }

    @Test
    public void testCloneable() {
        SudokuBoard instance = new SudokuBoard();
        int goodBoard[][] = {

                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        ArrayList<ArrayList<SudokuField>> gB = new ArrayList<>(9);
        for (int[] ints : goodBoard) {
            ArrayList<SudokuField> list = new ArrayList<>(9);
            for (int i : ints) {
                list.add(new SudokuField(i));
            }
            gB.add(list);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance.set(i, j, goodBoard[i][j]);
            }
        }

        SudokuBoard board2 = null;
        try {
            board2 = instance.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        assertTrue(instance.equals(board2));
    }

}