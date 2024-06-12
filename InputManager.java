package Hangman;



public class InputManager {
    
    private WordManager wordManager = new WordManager();//new wordmanager instance 
    private String word = wordManager.ChosenWord().toLowerCase(); // get chosen word and convert it to lowercase 
    
    
    //validates the users input
    public String validateUserInput(String userInput) {
        userInput = userInput.toLowerCase(); // Converts the user input to lowervase
        
        
        if (userInput.matches("[^a-zA-Z]")) { //Check if the input is not a letter
            return "Error! Must guess a letter!"; // return message to gui
        } 
        else if (userInput.isEmpty()) { // check if input empty
            return "Error! Guess a letter!";
        } else if (userInput.length() > 1) { //Check if the input is more than 1 letter
            return "Error! Must guess a single letter!";
        }
        return null;  // null indicates the input is valid
    }
    
    public String getWord() {
        return word;
    }
}
