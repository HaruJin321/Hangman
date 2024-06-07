package hangman_prototype;

public class DisplayManager {
    private InputManager inputManager = new InputManager();
    private String word;
    private StringBuilder guessedLetters;
    private int maxGuess = 7;
    private User aUser;
    


    
    public DisplayManager(User u)
    {
        this.aUser = u;
        word = inputManager.getWord();
        guessedLetters = new StringBuilder(word.replaceAll(".", "_ "));
        this.displayGame();
    }
    

    
    //method checks if what the user types is correct,incorrect
    public void displayGame() {
    	while (true) {
            System.out.println("Word " + guessedLetters);
            String userInput = inputManager.getUserInput();

            //checks if user input letter matches the word
            if (updateGuessedLetters(userInput)) {
                System.out.println("Correct! You have " + maxGuess + " guesses left.\n");
            } else {
                maxGuess--;
                System.out.println("Incorrect! You have " + maxGuess + " guesses left.\n");

            }

            //breaks loop if max guesses reach 0
            if (maxGuess == 0) {
                this.aUser.setScore(aUser.getScore() - 10);
                System.out.println("Loser! -10pts and the word was: " + word);
                break;
            }
            
            //if there is no more guesses (underscores) the user will win
            if (!guessedLetters.toString().contains("_")) {
                this.aUser.setScore(aUser.getScore() + 10);
                System.out.println("You win! +10pts and the word was: " + word);
                break;
                
            }
        }
    }

    //method handles checking the user input with what the word is, and replaces
    //the user input with the corresponding underscore.
    private boolean updateGuessedLetters(String userInput) {
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
