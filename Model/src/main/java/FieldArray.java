import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

class FieldArray {

    private List<SudokuField> sudokuFields;

    FieldArray(final List<SudokuField> fields) {
        sudokuFields = fields;
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
                if (field1.equals(field2)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldArray that = (FieldArray) o;

        return new EqualsBuilder()
                .append(sudokuFields, that.sudokuFields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuFields)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("sudokuFields", sudokuFields)
                .toString();
    }
}