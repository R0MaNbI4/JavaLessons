package ru.geekbrains.words;

import java.util.*;

public class Words {
    String[] words;
    Set<String> uniqueWords = new HashSet<>();

    public Words(String[] words) {
        this.words = words;
//        uniqueWords.addAll(Arrays.asList(words.clone()));
        Collections.addAll(uniqueWords, words);
    }

    public void printUniqueWords() {
//        Iterator<String> iterator = uniqueWords.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        for (String uniqueWord : uniqueWords) {
            System.out.println(uniqueWord);
        }
    }

    public void printNumberOfRepetitions() {
        int repetitions;
        for (String uniqueWord : uniqueWords) {
            repetitions = 0;
            for (String word : words) {
                if (word.equals(uniqueWord)) {
                    repetitions++;
                }
            }
            System.out.printf("\"%s\" repeat %d times\n", uniqueWord, repetitions);
        }
    }
}
