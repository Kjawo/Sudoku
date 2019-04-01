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

    private int value;
}
