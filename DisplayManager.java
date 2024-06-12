package Hangman;


public class DisplayManager {
    private InputManager inputManager = new InputManager(); //inputmanager instance
    public GuessedLetterManager guessedLetterManager = new GuessedLetterManager();//new instance
    private String word; 
    private StringBuilder guessedLetters;
    private User aUser;

    
    //Constructor for DisplayManager
    public DisplayManager(User u) {
        this.aUser = u;
        word = inputManager.getWord(); // get word to be guessed
        guessedLetters = new StringBuilder(word.replaceAll(".", "_ ")); //initialized guessed letters with underscore
        guessedLetterManager.clearGuessedLetters();  //clear guessed letters table at the start of a new game
    }

    //return current state of guessed letters
    public String getGuessedLetters() {
        return guessedLetters.toString();
    }

    public String getWord() {
        return word;
    }

    
    //update guessed letters based on user input
    public boolean updateGuessedLetters(String userInput) {
        char guessedChar = userInput.charAt(0);

        // to check if the letter has already been guessed
        if (guessedLetterManager.isLetterGuessed(guessedChar)) {
            return false; 
        }

        guessedLetterManager.addGuessedLetter(guessedChar); //insert the guessed letter to table

        boolean found = false;
        StringBuilder newGuessedLetters = new StringBuilder(guessedLetters);

        
        //iterate through the word and update guessed letters if letter is found 
        for (int i = 0; i < word.length(); i++) {
            if (guessedChar == word.charAt(i)) {
                newGuessedLetters.setCharAt(i * 2, guessedChar);//update the guessed letter
                found = true;
            }
        }
        
        // update guessed letters if the character was found
        if (found) {
            guessedLetters = new StringBuilder(newGuessedLetters);
        }

        return found;
        
    }
    
}
