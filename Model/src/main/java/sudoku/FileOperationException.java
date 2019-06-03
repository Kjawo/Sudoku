package sudoku;

public class FileOperationException extends RuntimeException {
    public FileOperationException(Throwable cause) {
        super(cause);
        SudokuBoard.logger.error("File operation error");
    }
}
