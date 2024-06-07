/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hangman;

/**
 *
 * @author harry
 */
import javax.swing.*;
import java.awt.*;

public class HangmanFrame extends JFrame {
    private GameManager gameManager;
    private JLabel wordLabel;
    private JTextField inputField;
    private JLabel messageLabel;
    private JLabel scoreLabel;

    public HangmanFrame(GameManager gameManager) {
        this.gameManager = gameManager;
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



        add(panel);

        
    }


}