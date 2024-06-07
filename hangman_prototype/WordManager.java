package hangman_prototype;


import java.util.ArrayList;
import java.util.Random;

public class WordManager {
    

    private final ArrayList<String> wordList = new ArrayList<>();

    public WordManager() {
	wordList.add("Minecraft");
	wordList.add("Fortnite");
	wordList.add("netbeans");
	wordList.add("programming");
	wordList.add("coding");
	wordList.add("student");
	wordList.add("university");
	wordList.add("expectation");
	wordList.add("family");
	wordList.add("elephant");
        wordList.add("temperature");
        wordList.add("advantage");
        wordList.add("perfect");
        wordList.add("australia");
        }
    

    //method to get a random word from the list
    public String ChosenWord() {
        Random random = new Random();
        int chosenWordIndex = random.nextInt(wordList.size());
        return wordList.get(chosenWordIndex);
    }
    

}
