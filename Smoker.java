import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Smoker extends Thread {
    private String ingredient;
    private Table table;
    private Lock lock;
    private Condition agentReady;

    public Smoker(String ingredient, Table table, Lock lock, Condition agentReady) {
        this.ingredient = ingredient;
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
                    // Wait for the needed ingredients
                    while (!table.hasNeededIngredients(ingredient)) {
                        agentReady.await();
                    }

                    // Use the ingredients to make and smoke a cigarette
                    System.out.println("Smoker with " + ingredient + " is smoking.");
                    table.resetTable();

                    // Notify the agent that the smoker is done
                    agentReady.signal();
                } finally {
                    lock.unlock();
                }

                // Simulate smoking time
                sleep(1000); // Adjust as needed
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

