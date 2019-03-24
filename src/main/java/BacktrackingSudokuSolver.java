import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver {
    private boolean findUnassignedField(SudokuBoard board, final int[] pos) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidChoice(SudokuBoard board, final int[] pos, int num) {
        //Check if num is already present in given row
        for (int i = 0; i < 9; i++) {
            if (board.get(pos[0],i) == num) {
                return false;
            }

        }

        //Check if num is already present in given col
        for (int i = 0; i < 9; i++) {
            if (board.get(i, pos[1]) == num) {
                return false;
            }
        }

        //Check if num is already present in given square
        int squareRowBegining = pos[0] - pos[0] % 3;
        int squareColBegining = pos[1] - pos[1] % 3;

        for (int i = squareRowBegining; i < squareRowBegining + 3; i++) {
            for (int j = squareColBegining; j < squareColBegining + 3; j++) {
                if (board.get(i,j) == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solve(SudokuBoard board) {
        //pos[0] represents a row
        //pos[1] represents a column
        int[] pos = {0, 0};

        //Find unnasigned field and store it coordinates in pos[]
        //If there are none, board is filled
        if (!findUnassignedField(board, pos)) {
            return true;
        }

        //Create and shuffle list of numbers from 1 to 9
        List<Integer> shuffledNum = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            shuffledNum.add(i);
        }
        Collections.shuffle(shuffledNum);

        for (int i = 0; i < 9; i++) {
            int num = shuffledNum.get(i);
            //If given number (num) does not conflict with others in col, row and square, assign it
            if (isValidChoice(board, pos, num)) {
                board.set(pos[0], pos[1], num);
                if (solve(board)) { // Try solving rest recursively
                    return true;
                } else {
                    board.set(pos[0], pos[1], 0); //else - try next number
                }
            }

        }
        return false;
    }
}
