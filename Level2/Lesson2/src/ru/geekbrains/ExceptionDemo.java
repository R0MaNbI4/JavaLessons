package ru.geekbrains;

import java.util.Arrays;

public class ExceptionDemo {
    private String[][] arr = new String[4][4];

    void setArray(String[][] arr) throws MySizeArrayException {
        if (arr.length == 4) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].length != 4) {
                    throw new MySizeArrayException("Wrong array size");
                }
            }
        } else {
            throw new MySizeArrayException("Wrong array size");
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
}
