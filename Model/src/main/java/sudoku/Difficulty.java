package sudoku;

public enum Difficulty {
    hard("hard"),
    medium("medium"),
    easy("easy");
    public final Level level;
    int number;
    Difficulty(String lvl) {
        if(lvl == "hard") {
            number = 70;
        }
        if(lvl == "medium") {
            number = 50;
        }
        if(lvl == "easy") {
            number = 30;
        }
        else throw new IllegalArgumentException("Wrong argument");
        level = new Level(number);
    }
    public Level getLvl(){
        return level;
    }
}
