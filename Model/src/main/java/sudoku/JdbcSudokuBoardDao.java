package sudoku;

import java.sql.SQLException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    private String boardName;
    private final static String tableName = "sudokuTable";

    JdbcSudokuBoardDao(String boardName) throws Exception {
        this.boardName = boardName;
        SudokuBoardDatabase database = new SudokuBoardDatabase("sudoku");
        database.connectionToDerby();

        if(!database.checkIsTableExisting(tableName)){
            database.createEmptyTable(tableName);
        }
    }

    @Override
    public SudokuBoard read() throws SQLException {
        SudokuBoardDatabase database = new SudokuBoardDatabase("sudoku");
        database.connectionToDerby();
        try {
            return database.getBoard(tableName, boardName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileOperationException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws Exception{
        SudokuBoardDatabase database = new SudokuBoardDatabase("sudoku");
        database.connectionToDerby();
        database.insertBoard(tableName, boardName, obj);
    }

    public static String getTableName() {
        return tableName;
    }
}
