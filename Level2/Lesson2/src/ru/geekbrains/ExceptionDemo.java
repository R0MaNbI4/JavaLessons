package ru.geekbrains;

import java.util.Arrays;

public class ExceptionDemo {
    private String[][] arr = new String[4][4];

    void setArray(String[][] arr) throws MySizeArrayException {
        checkSizeCondition(arr);
        for (int i = 0; i < arr.length; i++) {
            checkSizeCondition(arr[i]);
        }
        this.arr = arr;
    }

    int sumArray() throws MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch(NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Error converting to number: String \"%s\"", arr[i][j]), e);
                }
            }
        }
        return sum;
    }

    void checkSizeCondition(String[][] arr) {
        if (arr.length != 4) {
            throw new MySizeArrayException("Wrong array size");
        }
    }

    void checkSizeCondition(String[] arr) {
        if (arr.length != 4) {
            throw new MySizeArrayException("Wrong array size");
        }
    }
}
