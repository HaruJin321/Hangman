package Hangman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuessedLetterManager 
{

    
    public boolean isLetterGuessed(char letter) 
    {
        String query = "SELECT COUNT(*) FROM GuessedLetters WHERE letter = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement guess = conn.prepareStatement(query)) 
        {
            guess.setString(1, String.valueOf(letter));
            ResultSet rs = guess.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) 
            {
                return true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public void addGuessedLetter(char letter) 
    {
        String insert = "INSERT INTO GuessedLetters (letter) VALUES (?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement add = conn.prepareStatement(insert)) 
        {
            add.setString(1, String.valueOf(letter));
            add.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        
    }
    
    
    
    
    public void clearGuessedLetters() 
    {
        
        String deleteAll = "DELETE FROM GuessedLetters";
        
        try (Connection conn = DatabaseManager.getConnection();
                
             PreparedStatement clear = conn.prepareStatement(deleteAll)) 
            
        {
            clear.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    
}
