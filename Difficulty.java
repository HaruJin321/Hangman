package Hangman;


interface DifficultyStrategy {
    int getMaxGuesses();
}

class EasyDifficulty implements DifficultyStrategy {
    @Override
    public int getMaxGuesses() {
        return 10;
    }
}

class MediumDifficulty implements DifficultyStrategy {
    @Override
    public int getMaxGuesses() {
        return 7;
    }
}

class HardDifficulty implements DifficultyStrategy {
    @Override
    public int getMaxGuesses() {
        return 5;
    }
}
