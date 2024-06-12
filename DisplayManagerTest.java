/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Hangman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harry
 */
public class DisplayManagerTest {
    private DisplayManager displaytest;
    private User user;
    
    public DisplayManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        user = new User("testUser", 0);
        displaytest = new DisplayManager(user);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGuessedLetters method, of class DisplayManager.
     */
    @Test
    public void testGetGuessedLetters() {
        System.out.println("getGuessedLetters");
        String result = displaytest.getGuessedLetters();
        assertNotNull(result);

    }


    
    /**
     * Test of updateGuessedLetters method, of class DisplayManager.
     */
    @Test
    public void testUpdateGuessedLetters() {
        System.out.println("updateGuessedLetters (correct guess)");
        String userInput = String.valueOf(displaytest.getWord().charAt(0));
        boolean result = displaytest.updateGuessedLetters(userInput);
        assertTrue(result);

    }
    
    /**
     * Test of updateGuessedLetters method, of class DisplayManager.
     */
    @Test
    public void testUpdateGuessedLettersIncorrectGuess() {
        System.out.println("updateGuessedLetters (incorrect guess)");
        String userInput = "z";
        boolean result = displaytest.updateGuessedLetters(userInput);
        assertFalse(result);
    }
    
    
}
