package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class SudokuBoardDatabase {
    Connection conn;
    private final String databaseName;
    public static final Logger logger = LoggerFactory.getLogger(SudokuBoardDatabase.class);

    SudokuBoardDatabase(String databaseName) {
        this.databaseName = databaseName;
    }
    public void connectionToDerby() throws SQLException {
        // -------------------------------------------
        // URL format is
        // jdbc:derby:<local directory to save data>
        // -------------------------------------------
        String pathToHome = System.getProperty("user.home");
        pathToHome = pathToHome.replace("\\", "\\\\");
        String pathToDatabase = pathToHome + "\\MyDB\\" + databaseName;
//        System.out.println(pathToDatabase);
        String dbUrl = "jdbc:derby:" + pathToDatabase + ";create=true";
        conn = DriverManager.getConnection(dbUrl);
    }

    public boolean checkIsTableExisting(String tableName) throws Exception{
        DatabaseMetaData dbm = conn.getMetaData();
        tableName = tableName.toUpperCase();

        ResultSet tables = dbm.getTables(null, null, tableName, null);

        logger.info("Checking is table: " + tableName + " existing...");

        if (tables.next()) {
            logger.info("Result: " + tableName + " IS existing");

            return true;
        }
        logger.info("Result: " + tableName + " IS NOT existing");
        return false;
    }

    public void createEmptyTable(String tableName) throws Exception {
        Statement stmt = conn.createStatement();
        tableName = tableName.toUpperCase();

        logger.info("Trying to creating empty table: " + tableName);

        stmt.executeUpdate("Create table " + tableName +
                " (id integer generated always as identity constraint pkId primary key, " +
                "name varchar(30) NOT NULL, " +
                "SudokuBoard BLOB NOT NULL)");

        if(checkIsTableExisting(tableName)){
            logger.info("Result: " + tableName + " created successful");
        }

    }

    public String getAllTablesNames() throws Exception {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);

        logger.info("Trying to get all tables name's");

        String tables = "";
        while (rs.next()) {
            tables += rs.getString(3) + '\n';
        }

        logger.info("Result: tables name's received successful");

    return tables;
    }

    public void dropTable(String tableName) throws Exception {
        Statement stmt = conn.createStatement();
        tableName = tableName.toUpperCase();
        logger.info("Trying to drop table: " + tableName);
        stmt.executeUpdate("Drop Table " + tableName);

        logger.info("Result: " + tableName + " dropped successful");
    }

    public int getSizeOfTable(String tableName) throws Exception {
        int size = 0;
        String query = "SELECT count(*) FROM " + tableName;
        Statement stmt = conn.createStatement();

        logger.info("Trying to get size of table: " + tableName);

        ResultSet result = stmt.executeQuery(query);
        while (result.next()){
            size= result.getInt(1);
        }

        logger.info("Result: " + tableName + " size equals " + size);
        return size;
    }

    public void insertBoard(String tableName, String boardName, SudokuBoard board) throws Exception {
        tableName = tableName.toUpperCase();
        String sql = "INSERT INTO " + tableName + " (name, SudokuBoard) VALUES (?, ?)";

        logger.info("Trying to INSERT INTO " + tableName + " board named " + boardName);

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, boardName);

        Dao boardInFile = SudokuBoardDaoFactory.getFileDao(boardName);

        boardInFile.write(board);

        File temp = new File(boardName);

        FileInputStream fis = new FileInputStream(temp);

        stmt.setBinaryStream(2, fis, (int) temp.length());
        stmt.execute();

        conn.commit();
        fis.close();
        temp.delete();

        logger.info("Result: " + boardName + " inserted INTO table " + tableName + " successful");
    }

    public SudokuBoard getBoard(String tableName, int id) throws Exception{
        tableName = tableName.toUpperCase();
        String sql = "SELECT * FROM " + tableName;
        Statement stmt = conn.createStatement();

        logger.info("Trying to get SudokuBoard of id: " + id + " FROM " + tableName);

        File file = new File("temp");
        FileOutputStream fos = new FileOutputStream(file);

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            if((int) rs.getObject(1) == id ) {
                SudokuBoard board = getSudokuBoard(fos, rs);
                file.delete();
                rs.close();
                stmt.close();
                logger.info("SudokuBoard of id " + id + " received FROM table " + tableName);
                return board;
            }
        }
        throw new SQLException("Object with given ID not found");
    }


    public SudokuBoard getBoard(String tableName, String boardName) throws Exception {
        tableName = tableName.toUpperCase();
        String sql = "SELECT * FROM " + tableName;
        Statement stmt = conn.createStatement();

        logger.info("Trying to get SudokuBoard of name: " + boardName + " FROM " + tableName);

        File file = new File("temp");
        FileOutputStream fos = new FileOutputStream(file);

        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            if(((String) rs.getObject(2)).equals(boardName) ) {
                SudokuBoard board = getSudokuBoard(fos, rs);
                file.delete();
                rs.close();
                stmt.close();
                logger.info("SudokuBoard of name " + boardName + " received FROM table " + tableName);
                return board;
            }
        }
        throw new SQLException("Object with given board name not found");
    }

    private SudokuBoard getSudokuBoard(FileOutputStream fos, ResultSet rs) throws SQLException, IOException {
        InputStream input = rs.getBinaryStream(3);
        byte[] buffer = new byte[1024];
        while (input.read(buffer) > 0) {
            fos.write(buffer);
        }
        FileSudokuBoardDao daoObject = new FileSudokuBoardDao("temp");
        SudokuBoard board = daoObject.read();
        return board;
    }

}
