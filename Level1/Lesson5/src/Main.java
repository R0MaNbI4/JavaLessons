public class Main {
    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        workers[0] = new Worker("Петренко Василий Петрович", "Директор", "dir@mail.ru", "88005553535", 100000, 47);
        workers[1] = new Worker("Иванова Татьяна Ивановна", "Бухгалтер", "glavbuh@mail.ru", "89148901722", 50000, 55);
        workers[2] = new Worker("Ломайко Максим Владимирович", "Сисадмин", "sys@mail.ru", "89528197224", 40000, 27);
        workers[3] = new Worker("Чистова Вера Дмитриевна", "Уборщица", "cleaner@mail.ru", "89140918252", 30000, 44);
        workers[4] = new Worker("Бдун Антон Юрьевич", "Охранник", "safe@mail.ru", "89149178228", 25000, 54);

        for (int i = 0; i < workers.length; i++) {
            if (workers[i].getAge() > 40) {
                workers[i].getInfo();
                System.out.println();
            }
        }
    }
}