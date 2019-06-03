package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable{

    private static final long serialVersionUID = 1L;


    private int value;

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
        if(value < 0 || value > 9) throw new BadFieldValueException("Value is incorrect");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField field = (SudokuField) o;

        return new EqualsBuilder()
                .append(value, field.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("Value", value)
                .toString();
    }

    @Override
    public int compareTo(SudokuField sudokuField) {
        return Integer.compare(this.value, sudokuField.getFieldValue());
    }

    @Override
    protected SudokuField clone() throws CloneNotSupportedException {
        return new SudokuField(value);
    }
}
