import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start(3);
    }

    static void start(int size) {
        char[][] field = getField(size, '-');
        doDraw(field);

        do {
            handlePlayerMove(field, size);
            doDraw(field);
            if (isFinal(field, playerSign())) {
                break;
            }

            System.out.println();

            handleAIMove(field, size);
            doDraw(field);
            if (isFinal(field, AIsign())) {
                break;
            }

        } while(true);
    }

    static char playerSign() {
        return 'X';
    }

    static char AIsign() {
        return 'O';
    }

    static char emptySign() {
        return '-';
    }

    static char[][] getField(int size, char emptySign) {
        char[][] field = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = emptySign;
            }

        }
        for (char[] chars : field) {
            Arrays.fill(chars, '-');
        }

        return field;
    }

    static void doDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            System.out.println();
            for (int j = 0; j < field[i].length; j++) {
                System.out.print("\t"+field[i][j]);
            }
        }
        System.out.println();
    }

    static void handlePlayerMove(char[][] field, int size) {
        int x;
        int y;

        do {
            x = chooseCoordinate('x', size);
            y = chooseCoordinate('y', size);
        } while(field[x][y] != '-');

        field[x][y] = playerSign();
    }

    static int chooseCoordinate(char coordinate, int size) {
        Scanner scanner = new Scanner(System.in);
        int value;

        do {
            System.out.printf("Введите координату %s [1..3]%n", coordinate);
            value = scanner.nextInt() - 1;
        } while (value < 0 || value > (size - 1));

        return value;
    }

    static void handleAIMove(char[][] field, int size) {
        //Пытаюсь найти линию с наибольшим количеством X и вставить туда O
        //orientation: h(horizontal, v(vertical), md(main diagonal), sd(side diagonal)
        String[] orientations = getOrientations();
        for (int number = field.length - 1; number > 0; number--) {
            for (int orientation = 0; orientation < orientations.length; orientation++) {
                for (int lineNumber = 0; lineNumber < size; lineNumber++) {
                    if (isLineWithNumberOfSigns(field, playerSign(), number, orientations[orientation], lineNumber)) {
                        if (tryMakeMove(field, AIsign(), orientations[orientation], lineNumber)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    static boolean isEmptyCoordinate(char[][] field, int x, int y) {
        return field[x][y] == emptySign();
    }

    static String[] getOrientations() {
        String[] orientations = {"h","v","md","sd"};
        return orientations;
    }

    static boolean tryMakeMove(char[][] field, char sign, String orientation, int lineNumber) {
        if (orientation == "h" || orientation == "v") {
            for (int i = 0; i < field.length; i++) {
                if (orientation == "h") {
                    if (isEmptyCoordinate(field, lineNumber, i)) {
                        field[lineNumber][i] = sign;
                        return true;
                    }
                }
                if (orientation == "v") {
                    if (isEmptyCoordinate(field, i, lineNumber)) {
                        field[i][lineNumber] = sign;
                        return true;
                    }
                }
            }
        }
        if (orientation == "md" || orientation == "sd") {
            for (int i = 0; i < field.length; i++) {
                if (orientation == "md") {
                    if (isEmptyCoordinate(field, i, i)) {
                        field[i][i] = sign;
                        return true;
                    }
                }
                if (orientation == "sd") {
                    if (isEmptyCoordinate(field, i, field.length - 1 - i)) {
                        field[i][field.length - 1 - i] = sign;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean isLineWithNumberOfSigns(char[][] field, char sign, int number, String orientation, int lineNumber) {
        if (orientation == "h" || orientation == "v") {
            int horizontalNumber = 0;
            int verticalNumber = 0;

            for (int i = 0; i < field.length; i++) {
                if (field[lineNumber][i] == sign) {
                    horizontalNumber++;
                }
                if (field[i][lineNumber] == sign) {
                    verticalNumber++;
                }
            }
            if (orientation == "h" && horizontalNumber == number) {
                return true;
            }
            if (orientation == "v" && verticalNumber == number) {
                return true;
            }
        }

        if (orientation == "md" || orientation == "sd") {
            int mainDiagonalNumber = 0;
            int sideDiagonalNumber = 0;

            for (int i = 0; i < field.length; i++) {
                if (field[i][i] == sign) {
                    mainDiagonalNumber++;
                }

                if (field[i][field.length - 1 - i] == sign) {
                    sideDiagonalNumber++;
                }
            }
            if (orientation == "md" && mainDiagonalNumber == number) {
                return true;
            }
            if (orientation == "sd" && sideDiagonalNumber == number) {
                return true;
            }
        }
        return false;
    }
    
    static boolean isDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    static boolean isWin(char[][] field, char sign) {
        String[] orientations = getOrientations();
        for (int orientation = 0; orientation < orientations.length; orientation++) {
            for (int lineNumber = 0; lineNumber < field.length; lineNumber++) {
                if (isLineWithNumberOfSigns(field, sign, field.length, orientations[orientation], lineNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isFinal(char[][] field, char sign) {
        if (isWin(field, sign)) {
            System.out.printf("Поздравляем! %s - победил!%n", sign);
            return true;
        }
        if (isDraw(field)) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
}