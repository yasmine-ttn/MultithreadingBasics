import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class second {
    public static void main(String[] args) {
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        ExecutorService executorService2=Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        for (int i = 0; i < 10 ; i++) {
            executorService3.execute(
                    () -> {
                        System.out.println("Doing through cachedThreadPool");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
        }


        try {
            executorService3.shutdown();
            executorService3.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Shutting down Cached thread pool executor service");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10 ; i++) {
            executorService2.submit(
                    () -> {
                        System.out.println("Doing through Single thread executor");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    });
        }
        try {
            executorService2.shutdown();
            executorService2.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Shutting down new single thread executor service");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10 ; i++) {
            executorService1.submit(() -> {
                System.out.println("Doing through fixed thread pool executor");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            executorService1.shutdown();
            executorService1.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Shutting down fixed thread pool executor service");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}