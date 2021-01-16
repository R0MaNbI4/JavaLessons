public class Cat {
    private boolean satiety;

    public void eat(Food food, int amount) {
        if (food.takeFood(amount)) {
            satiety = true;
        } else {
            satiety = false;
        }
    }

    public void printSatiety() {
        if (satiety) {
            System.out.println("Кот сыт");
        } else {
            System.out.println("Кот голоден");
        }
    }
}
