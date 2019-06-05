package sudoku;

public enum Difficulty {
    easy(2),
    medium(5),
    hard(10);

    int numberOfFieldsToRemove;

    Difficulty(int numberOfFieldsToRemove) {
        this.numberOfFieldsToRemove = numberOfFieldsToRemove;
    }
}
