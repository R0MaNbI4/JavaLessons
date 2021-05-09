package ru.geekbrains.tasks;

import java.util.Arrays;

public class TaskOne {
    TaskOne() {}

    public static int[] doTaskOne(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array can't be empty or null");
        }

        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                idx = i;
            }
        }

        if (idx < 0) {
            throw new RuntimeException("Digit four not found in array");
        }

        return Arrays.copyOfRange(arr, idx + 1, arr.length);
    }
}
