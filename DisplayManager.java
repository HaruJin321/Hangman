package Hangman;


public class DisplayManager {
    private InputManager inputManager = new InputManager();
    public GuessedLetterManager guessedLetterManager = new GuessedLetterManager();
    private String word;
    private StringBuilder guessedLetters;
    private User aUser;

    public DisplayManager(User u) {
        this.aUser = u;
        word = inputManager.getWord();
        guessedLetters = new StringBuilder(word.replaceAll(".", "_ "));
        guessedLetterManager.clearGuessedLetters();  //clear guessed letters at the start of a new game
    }

    public String getGuessedLetters() {
        return guessedLetters.toString();
    }

    public String getWord() {
        return word;
    }

    public boolean updateGuessedLetters(String userInput) {
        char guessedChar = userInput.charAt(0);

        // to check if the letter has already been guessed
        if (guessedLetterManager.isLetterGuessed(guessedChar)) {
            return false; 
        }

        guessedLetterManager.addGuessedLetter(guessedChar);

        boolean found = false;
        StringBuilder newGuessedLetters = new StringBuilder(guessedLetters);

        for (int i = 0; i < word.length(); i++) {
            if (guessedChar == word.charAt(i)) {
                newGuessedLetters.setCharAt(i * 2, guessedChar);
                found = true;
            }
        }

        if (found) {
            guessedLetters = new StringBuilder(newGuessedLetters);
        }

        return found;
        
    }
    
}
