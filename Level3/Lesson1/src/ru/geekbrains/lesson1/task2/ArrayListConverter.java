package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListConverter<T> {
    private T[] arr;
    private List<T> arrList = new ArrayList<>();

    public ArrayListConverter(T[] arr) {
        this.arr = arr;
    }

    public void convert() {
        for (T element : arr) {
            arrList.add(element);
        }
    }

    public List<T> getArrList() {
        return arrList;
    }
}
