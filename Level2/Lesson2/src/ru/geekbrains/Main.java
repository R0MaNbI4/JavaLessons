package ru.geekbrains;

public class Main {
    public static void main(String[] args) {
        int colNum = 4;
        //colNum = 3;
        int rowNum = 4;
        String[][] arr = new String[colNum][rowNum];

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                arr[i][j] = String.valueOf(i + j);
            }
        }
        //arr[0][0] = "a";

        ExceptionDemo exceptionDemo = new ExceptionDemo();
        try {
            exceptionDemo.setArray(arr);
        } catch (MySizeArrayException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println(exceptionDemo.sumArray());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Caused by: " + e.getCause());
        }
    }
}
