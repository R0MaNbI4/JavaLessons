package ru.geekbrains.lesson1.task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public List<T> getFruits() {
        return fruits;
    }

    public float getWeight() {
        if (fruits.size() == 0) {
            return 0.0f;
        }
        return fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return getWeight() == box.getWeight();
    }

    public void pour(Box<T> box) {
        box.getFruits().addAll(fruits);
        fruits.clear();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void add(T fruit, int number) {
        for (int i = 0; i < number; i++) {
            fruits.add(fruit);
        }
    }
}