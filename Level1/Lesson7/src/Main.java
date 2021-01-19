public class Main {
    public static void main(String[] args) {
        Food food = new Food(14);

        Cat[] cats = {
            new Cat(),
            new Cat(),
            new Cat()
        };

        for (Cat cat : cats) {
            cat.eat(food, 5);
            cat.printSatiety();
        }

        System.out.println();

        food.addFood(1);
        cats[2].eat(food, 5);
        cats[2].printSatiety();
    }
}