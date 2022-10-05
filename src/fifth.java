import java.util.concurrent.locks.ReentrantLock;

public class fifth{
    public static void main(String[] args) throws InterruptedException {
        fifth worker = new fifth();
        Cakes bakery1 = new Cakes("Vanilla", 10000);
        Cakes bakery2 = new Cakes("Raspberry", 20000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                worker.transfer(bakery1, bakery2, 1000);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                worker.transfer(bakery2, bakery1, 1000);

            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Total amount of cakes for both Bakeries: " + (bakery1.getAmount() + bakery2.getAmount()));
    }

    private void transfer(Cakes fromBakery,Cakes toBakery, Integer amount) {
        synchronized (fromBakery.getLock()) {
            synchronized (toBakery.getLock()) {
                System.out.println("Transferring cakes: " + amount + " from bakery " + fromBakery.getBakery() + " to bakery " + toBakery.getBakery());
                toBakery.setAmount(toBakery.getAmount() - amount);
                fromBakery.setAmount(fromBakery.getAmount() + amount);
            }
        }
    }




}

class Cakes {
    private String type;
    private Integer amount;
    private ReentrantLock lock;

    public Cakes(String type, Integer amount) {
        this.type = type;
        this.amount = amount;
        this.lock = new ReentrantLock();
    }

    public String getBakery() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}