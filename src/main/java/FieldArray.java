import java.util.Arrays;

class FieldArray {

    FieldArray(final SudokuField[] fields) {
        sudokuFields = Arrays.copyOf(fields, fields.length);
    }

    boolean verify(boolean considerZeroes) {
        for (SudokuField field1 : sudokuFields) {
            if (!considerZeroes && field1.getFieldValue() == 0) {
                continue;
            }
            for (SudokuField field2 : sudokuFields) {
                if (!considerZeroes && field2.getFieldValue() == 0) {
                    continue;
                }
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