import java.util.Arrays;

class FieldArray {
    FieldArray(final SudokuField[] fields) {
        sudokuFields = Arrays.copyOf(fields, fields.length);
    }

    boolean verify() {
        for (SudokuField field1 : sudokuFields) {
            for (SudokuField field2 : sudokuFields) {
                if (field1 == field2) {
                    continue;
                }
                if (field1.getFieldValue() == field2.getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    private SudokuField[] sudokuFields;
}