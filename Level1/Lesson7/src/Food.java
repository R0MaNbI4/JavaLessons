public class Food {
    private int foodAmount;

    public Food(int amount) {
        foodAmount = amount;
    }

    void addFood(int amount) {
        foodAmount += amount;
    }

    boolean takeFood(int amount) {
        if (foodAmount - amount >= 0) {
            foodAmount -= amount;
            return true;
        } else {
            return false;
        }
    }
}
