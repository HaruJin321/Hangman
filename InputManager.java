package Hangman;



public class InputManager {
    
    private WordManager wordManager = new WordManager();
    private String word = wordManager.ChosenWord().toLowerCase();
    
    public String validateUserInput(String userInput) {
        userInput = userInput.toLowerCase();
        
        //if (userInput.equals("0")) {
        //    return "Game Closed";
        //} 
        if (userInput.matches("[^a-zA-Z]")) {
            return "Error! Must guess a letter!";
        } 
        else if (userInput.isEmpty()) {
            return "Error! Guess a letter!";
        } else if (userInput.length() > 1) {
            return "Error! Must guess a single letter!";
        }
        return null;  // null indicates the input is valid
    }
    
    public String getWord() {
        return word;
    }
}
