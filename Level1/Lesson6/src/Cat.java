public class Cat extends Animal {
    private static int count;

    public Cat (String name, int maxRun) {
        super(name, maxRun);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > getMaxRun()) {
            System.out.println(getName() + " не может пробежать более " + getMaxRun() + " метров");
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
