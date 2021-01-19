public class Dog extends Animal {
    public static int count;

    public Dog(String name, int maxRun, int maxSwim) {
        super(name, maxRun, maxSwim);
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
        if (distance > getMaxSwim()) {
            System.out.println(getName() + " не может проплыть более " + getMaxSwim() + " метров");
        } else {
            System.out.println(getName() + " проплыл(а) " + distance + " метров");
        }
    }

    public static int getCount() {
        return count;
    }
}
