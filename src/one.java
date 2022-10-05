import java.util.stream.IntStream;

public class one {
    public static void main(String[] args) {
        //Create Thread using Thread Class
        IntStream.range(0,2).forEach(
                i->{
                    Thread t=new ThreadClass();
                    try {
                        t.join();
                    }catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(1000);

                    }catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                    t.setName("Thread "+i);

                    t.start();
                }
        );

//        new ThreadClass().start();

        //Create Thread using Runnable
        new Thread(new ThreadClass2()).start();

    }
}

class ThreadClass extends Thread {
    @Override
    public void run() {
        System.out.println("Executed "+getName()+" and state "+  getState());
    }
}

class ThreadClass2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread with Runnable Interface running here");

    }
}