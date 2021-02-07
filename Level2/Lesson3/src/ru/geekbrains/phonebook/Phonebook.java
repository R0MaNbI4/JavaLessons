package ru.geekbrains.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    Map<String, ArrayList<String>> phonebook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        ArrayList<String> phoneList = phonebook.containsKey(lastName) ? phonebook.get(lastName) : new ArrayList<>();
        phoneList.add(phoneNumber);
        phonebook.put(lastName, phoneList);
    }

    public void get(String lastName) {
        if (phonebook.containsKey(lastName)) {
            ArrayList<String> phoneList = phonebook.get(lastName);
            for (String phoneNumber : phoneList) {
                System.out.println(lastName + ": " + phoneNumber);
            }
        } else {
            System.out.println("Last name not found");
        }
    }
}
