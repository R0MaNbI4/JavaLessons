public class Dog extends Animal {
    public static int count;
    private int maxRunDist = 500;
    private int maxSwimDist = 10;

    public Dog(String name) {
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
        if (distance > maxSwimDist) {
            System.out.println(getName() + " не может проплыть более " + maxSwimDist + " метров");
        } else {
            System.out.println(getName() + " проплыл(а) " + distance + " метров");
        }
    }

    public static int getCount() {
        return count;
    }
}
