package sudoku;

public class EmptyBoardException extends Exception {
    public EmptyBoardException(String message) {
        super(message);
    }
}