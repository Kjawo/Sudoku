package sudoku;

import java.util.List;

class SudokuColumn extends FieldArray implements Cloneable {
    SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected SudokuColumn clone() throws CloneNotSupportedException {
        return new SudokuColumn(getSudokuFields());
    }
}