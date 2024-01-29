import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RollerCoaster {
    private final int capacity;
    private int passenger_on_board;

    private final Lock lock = new ReentrantLock();
    private final Condition carFull = lock.newCondition();
    private final Condition rideFinished = lock.newCondition();

    public RollerCoaster(int capacity) {
        this.capacity = capacity;
        this.passenger_on_board = 0;
    }

    public void ride(int passengerId) throws InterruptedException {
        lock.lock();
        try {
            while (passenger_on_board == capacity) {
                // Wait for the car to finish the ride
                rideFinished.await();
            }

            passenger_on_board++;

            System.out.println("Passenger " + passengerId + " is on the roller coaster.");

            if (passenger_on_board == capacity) {
                // If the car is full, signal the car to start the ride
                carFull.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void startRide() throws InterruptedException {
        lock.lock();
        try {
            while (passenger_on_board < capacity) {
                // Wait for the car to be full
                carFull.await();
            }

            System.out.println("Roller coaster is starting the ride.");

            // Simulate the ride duration
            Thread.sleep(2000);

            passenger_on_board = 0;

            System.out.println("Roller coaster finished the ride.");

            // Signal--> ride is finished
            rideFinished.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
