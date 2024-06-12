package Hangman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserManager 
{

    private static UserManager instance; //singleton instance
    private final HashMap<String, User> users; //store users

    private UserManager() 
    {
        this.users = new HashMap<>(); //initialize
        DatabaseManager.setupDatabase(); //call setupdatabase to create table if no exist
        loadUsersDB();// load users from the database
    }
    
    // synchronzied to ensure one instance is only
    public static synchronized UserManager getInstance() //singleton pattern
    {
        if (instance == null) {
            instance = new UserManager();//if instance does not exist create
        }
        return instance;
    }
    
    //load users from the database
    private void loadUsersDB() 
    {
        String query = "SELECT username, score FROM Users"; //table selection
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement load = conn.prepareStatement(query);
             ResultSet rs = load.executeQuery()) 
        {
            while (rs.next()) 
            {
                String username = rs.getString("username"); //get username
                int score = rs.getInt("score");// getscore
                User user = new User(username, score);
                users.put(username, user);// add user to hashmap
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
    }
    
    //if new user, create the new user
    public User checkUser(String username) 
    {
        User user = users.get(username);//get users from hashmap
        if (user == null) { // creating user if does not exist in db
            user = new User (username, 0);
            users.put(username, user);//add the userto hashmap since created
            saveUserDB(user);// call saveuser method
        }
        return user;
    }

    //updates the score of the user each game
    public void updateScore(User user) 
    {
        users.put(user.getUsername(), user);//upddate in hashmap
        updateUserDB(user);// call updateUserDB method
    }

    //save new user to database
    private void saveUserDB(User user) 
    {
        String insert = "INSERT INTO Users (username, score) VALUES (?, ?)"; //query to insert new user
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement save = conn.prepareStatement(insert)) 
        {
            save.setString(1, user.getUsername()); //set the username
            save.setInt(2, user.getScore()); //set socre
            save.executeUpdate(); //execute the update
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    
    //update an exisiting user in the database.
    private void updateUserDB(User user) 
    {
        
        String update = "UPDATE Users SET score = ? WHERE username = ?"; //query to update user
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement updated = conn.prepareStatement(update)) 
        {
            updated.setInt(1, user.getScore()); //set new score
            updated.setString(2, user.getUsername());
            updated.executeUpdate();
            
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    
}
