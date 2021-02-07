package ru.geekbrains;

import ru.geekbrains.phonebook.Phonebook;
import ru.geekbrains.words.*;

public class Main {
    public static void main(String[] args) {
        wordsDemo();
        System.out.println();
        phonebookDemo();
    }

    static void wordsDemo() {
        Words words = new Words(WordList.getWordList());
        words.printUniqueWords();
        words.printNumberOfRepetitions();
    }

    static void phonebookDemo() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Ivanov", "892912");
        phonebook.add("Petrov", "9812421");
        phonebook.add("Ivanov", "9807124");
        phonebook.get("Ivanov");
        phonebook.get("Petrov");
        phonebook.get("Sidorov");
    }
}
