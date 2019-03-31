import java.util.Objects;

public class SudokuField {
    public SudokuField() {
        this(0);
    }

    public SudokuField(final int value) {
        setFieldValue(value);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(final int value) {
        this.value = value;
    }

//    @Override
//    public boolean equals(final Object object) {
//        if (object == this) {
//            return true;
//        }
//        if (object == null || object.getClass() != getClass()) {
//            return false;
//        }
//        SudokuField field = (SudokuField) object;
//        return field.value == this.value;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(value);
//    }

    private int value;
}
