public class third{
    private Integer counter = 0;
    private void increment() {
        this.counter++;
    }


    public static void main(String[] args) throws InterruptedException {
        third SynchronizedMethod = new third();
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized(lock) {
                    SynchronizedMethod.increment();
                }
            }
        }, "IncrementThread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized(lock) {
                    SynchronizedMethod.increment();
                }
            }
        }, "IncrementThread");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Thread.sleep(1000);
        System.out.println(SynchronizedMethod.counter);
    }
}

