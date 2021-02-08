package ru.geekbrains.words;

import java.util.*;

public class Words {
    String[] words;
    Set<String> uniqueWords = new HashSet<>();

    public Words(String[] words) {
        this.words = words;
    }

    public void printUniqueWords() {
//        uniqueWords.addAll(Arrays.asList(words.clone()));
        Collections.addAll(uniqueWords, words);

//        Iterator<String> iterator = uniqueWords.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        for (String uniqueWord : uniqueWords) {
            System.out.println(uniqueWord);
        }
    }

    public void printNumberOfRepetitions() {
        Map<String, Integer> repetitions = new HashMap<>();
        for (String word : words) {
            repetitions.put(word, repetitions.containsKey(word) ? repetitions.get(word) + 1 : 1);
        }

        for (Map.Entry<String, Integer> entry : repetitions.entrySet()) {
            System.out.printf("\"%s\" repeat %d times\n", entry.getKey(), entry.getValue());
        }
    }
}
