package ru.geekbrains;

import ru.geekbrains.phonebook.Phonebook;
import ru.geekbrains.words.*;

public class Main {
    public static void main(String[] args) {
        doWordsDemo();
        System.out.println();
        doPhonebookDemo();
    }

    static void doWordsDemo() {
        Words words = new Words(WordList.getWordList());
        words.printUniqueWords();
        words.printNumberOfRepetitions();
    }

    static void doPhonebookDemo() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Ivanov", "892912");
        phonebook.add("Petrov", "9812421");
        phonebook.add("Petrov", "9812421");
        phonebook.add("Ivanov", "9807124");
        System.out.println("Ivanov: " + phonebook.get("Ivanov"));
        System.out.println("Petrov: " + phonebook.get("Petrov"));
        System.out.println("Sidorov: " + phonebook.get("Sidorov"));
    }
}
