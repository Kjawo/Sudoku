package sudoku;

import java.sql.SQLException;

public interface Dao<T> {
    T read() throws SQLException;

    void write(T obj) throws Exception;
}
