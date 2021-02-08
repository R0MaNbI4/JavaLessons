package ru.geekbrains.phonebook;

import java.util.*;

public class Phonebook {
    Map<String, HashSet<String>> phonebook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        HashSet<String> phoneList = phonebook.containsKey(lastName) ? phonebook.get(lastName) : new HashSet<>();
        phoneList.add(phoneNumber);
        phonebook.put(lastName, phoneList);
    }

    public Set<String> get(String lastName) {
        if (phonebook.containsKey(lastName)) {
            return phonebook.get(lastName);
        } else {
            return Collections.emptySet();
        }
    }
}
