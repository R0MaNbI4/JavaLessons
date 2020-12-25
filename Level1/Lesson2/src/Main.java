public class Main {
    public static void main(String[] args) {
        doTask1();
        doTask2();
        doTask3();
        doTask4(5);
        doTask5(10);
        System.out.println(doTask6() + "\n");
        doTask7(10,0);
        doTask7(10, 3);
        doTask7(10, -3);
    }

    /**
     * Задать целочисленный массив, состоящий из элементов 0 и 1.
     * Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    static void doTask1() {
        int[] arr = { 1, 1, 0, 0, 1, 1, 0, 0 };

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else if (arr[i] == 1) {
                arr[i] = 0;
            }
        }
        printArray(arr);
    }

    /**
     * Задать пустой целочисленный массив размером 8.
     * С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
     */
    static void doTask2() {
        int[] arr = new int[8];

        for (int i = 0; i < arr.length; i++ ) {
            arr[i] = i * 3;
        }

        printArray(arr);
    }

    /**
     * Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
     * пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    static void doTask3() {
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
        }

        printArray(arr);
    }

    /**
     * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое)
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
     */
    static void doTask4(int size) {
        int[][] arr = new int[size][size];

        for (int i = 0; i < arr.length; i++){
            arr[i][i] = 1;
            arr[i][size - 1 - i] = 1;
        }

        printArray(arr);
    }

    /**
     * Задать одномерный массив и найти в нем минимальный и максимальный элементы;
     */
    static void doTask5(int size) {
        int[] arr = new int[size];
        fillArray(size, arr);

        int min, max;
        min = max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        System.out.println("min: " + min + "\n" + "max: " + max);
        printArray(arr);
    }

    /**
     * Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true, если в массиве есть место,
     * в котором сумма левой и правой части массива равны.
     * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
     * checkBalance([1, 1, 1, || 2, 1]) → true,
     * граница показана символами ||, эти символы в массив не входят.
     */
    static boolean doTask6() {
        int[] arr = { 2, 2, 2, 1, 2, 2, 10, 1 };
        int leftSum = 0;
        int rightSum = 0;

        for (int i = 1; i < arr.length; i++) {
            for (int left = 0; left < i; left++) {
                leftSum += arr[left];
            }
            for (int right = i; right < arr.length; right++) {
                rightSum += arr[right];
            }

            if (leftSum == rightSum) {
                return true;
            }

            leftSum = 0;
            rightSum = 0;
        }

        return false;
    }

    /**
     * Написать метод, которому на вход подается одномерный массив
     * и число n (может быть положительным, или отрицательным),
     * при этом метод должен сместить все элементы массива на n позиций.
     * Элементы смещаются циклично.
     * Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     * Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
     * [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
     * При каком n в какую сторону сдвиг можете выбирать сами.
     */
    static void doTask7(int size, int n) {
        int[] arr = new int[size];
        fillArray(size, arr);

        if (n < 0) {
            for (int i = 0; i > n; i--) {
                int temp = arr[0];
                for (int j = 0; j < size - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length - 1] = temp;
            }
        } else if (n > 0) {
            for (int i = 0; i < n; i++) {
                int temp = arr[arr.length - 1];
                for (int j = arr.length - 1; j > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = temp;
            }
        }
        printArray(arr);
    }

    static void fillArray(int size, int[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
