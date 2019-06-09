package sudoku;

import java.sql.SQLException;

public class TableNotExistingException extends SQLException {
    public TableNotExistingException(final String message) {
        super(message);
        SudokuBoard.logger.error("Table does not exist");
    }

}
