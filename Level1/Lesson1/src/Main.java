public class Main {
    public static void main(String[] args) {
        byte _byte = 127;
        short _short = 32767;
        int _int = 2147483647;
        long _long = 9223372036854775807L;
        float _float = 0.1f;
        double _double = 3.4e+100;
        boolean _boolean = true;
        char _char = 'a';

        System.out.println(calc (10, 20, 30, 40));
        System.out.println(isInRange (7, 9));
        checkSignPrint(13);
        System.out.println(checkSignBool(-3));
        printWelcome("Макс");
        isLeapPrint(20);
        isLeapPrint(200);
        isLeapPrint(400);
    }

    static float calc(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    static boolean isInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    static void checkSignPrint(int a) {
        if (a >= 0)
            System.out.println("Число положительное");
        else
            System.out.println("Число отрицательное");
    }

    static boolean checkSignBool(int a) {
        return a >= 0;
    }

    static void printWelcome(String name) {
        System.out.println("Привет, " + name);
    }

    static void isLeapPrint(int year) {
        if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0)) {
            System.out.println("Високосный");
        } else {
            System.out.println("Невисокосный");
        }
    }
}
