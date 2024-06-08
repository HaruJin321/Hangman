package Hangman;


public class DisplayManager {
    private InputManager inputManager = new InputManager();
    private String word;
    private StringBuilder guessedLetters;
    private User aUser;

    public DisplayManager(User u) {
        this.aUser = u;
        word = inputManager.getWord();
        guessedLetters = new StringBuilder(word.replaceAll(".", "_ "));
    }

    public String getGuessedLetters() {
        return guessedLetters.toString();
    }

    public String getWord() {
        return word;
    }

    public boolean updateGuessedLetters(String userInput) {
        boolean found = false;
        StringBuilder newGuessedLetters = new StringBuilder(guessedLetters);

        for (int i = 0; i < word.length(); i++) {
            if (userInput.charAt(0) == word.charAt(i)) {
                newGuessedLetters.setCharAt(i * 2, userInput.charAt(0));
                found = true;
            }
        }

        if (found) {
            guessedLetters = new StringBuilder(newGuessedLetters);
        }

        return found;
    }
}
