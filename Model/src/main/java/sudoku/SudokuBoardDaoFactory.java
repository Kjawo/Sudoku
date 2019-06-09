package sudoku;

public class SudokuBoardDaoFactory {
    public static FileSudokuBoardDao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static JdbcSudokuBoardDao getJdbcDao(String boardName) throws Exception {
        return new JdbcSudokuBoardDao(boardName);
    }

}
