public class Cat {
    private boolean satiety;

    public void eat(Food food, int amount) {
        satiety = food.takeFood(amount);
    }

    public void printSatiety() {
        if (satiety) {
            System.out.println("Кот сыт");
        } else {
            System.out.println("Кот голоден");
        }
    }
}
