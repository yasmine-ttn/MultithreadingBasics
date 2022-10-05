import java.util.stream.IntStream;

public class Main extends Thread{
    @Override
    public void run() {
        System.out.println("Executed "+getName()+" and state "+  getState());
    }

    public static void main(String[] args) {
        IntStream.range(0,10).forEach(
                i->{
                    Thread t=new Main();
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


    }
}