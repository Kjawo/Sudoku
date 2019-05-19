import java.util.ArrayList;
import java.util.List;

class SudokuBox extends FieldArray implements Cloneable {
    SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected SudokuBox clone() throws CloneNotSupportedException {
        return new SudokuBox(getSudokuFields());
    }
}
