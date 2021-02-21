package ru.geekbrains;

import java.util.Arrays;

public class Task {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private static long timer = 0;
    private static float[] arr = new float[size];
    private static float[] arr1 = new float[h];
    private static float[] arr2 = new float[h];

    public static void doOneThread() {
        Arrays.fill(arr, 1);
        startTimer();

        calculate(arr,0, size);

        printTimer();
    }

    public static void doTwoThreads() {
        Arrays.fill(arr, 1);
        startTimer();
        divideArray();

        Thread t1 = new Thread(() -> {
           calculate(arr1,0, h);
        });
        Thread t2 = new Thread(() -> {
            calculate(arr2, 0, h);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        connectArrays();
        printTimer();
    }

    private static void calculate(float[] arr, int from, int to) {
        for (int i = from; i < to; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void divideArray() {
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
    }

    private static void connectArrays() {
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
    }

    private static void startTimer() {
        timer = System.currentTimeMillis();
    }

    private static void printTimer() {
        System.out.println(System.currentTimeMillis() - timer);
    }
}
