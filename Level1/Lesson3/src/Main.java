import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        guessNumber();
        guessWord();
    }

    static void guessNumber() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(10);
        int answer;
        int tryNum = 0, tryMaxNum = 3;

        System.out.println("Угадайте загаданное число от 0 до 9.");
        do {
            System.out.printf("У вас осталось %d попыток%n", tryMaxNum - tryNum);
            answer = scanner.nextInt();
            if (answer > number) {
                System.out.println("Загаданное число меньше");
            } else if (answer < number) {
                System.out.println("Загаданное число больше");
            }
            tryNum++;
        } while(answer != number && tryNum < tryMaxNum);

        if (answer == number) {
            System.out.println("Поздравляем, вы угадали число");
        } else {
            System.out.printf("Вы исчерпали все попытки. Загаданное число было %d.%n", number);
        }

        System.out.println("Повторить игру еще раз? 1 - да/ 0 - нет");
        if (scanner.nextInt() == 1) {
            guessNumber();
        }
    }

    static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String word = words[random.nextInt(words.length)];
        String answer;
        int blankSize = 15;

        System.out.println("Ваша задача - угадать слово.");

        while (true) {
            answer = scanner.nextLine();

            if (isGuessWord(answer, word)) {
                System.out.println("Поздравляем! Вы угадали!");
                return;
            }

            helpGuessWord(answer, word, blankSize);
        }
    }

    static boolean isGuessWord(String answer, String word) {
        boolean isRight = false;
        if (answer.length() == word.length()) {
            for (int i = 0; i < answer.length(); i++) {
                if (answer.charAt(i) == word.charAt(i)) {
                    isRight = true;
                } else {
                    isRight = false;
                    break;
                }
            }
            return isRight;
        }
        return false;
    }

    static void helpGuessWord(String answer, String word, int blankSize) {
        int shortestString = answer.length();
        if (word.length() < shortestString) {
            shortestString = word.length();
        }
        for (int i = 0; i < shortestString; i++) {
            if (answer.charAt(i) == word.charAt(i)) {
                System.out.print(answer.charAt(i));
            }
        }
        for (int i = 0; i < blankSize - shortestString; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
