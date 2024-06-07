package hangman_prototype;

import java.util.Scanner;


public class InputManager {
    
    // instance of WordManager to access the chosen word
    private WordManager wordManager = new WordManager();
    
    // to receive input from the user
    private Scanner scanner = new Scanner(System.in);
    private String word = wordManager.ChosenWord().toLowerCase();
    
    //Method to get the user input for guessing a letter
    public String getUserInput() {
        
        System.out.println("Input a letter: ");
        // reads user input and converts to lowercase
        String userInput = scanner.nextLine().toLowerCase();
        
        // Check if user wants to close game
        if (userInput.equals("0")) {
            System.out.println("Game Closed");
            //teriminate game
            System.exit(0);
        } 
        //this checks if input is not a letter
        else if (userInput.matches("[^a-zA-Z]")) {
            System.out.println("Error! Must guess a letter!");
            //will recursively call getUserInput() till valid input
            return getUserInput();
        } 
        //if user input is empty, display error message and call getUserInput() to get valid input
        else if (userInput.isEmpty()) {
            System.out.println("Error! Guess a letter!");
            return getUserInput();
        } else if (userInput.length() > 1) {
            System.out.println("Error! Must guess a single letter!");
            return getUserInput();
        }

        
        return userInput;
        
    }
    
    //Method to get the chosen word
    public String getWord() {
	return word;
    }
    
}