public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        new Thread(task::printA).start();
        new Thread(task::printB).start();
        new Thread(task::printC).start();
    }
}
