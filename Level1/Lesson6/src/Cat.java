public class Cat extends Animal {
    private static int count;
    private int maxRunDist = 200;

    public Cat (String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > maxRunDist) {
            System.out.println(getName() + " не может пробежать более " + maxRunDist + " метров");
        } else {
            System.out.println(getName() + " пробежал(а) " + distance + " метров");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать");
    }

    public static int getCount() {
        return count;
    }
}
