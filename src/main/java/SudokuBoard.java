import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {
    private int[][] board;

    public void setBoard(int[][] boardd){
        this.board = boardd;
    }

    public int[][] getBoard() {
        return board;
    }

    private boolean findUnassignedField(int[] pos) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidChoice(int[] pos, int num) {
        //Check if num is already present in given row
        for (int i = 0; i < 9; i++) {
            if (board[pos[0]][i] == num)
                return false;
        }

        //Check if num is already present in given col
        for (int i = 0; i < 9; i++) {
            if (board[i][pos[1]] == num)
                return false;
        }

        //Check if num is already present in given square
        int squareRowBegining = pos[0] - pos[0] % 3;
        int squareColBegining = pos[1] - pos[1] % 3;

        for (int i = squareRowBegining; i < squareRowBegining + 3; i++) {
            for (int j = squareColBegining; j < squareColBegining + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean solve() {
        //pos[0] represents a row
        //pos[1] represents a column
        int[] pos = {0, 0};

        //Find unnasigned field and store it coordinates in pos[]
        //If there are none, board is filled
        if (!findUnassignedField(pos))
            return true;

        //Create and shuffle list of numbers from 1 to 9
        List<Integer> shuffledNum = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            shuffledNum.add(i);
        }
        Collections.shuffle(shuffledNum);

        for (int i = 0; i < 9; i++) {
            int num = shuffledNum.get(i);
            //If given number (num) does not conflict with others in col, row and square, assign it
            if (isValidChoice(pos, num)) {
                board[pos[0]][pos[1]] = num;
                if (solve()) { // Try solving rest recursively
                    return true;
                } else {
                    board[pos[0]][pos[1]] = 0; //else - try next number
                }
            }

        }
        return false;
    }

    public void fillBoard() {
        board = new int[9][9];
        solve();

    }

    public boolean checkBoard(){
        for(int i = 0; i < 9; i++){ //first check in rows and columns
            List<Integer> column = new ArrayList<>();
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < 9; j++){
                if(row.contains(board[i][j])) return false;
                row.add(board[i][j]);
                if(column.contains(board[j][i])) return false;
                column.add(board[j][i]);
            }
        }
        List<Integer> matrixInArray = new ArrayList<>();
        for(int i = 0; i < 9; i++){ //transform matrix board into array
            for(int j = 0; j < 9; j++){
                matrixInArray.add(board[i][j]);
            }
        }
        int last = 0;
        for(int i = 0; i < 9; i++){ //now check in each square
            List<Integer> square = new ArrayList<>();
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    int position = k + j * 9 + i * 3 + last; // position of numbers in square
                    if(square.contains(matrixInArray.get(position))) return false; //if number is duplicated in square
                    square.add(matrixInArray.get(position));
                }
            }
            if(i % 3 == 2) last += 18; // number for "jump" to square below "row" of square
        }
        return true;
    }


}