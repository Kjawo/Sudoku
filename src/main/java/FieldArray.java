import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class FieldArray {

    FieldArray(final ArrayList<SudokuField> fields) {
//        sudokuFields = ArrayList.copyOf(fields, fields.size());
        sudokuFields = new ArrayList<SudokuField>(fields);
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

    private ArrayList<SudokuField> sudokuFields;
}