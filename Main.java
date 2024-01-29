//Q4

public class Main {
    public static void main(String[] args) {
        int numPassengers = 10;
        int carCapacity = 4;

        RollerCoaster rollerCoaster = new RollerCoaster(carCapacity);


        for (int i = 1; i <= numPassengers; i++) {
            new Passenger(i, rollerCoaster).start();
        }

        new Car(rollerCoaster).start();
    }
}