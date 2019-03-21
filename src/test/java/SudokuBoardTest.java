import junit.framework.TestCase;
import org.junit.*;
import org.junit.rules.ExpectedException;

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
    public void testSolver() {
        SudokuBoard instance = new SudokuBoard();
        instance.fillBoard();
        int[][] board = instance.getBoard();
        assertEquals(true, instance.checkBoard());
    }

    @Test
    public void testDuplicated() {
        SudokuBoard b1 = new SudokuBoard();
        b1.fillBoard();
        int arr1[][] = b1.getBoard();
        b1.fillBoard();
        int arr2[][] = b1.getBoard();
        assertNotEquals(arr1, arr2);
    }

    @Test
    public void testSetGetBoard() {
        int goodBoard[][] = {

                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        SudokuBoard instance = new SudokuBoard();
        instance.setBoard(goodBoard);
        assertEquals(goodBoard, instance.getBoard());
    }

    @Test
    public void testCheckBoard(){
        SudokuBoard instance = new SudokuBoard();
        int goodBoard[][] = {

                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        instance.setBoard(goodBoard);
        assertEquals(true, instance.checkBoard());
        int wrongBoard1[][] = {

                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,2,9,7,6,1,4,2,3}, // 2 twice in one square
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        SudokuBoard instance2 = new SudokuBoard();
        instance2.setBoard(wrongBoard1);
        assertEquals(false, instance2.checkBoard());
        int wrongBoard2[][] = {

                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5}, // 5 twice in one square
                {3,4,5,2,8,6,1,5,9}  //
        };
        SudokuBoard instance3 = new SudokuBoard();
        instance3.setBoard(wrongBoard2);
        assertEquals(false, instance3.checkBoard());
    }

}