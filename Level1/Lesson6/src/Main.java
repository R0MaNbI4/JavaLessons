public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Муся", 200);
        cat.run(150);
        cat.run(250);
        cat.swim(3);

        System.out.println();

        Dog dog = new Dog("Рекс", 500, 10);
        dog.run(350);
        dog.run(600);
        dog.swim(7);
        dog.swim(13);

        System.out.println();

        System.out.printf("Было создано %d животных. Из них %d собак, %d кошек",
                Animal.getCount(),
                Dog.getCount(),
                Cat.getCount());
    }
}
