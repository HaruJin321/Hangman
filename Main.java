package Hangman;

/**
 *
 * @author harry
 */
public class Main {

    public static void main(String[] args) {
        GameManager gameManager = GameManager.getInstance();
        new HangmanFrame(gameManager).setVisible(true);
    }
}
