package Hangman;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class HangmanFrame extends JFrame {
    private GameManager gameManager;
    private DisplayManager displayManager;
    private InputManager inputManager;
    private User currentUser;
    private JLabel wordLabel;
    private JTextField inputField;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private DifficultyStrategy difficultyStrategy;
    private int maxGuess;


    public HangmanFrame(GameManager gameManager) 
    {
        this.gameManager = gameManager;
        this.inputManager = new InputManager();
        initUI();
    }

    private void initUI() {
        
        setTitle("Hangman Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        wordLabel = new JLabel("_ _ _ _ _ _ _ _ _", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        panel.add(wordLabel, BorderLayout.CENTER);

        inputField = new JTextField(1);
        panel.add(inputField, BorderLayout.SOUTH);

        messageLabel = new JLabel("Enter a letter:", SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.NORTH);

        scoreLabel = new JLabel("Score: 0", SwingConstants.RIGHT);
        panel.add(scoreLabel, BorderLayout.EAST);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        add(panel);
        askUsername();


    }

    private void startNewGame() 
    {

        displayManager = new DisplayManager(currentUser);
        wordLabel.setText(displayManager.getGuessedLetters());
        maxGuess = difficultyStrategy.getMaxGuesses();
        messageLabel.setText("Enter a letter:");
        updateScoreLabel();

    }

    
    
    private void askUsername() {
        String username = null;
        boolean validUsername = false;

        while (!validUsername) {
            username = JOptionPane.showInputDialog(this, "Enter your username:");

            if (username == null) { // User canceled the input dialog
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else if (!username.trim().isEmpty()) {
                validUsername = true;
            }
        }

        if (validUsername) {
            currentUser = gameManager.checkUser(username.toLowerCase());
            displayManager = new DisplayManager(currentUser);
            updateScoreLabel();
            chooseDifficulty();
        }
    }
    
    
    
    private void chooseDifficulty() {
        Object[] options = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(this, "Choose Difficulty Level", "Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                difficultyStrategy = new EasyDifficulty();
                break;
            case 1:
                difficultyStrategy = new MediumDifficulty();
                break;
            case 2:
                difficultyStrategy = new HardDifficulty();
                break;
            default:
                difficultyStrategy = new MediumDifficulty();
        }
        

        startNewGame();
    }
    
    
    

    private void handleGuess() {
        
        String guess = inputField.getText().toLowerCase();
        inputField.setText("");

        String validationMessage = inputManager.validateUserInput(guess);
        if (validationMessage != null) 
        {
            messageLabel.setText(validationMessage);
            return;
        }

        if (displayManager.updateGuessedLetters(guess)) 
        {
            messageLabel.setText("Correct! You have " + maxGuess + " guesses left.");
        } else 
        {
            maxGuess--;
            messageLabel.setText("Incorrect! You have " + maxGuess + " guesses left.");
        }

        
        wordLabel.setText(displayManager.getGuessedLetters());

        
        if (maxGuess == 0) 
        {
            currentUser.setScore(currentUser.getScore() - 10);
            messageLabel.setText("Loser! -10pts. The word was: " + displayManager.getWord());
            endGame();
        } 
        else if (!displayManager.getGuessedLetters().contains("_")) 
        {
            currentUser.setScore(currentUser.getScore() + 10);
            messageLabel.setText("You win! +10pts. The word was: " + displayManager.getWord());
            endGame();
        }
        
        updateScoreLabel();

    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + currentUser.getScore());
    }

    private void endGame() 
    {
        
        gameManager.updateScore(currentUser);
        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) 
        {
            chooseDifficulty();
        } else {
            System.exit(0);
        }
    }
}
