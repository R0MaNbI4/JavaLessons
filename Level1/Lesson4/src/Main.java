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

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = emptySign;
            }

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
        Random random = new Random();
        int x;
        int y;

        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while(field[x][y] != emptySign());

        field[x][y] = AIsign();
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
        //horizontal & vertical
        int horizontalNumber;
        int verticalNumber;

        for (int i = 0; i < field.length; i++) {
            horizontalNumber = 0;
            verticalNumber = 0;
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == sign) {
                    horizontalNumber++;
                }

                if (field[j][i] == sign) {
                    verticalNumber++;
                }
                }
            if (horizontalNumber == field.length || verticalNumber == field.length) {
                return true;
            }
        }

        //diagonal
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
        if (mainDiagonalNumber == field.length || sideDiagonalNumber == field.length) {
            return true;
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