package sudoku;

public class Level {
    private final int numberOfFieldsToRemove;
    Level(int number) {
        numberOfFieldsToRemove = number;
    }

    public int getNumberOfFieldsToRemove() {
        return numberOfFieldsToRemove;
    }
}
