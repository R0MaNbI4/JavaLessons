package ru.geekbrains.lesson1.task1;

public class Swapper<T> {
    private T[] arr;

    public Swapper(T[] arr) {
        this.arr = arr;
    }

    public void swap(int idx1, int idx2) {
        T temp;
        temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public void print() {
        for (T element : arr) {
            System.out.print(element + ", ");
        }
    }
}
