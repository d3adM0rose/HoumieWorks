class ThreadsTask {

    // Счётчик для варианта без synchronized
    static int counterWithoutSync = 0;

    // Счётчик для варианта с synchronized
    static int counterWithSync = 0;

    public static synchronized void increment() {
        counterWithSync++;
    }

    public static void main(String[] args) throws InterruptedException {

        // 1. БЕЗ SYNCHRONIZED

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counterWithoutSync++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counterWithoutSync++;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Без synchronized");
        System.out.println("Результат: " + counterWithoutSync);
        //ожидается 200000, но может меняться


        // 2. С SYNCHRONIZED

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                increment();
            }
        });

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println();
        System.out.println("С synchronized");
        System.out.println("Результат: " + counterWithSync);
        //ожидается 200000
    }
}
