package hangman_prototype;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class GameManager {
    //HashMap to store user data 
    private final HashMap<String, User> users;
    
    private final String fileName;

    // constructor initialize file name and user data
    public GameManager() {
        this.fileName = "./score.txt"; 
        this.users = new HashMap(); // initialize user data HashMap
        this.getUsers(fileName);// load any exisiting users from file
    }


    public static void main(String[] args) {
        // create an instance of GameManager
        GameManager game = new GameManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to a game of Hangman! You have 7 attempts to guess the word.\nPress \"0\" to close game.");
        System.out.println("Insert your username: ");
        // reads username and converts to lowercase
        String s = scanner.nextLine().toLowerCase();
        
        //Check if user wants to close game, can terminate
        if (s.equals("0")) {
            System.out.println("Game Closed");
            System.exit(0);
        }
        // check if the users exists in the user data or create a new        
        User u = game.checkUser(s);
        //a DisplayManager object for user
        DisplayManager holder = new DisplayManager(u);
        //Updates the user score
        game.updateScore(u);
        // Displays the user score
        System.out.println(u.getUsername() + ", your score is now a total of: " + u.getScore());
       
        
    }
    
    //load users from the file        
    public void getUsers(String fn)
    {
        FileInputStream fin;
        
        try {
            fin = new FileInputStream(fn);
            
            Scanner fileScanner = new Scanner(fin);
            //Read user data and add to the users HashMap
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                StringTokenizer st = new StringTokenizer(line);
                
                User u = new User(st.nextToken(), Integer.parseInt(st.nextToken()));
                this.users.put(u.getUsername(), u);
            }
            
            fin.close();
            
        // handles IO/files not found exception
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //method to check if the user exists or create a new user
    public User checkUser(String un) 
    {
        
        User u;
        
        if (this.users.containsKey(un)) {
            u = this.users.get(un);
            System.out.println("Welcome Back! Your current score is: " + u.getScore());
            
        } else { // if the user doesnt exist, create a new
            u = new User(un, 0);
            this.users.put(un, u); // add the new user to HashMap
        }
        
        return u;
        
    }
    
    
    //method to update the user score and save to file
    public void updateScore(User user) 
    {
        
        this.users.put(user.getUsername(), user);
        
        try {
            //opens file for writing
            FileOutputStream fOut = new FileOutputStream(this.fileName);
            PrintWriter pw = new PrintWriter(fOut);
            // Write user data to the file
            for (User u : this.users.values()) {
                pw.println(u.getUsername() + " " + u.getScore());
            }
            
            pw.close();
            
        //Handles file not found exception    
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    }   
    
    
    
}
