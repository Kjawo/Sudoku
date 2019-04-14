import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField {

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
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(value).toString();
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
}
