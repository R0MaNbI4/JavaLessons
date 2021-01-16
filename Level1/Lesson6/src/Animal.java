public abstract class Animal {
    private static int count;
    private String name;
    private int maxRun;
    private int maxSwim;

    public Animal(String name, int maxRun, int maxSwim) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        count++;
    }

    public Animal(String name, int maxRun) {
        this(name, maxRun, 0);
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public String getName() {
        return name;
    }

    public static int getCount() {
        return count;
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxSwim() {
        return maxSwim;
    }
}
