import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Agent extends Thread {
    private Table table;
    private Lock lock;
    private Condition agentReady;

    public Agent(Table table, Lock lock, Condition agentReady) {
        this.table = table;
        this.lock = lock;
        this.agentReady = agentReady;
    }

    @Override
    public void run() {
        try {
            while (true) {
                lock.lock();
                try {
                    // Randomly select two supplies
                    table.placeRandomItems();

                    // Notify smokers that new items are on the table
                    agentReady.signalAll();

                    // Wait until a smoker signals that they are done
                    agentReady.await();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
