package Hangman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:derby:hangmandb_Ebd;create=true";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE Users (username VARCHAR(50) PRIMARY KEY, score INT)";
    private static final String CREATE_TABLE_GUESSED = "CREATE TABLE GuessedLetters (letter CHAR(1) PRIMARY KEY)";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void setupDatabase() 
    {
        
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) 
        {
            stmt.executeUpdate(CREATE_TABLE_USERS);
        } 
        catch (SQLException e) 
        {
            if (e.getSQLState().equals("X0Y32")) {
            } else {
                e.printStackTrace();
            }
        }
        
        
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement())
        {
             
            stmt.executeUpdate(CREATE_TABLE_GUESSED);
        } 
        catch (SQLException e) 
        {
            if (e.getSQLState().equals("X0Y32")) {
            } 
            else 
            {
                e.printStackTrace();
            }
        }
        
        
        
    }
    
    
    
    
    
}
