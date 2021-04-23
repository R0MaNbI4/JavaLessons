public class Task {
    int iter;

    public Task(int iter) {
        this.iter = iter;
    }

    public Task() {
        this(5);
    }

    String printed = "C";
    public synchronized void printA() {
        for (int i = 0; i < iter; i++) {
            while (printed != "C") {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printed = "A";
            System.out.print("A");
            notifyAll();
        }
    }

    public synchronized void printB() {
        for (int i = 0; i < iter; i++) {
            while (printed != "A") {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printed = "B";
            System.out.print("B");
            notifyAll();
        }
    }

    public synchronized void printC() {
        for (int i = 0; i < iter; i++) {
            while (printed != "B") {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printed = "C";
            System.out.print("C");
            notifyAll();
        }
    }
}
