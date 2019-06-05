package sudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Write sudoku board.
     * @param obj Sudoku board
     */
    public void write(SudokuBoard obj) {
        try (ObjectOutputStream objectOut =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOut.writeObject(obj);
            SudokuBoard.logger.info("Wrote to file: ".concat(fileName));

        } catch (FileNotFoundException e) {
            SudokuBoard.logger.error("File not found");
            e.printStackTrace();
            throw new FileOperationException(new FileNotFoundException());
        } catch (IOException e) {
            SudokuBoard.logger.error("Error initializing stream");
            e.printStackTrace();
            throw new FileOperationException(new FileNotFoundException());
        }


    }

    /**
     * Read sudoku board.
     * @return sudoku board
     */
    public SudokuBoard read() {

        try (ObjectInputStream oi = new ObjectInputStream((new FileInputStream(fileName)))) {
            SudokuBoard sb1 = (SudokuBoard) oi.readObject();
            SudokuBoard.logger.info("Read from file: ".concat(fileName));
            return sb1;
        } catch (FileNotFoundException e) {
            SudokuBoard.logger.error("File not found");
            //System.out.println("File not found");
        } catch (IOException e) {
            //System.out.println("Error initializing stream");
            SudokuBoard.logger.error("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            SudokuBoard.logger.error("Class not found");
            e.printStackTrace();
        }

        throw new FileOperationException(new FileNotFoundException());
        // return null;
    }

    //    public void finalize() throws Throwable {
    //        super.finalize();
    //    }

}
