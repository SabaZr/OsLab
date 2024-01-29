import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition agentReady = lock.newCondition();

        Table table = new Table();
        Agent agent = new Agent(table, lock, agentReady);
        Smoker smoker1 = new Smoker("tobacco", table, lock, agentReady);
        Smoker smoker2 = new Smoker("paper", table, lock, agentReady);
        Smoker smoker3 = new Smoker("matches", table, lock, agentReady);

        agent.start();
        smoker1.start();
        smoker2.start();
        smoker3.start();
    }
}