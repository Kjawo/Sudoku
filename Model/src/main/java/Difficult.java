public enum Difficult {
    hard("hard"),
    medium("medium"),
    easy("easy");
    private final Level level;
    Difficult(String lvl) {
        if(lvl == "hard") {
            level = new Level(70);
        }
        if(lvl == "medium") {
            level = new Level(50);
        }
        if(lvl == "easy") {
            level = new Level(30);
        }
        else throw new IllegalArgumentException("Wrong argument");
    }
    public Level getLvl(){
        return level;
    }
}
