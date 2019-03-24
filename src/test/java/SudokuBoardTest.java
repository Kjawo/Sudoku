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
    public void testSetGet() {
        SudokuBoard instance = new SudokuBoard();
        instance.set(0,0,3);
        assertEquals(instance.get(0,0),3);
    }
}