package ru.geekbrains.tasks;

public class TaskTwo {
    TaskTwo() {}

    public static boolean doTaskTwo(int[] arr) {
        boolean result = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1 || arr[i] == 4) {
                result = true;
                break;
            }
        }

        return result;
    }
}
