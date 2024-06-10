package Hangman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GameManager 
{

    private static GameManager instance;
    private final HashMap<String, User> users;

    private GameManager() 
    {
        this.users = new HashMap<>();
        DatabaseManager.setupDatabase();
        loadUsersDB();
    }
    
    // synchronzied to ensure one instance is only
    public static synchronized GameManager getInstance() //singleton pattern
    {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    private void loadUsersDB() 
    {
        String query = "SELECT username, score FROM Users"; //table selection
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement load = conn.prepareStatement(query);
             ResultSet rs = load.executeQuery()) 
        {
            while (rs.next()) 
            {
                String username = rs.getString("username");
                int score = rs.getInt("score");
                User user = new User(username, score);
                users.put(username, user);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
    }

    public User checkUser(String username) 
    {
        User user = users.get(username);
        if (user == null) { // creating user if donest exit in db
            user = new User (username, 0);
            users.put(username, user);
            saveUserDB(user);
        }
        return user;
    }

    public void updateScore(User user) 
    {
        users.put(user.getUsername(), user);
        updateUserDB(user);
    }

    private void saveUserDB(User user) 
    {
        String insert = "INSERT INTO Users (username, score) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement save = conn.prepareStatement(insert)) 
        {
            save.setString(1, user.getUsername());
            save.setInt(2, user.getScore());
            save.executeUpdate();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    private void updateUserDB(User user) 
    {
        
        String update = "UPDATE Users SET score = ? WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement updated = conn.prepareStatement(update)) 
        {
            updated.setInt(1, user.getScore());
            updated.setString(2, user.getUsername());
            updated.executeUpdate();
            
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    
}
