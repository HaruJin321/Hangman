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
public class InputManagerTest {
    
    private InputManager inputtest;
    
    public InputManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        inputtest = new InputManager();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateUserInput method, of class InputManager.
     */
    @Test
    public void testValidateUserInput() {
        System.out.println("validateUserInput (Emtpy)");
        String userInput = "";
        String expResult = "Error! Guess a letter!";
        String result = inputtest.validateUserInput(userInput);
        assertEquals(expResult, result);

    }

    /**
     * Test of getWord method, of class InputManager.
     */
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        String word = inputtest.getWord();
        assertNotNull(word);

    }
    
    
    /**
     * Test of validateUserInput method, of class InputManager.
     */
    @Test
    public void testValidateUserInput2() {
        System.out.println("validateUserInput (Valid)");
        String userInput = "a";
        String result = inputtest.validateUserInput(userInput);
        assertNull(result);

    }
    
    
    
    
    /**
     * Test of validateUserInput method, of class InputManager.
     */
    @Test
    public void testValidateUserInput3() {
        System.out.println("validateUserInput (Not valid input)");
        String userInput = "1";
        String expResult = "Error! Must guess a letter!";
        String result = inputtest.validateUserInput(userInput);
        assertEquals(expResult, result);

    }
    
    
        /**
     * Test of validateUserInput method, of class InputManager.
     */
    @Test
    public void testValidateUserInput4() {
        System.out.println("validateUserInput (multiple invalid input)");
        String userInput = "po";
        String expResult = "Error! Must guess a single letter!";
        String result = inputtest.validateUserInput(userInput);
        assertEquals(expResult, result);

    }
    
}
