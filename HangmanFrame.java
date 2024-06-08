package Hangman;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanFrame extends JFrame {
    private GameManager gameManager;
    private DisplayManager displayManager;
    private InputManager inputManager;
    private JLabel wordLabel;
    private JTextField inputField;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private int maxGuess = 7;

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

        startNewGame();
    }

    private void startNewGame() 
    {

        displayManager = new DisplayManager();
        wordLabel.setText(displayManager.getGuessedLetters());
        maxGuess = 7;
        scoreLabel.setText("Score: 0");
        
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
            messageLabel.setText("Loser! -10pts. The word was: " + displayManager.getWord());
            endGame();
        } 
        else if (!displayManager.getGuessedLetters().contains("_")) 
        {
            messageLabel.setText("You win! +10pts. The word was: " + displayManager.getWord());
            endGame();
        }


    }



    private void endGame() 
    {
        
        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) 
        {
            startNewGame();
        } else {
            System.exit(0);
        }
    }
}
