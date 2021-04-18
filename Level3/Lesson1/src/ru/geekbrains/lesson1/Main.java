package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.task1.Swapper;
import ru.geekbrains.lesson1.task2.ArrayListConverter;
import ru.geekbrains.lesson1.task3.*;

public class Main {
    public static void main(String[] args) {
        doTask1();
        System.out.println();
        doTask2();
        doTask3();
    }

    static void doTask1() {
        Swapper<Integer> swapper = new Swapper<>(new Integer[] {1, 2, 3, 4, 5});
        swapper.swap(0,2);
        swapper.print();
    }

    static void doTask2() {
        ArrayListConverter<Integer> converter = new ArrayListConverter<>(new Integer[] {1, 2, 3, 4, 5});
        converter.convert();
        System.out.println(converter.getArrList());
    }

    static void doTask3() {
        Box<Apple> Apples = new Box<>();
        Box<Orange> Oranges = new Box<>();
        Apples.add(new Apple(), 5);
        Oranges.add(new Orange(), 4);

        System.out.println("Apple box weight: " + Apples.getWeight());
        System.out.println("Orange box weight: " + Oranges.getWeight());

        if (Apples.compare(Oranges)) {
            System.out.println("Boxes of the same weight");
        } else {
            System.out.println("Boxes are not the same weight");
        }

        Box<Apple> Apples2 = new Box<>();
        Apples2.add(new Apple(), 7);
        System.out.printf("In the first box %d apples, in the second %d apples\n",
                Apples.getFruits().size(), Apples2.getFruits().size());
        Apples2.pour(Apples);
        System.out.printf("In the first box %d apples, in the second %d apples\n",
                Apples.getFruits().size(), Apples2.getFruits().size());
    }
}
