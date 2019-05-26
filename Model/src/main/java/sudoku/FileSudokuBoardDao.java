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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SudokuBoard read() {

        try(ObjectInputStream oi = new ObjectInputStream((new FileInputStream(fileName)))) {
            SudokuBoard sb1 = (SudokuBoard) oi.readObject();
            return sb1;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       throw new RuntimeException("Error reading from file");
       // return null;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

}
