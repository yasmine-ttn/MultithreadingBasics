class PrintWorker extends Thread{
    private final static Integer printTill=10;
    private static Integer counter=0;
    String type;
    Object lock=new Object();
    PrintWorker(String t)
    {
        this.type=t;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
            while(counter<=printTill) {

                    if (shouldWait(type, counter)) {
                        lock.wait();
                    }
                System.out.println(counter);
                counter++;
                lock.notifyAll();
                }
            }
        }catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

    }
    Boolean shouldWait(String type,Integer number)
    {
        if(type.equals("Odd"))
            return number%2==0;
        else
            return number%2!=0;
    }
}
public class EvenOdd {
    public static void main(String[] args) {
        PrintWorker oddPrinter=new PrintWorker("Odd");
        PrintWorker evenPrinter=new PrintWorker("Even");
        oddPrinter.start();
        evenPrinter.start();
    }
}
