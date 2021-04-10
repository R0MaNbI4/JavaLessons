package ru.geekbrains.lesson1.task3;

public abstract class Fruit {
    private float weight;

    Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
