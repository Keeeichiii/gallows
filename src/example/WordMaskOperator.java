package example;

import java.util.*;

public class WordMaskOperator {

    private String word;
    private char[] mask;
    private final Set<String> usedLetters = new HashSet<>();
    private final Set<String> wordUniqueLetters = new HashSet<>();
    private int numberGuessed = 0;

    public void setWord(String word) {
        this.word = word;
        this.mask = new char[word.length()];
        Arrays.fill(mask, '*');
        Collections.addAll(wordUniqueLetters, word.split(""));
    }

    public void maskOutput() {
        System.out.println(String.valueOf(mask));
    }

    public boolean updateMask(String letter) {
        boolean guessedCorrectly = false;

        for (int i = 0; i < word.length(); i++) {
            if (Character.toString(word.charAt(i)).equalsIgnoreCase(letter)) {
                mask[i] = letter.charAt(0);
                guessedCorrectly = true;
            }
        }

        if (guessedCorrectly) {
            numberGuessed++;
        }

        return guessedCorrectly;
    }

    public boolean containsLetter(String letter) {
        return wordUniqueLetters.contains(letter);
    }

    public void useInputLetters(String letter) {
        usedLetters.add(letter);
    }

    public boolean isLetterAlreadyUsed(String letter) {
        return usedLetters.contains(letter);
    }

    public boolean userWin() {
        return numberGuessed == wordUniqueLetters.size();
    }

    public void clearBuffer() {
        usedLetters.clear();
        wordUniqueLetters.clear();
        numberGuessed = 0;
    }
}
