import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

class Worker1 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
class Worker2 implements Callable<String>
{
    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        return Thread.currentThread().getName();
    }
}
public class fourth {
    public static void main(String[] args) {
        //we are creating a thread pool of size 5
        ExecutorService exe= Executors.newFixedThreadPool(5);

        Callable<String> callable=new Worker2();
        //Creating a list to hold the  future object associated with callable.
        List<Future<String>> list=new ArrayList<Future<String>>();
        for(int i=0;i<10;i++)
        {
//submitting the callable tasks  to be executed by the thread pool
            Future<String> store=exe.submit(callable);
            list.add(store);
        }

        for(Future<String> i:list)
        {
            try {
                //because Future.get() waits for tasks to get completed.
                System.out.println(new Date()+"::"+i.get());
            }catch (InterruptedException |ExecutionException e)
            {
                e.printStackTrace();
            }
            //shutting down the service
            exe.shutdown();
        }
        Worker1 w1=new Worker1();
        Thread t=new Thread(w1);
        t.setName("Thread 1");
        t.start();
    }
}
