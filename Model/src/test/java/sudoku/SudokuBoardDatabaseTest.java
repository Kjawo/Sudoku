package sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuBoardDatabaseTest {

    SudokuBoardDatabase database = new SudokuBoardDatabase("database_test");

    @Test
    public void checkIsTableExisting() throws Exception{
        database.connectionToDerby();
        if(!database.checkIsTableExisting("table1")) {
            database.createEmptyTable("table1");
        }

        assertTrue(database.checkIsTableExisting("table1"));
    }

    @Test
    public void dropTable() throws Exception{
        database.connectionToDerby();
        if(!database.checkIsTableExisting("table1")) {
            database.createEmptyTable("table1");
        }

        assertTrue(database.checkIsTableExisting("table1"));

        database.dropTable("table1");

        assertFalse(database.checkIsTableExisting("table1"));
    }

    @Test
    public void insertBoard() throws Exception{
        SudokuBoard sudoku = new SudokuBoard();

        int solvedBoard[][] = {

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
                sudoku.set(i, j, solvedBoard[i][j]);
            }
        }
        database.connectionToDerby();


        if(!database.checkIsTableExisting("table1")) {
            database.createEmptyTable("table1");
        }
        else{
            database.dropTable("table1");
            database.createEmptyTable("table1");
        }

        assertTrue(database.checkIsTableExisting("table1"));

        database.insertBoard("table1", "sudoku1", sudoku);

        assertEquals(database.getSizeOfTable("table1"), 1);

    }

    @Test
    public void getBoard() throws Exception{
        SudokuBoard sudoku = new SudokuBoard();

        int solvedBoard[][] = {

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
                sudoku.set(i, j, solvedBoard[i][j]);
            }
        }
        database.connectionToDerby();


        if(!database.checkIsTableExisting("table1")) {
            database.createEmptyTable("table1");
        }
        else{
            database.dropTable("table1");
            database.createEmptyTable("table1");
        }

        assertTrue(database.checkIsTableExisting("table1"));

        database.insertBoard("table1", "sudoku1", sudoku);

        assertEquals(database.getSizeOfTable("table1"), 1);

        SudokuBoard gettedBoard = database.getBoard("table1", "sudoku1");

        assertTrue(sudoku.equals(gettedBoard));
    }
}