import java.util.ArrayList;
import java.util.List;

class SudokuRow extends FieldArray implements Cloneable {
    SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new SudokuRow(getSudokuFields());
    }
}