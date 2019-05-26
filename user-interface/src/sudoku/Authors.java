package sudoku;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][] {
                {"author1", "Konrad Jaworski"},
                {"author2", "Przemyslaw Rudowicz"}
        };
    }
}
