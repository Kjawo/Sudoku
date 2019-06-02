package sudoku;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public void write(SudokuBoard obj) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(fileName)) ){
            objectOut.writeObject(obj);
            SudokuBoard.logger.info("Wrote to file: ".concat(fileName));

        } catch (FileNotFoundException e) {
            SudokuBoard.logger.error("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            SudokuBoard.logger.error("Error initializing stream");
            e.printStackTrace();
        }
    }

    public SudokuBoard read() {

        try(ObjectInputStream oi = new ObjectInputStream((new FileInputStream(fileName)))) {
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

       throw new RuntimeException("Error reading from file");
       // return null;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

}
