public class SudokuBoardDaoFactory {
    public static Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
