package Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private int incorrectGuesses;
    private JLabel hangmanImageLabel;
    private ImageManager imageManager;
    
    public HangmanFrame(GameManager gameManager) {
        this.gameManager = gameManager;
        this.inputManager = new InputManager();
        this.imageManager = new ImageManager();
        initUI();
    }

    private void initUI() {
        setTitle("Hangman Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

	try {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
            e.printStackTrace();
	}
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

	// restart button
	JButton restartButton = new JButton("Restart");
	restartButton.setFont(new Font("Arial", Font.BOLD, 14)); 
	restartButton.addActionListener(e -> confirmRestart());
	restartButton.setBounds(25, 25, 100, 30);
	add(restartButton);
	
	//exit button
	JButton exitButton = new JButton("Exit");
	exitButton.setFont(new Font("Arial", Font.BOLD, 14)); 
	exitButton.addActionListener(e -> confirmExit());
	exitButton.setBounds(660, 25, 100, 30);
	add(exitButton);
		
        
        wordLabel = new JLabel("_ _ _ _ _ _ _ _ _");
        wordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        wordLabel.setBounds(300, 280, 200, 400);  // x from left edge, y from top edge, width of pixel, height of pixel. 
        add(wordLabel);                             

	inputField = new JTextField(10); // Increase the width of the text field
	inputField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase the font size of the text field
	inputField.setHorizontalAlignment(JTextField.CENTER); // Center the text in the text field
	panel.add(inputField, BorderLayout.SOUTH);

	messageLabel = new JLabel("Enter a letter:", SwingConstants.CENTER);
	messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	panel.add(messageLabel, BorderLayout.NORTH);

	// use this type of code to adjust it manually
	scoreLabel = new JLabel("Score: 0");
	scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
	scoreLabel.setBounds(300, 320, 200, 400); // x from left edge, y from top edge, width of pixel, height of pixel.
	add(scoreLabel);

        
        hangmanImageLabel = new JLabel();
        hangmanImageLabel.setBounds(235, 100, 330, 370); // x from left edge, y from top edge, width of pixel, height of pixel. 
        add(hangmanImageLabel);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        updateHangmanImage(0);
        add(panel);
        askUsername();
    }

    private void startNewGame() {
        displayManager = new DisplayManager(currentUser);
        wordLabel.setText(displayManager.getGuessedLetters());
        maxGuess = difficultyStrategy.getMaxGuesses();
        incorrectGuesses = 0;
        messageLabel.setText("Enter a letter:");
        updateScoreLabel();
        updateHangmanImage(incorrectGuesses);
    }


    
    //confirm restart button
    private void confirmRestart() {
	int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to restart the game?", 
                "Confirm Restart", JOptionPane.YES_NO_OPTION);
	if (option == JOptionPane.YES_OPTION) {
	    askUsername();
	    }
	}
    
    //exit button
	private void confirmExit() {
		int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to Exit the game?", "Confirm Restart",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			dispose();
		}
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
        if (validationMessage != null) {
            messageLabel.setText(validationMessage);
            return;
        }

        if (displayManager.updateGuessedLetters(guess)) {
            messageLabel.setText("Correct! You have " + maxGuess + " guesses left.");
        } else {
            incorrectGuesses++;
            maxGuess--;
            messageLabel.setText("Incorrect! You have " + maxGuess + " guesses left.");
            updateHangmanImage(incorrectGuesses);
        }

        wordLabel.setText(displayManager.getGuessedLetters());

        if (maxGuess == 0) {
            currentUser.setScore(currentUser.getScore() - 10);
            messageLabel.setText("Loser! -10pts. The word was: " + displayManager.getWord());
            endGame();
        } else if (!displayManager.getGuessedLetters().contains("_")) {
            currentUser.setScore(currentUser.getScore() + 10);
            messageLabel.setText("You win! +10pts. The word was: " + displayManager.getWord());
            endGame();
        }

        updateScoreLabel();
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + currentUser.getScore());
    }
    
    private void updateHangmanImage(int incorrectGuesses) {
        ImageIcon icon = imageManager.getHangmanImage(incorrectGuesses, difficultyStrategy);
        hangmanImageLabel.setIcon(icon);
    }

    private void endGame() {
        gameManager.updateScore(currentUser);
        int option = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            chooseDifficulty();
        } else {
            System.exit(0);
        }
    }
}
