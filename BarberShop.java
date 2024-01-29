public class BarberShop {
    private int n; //  chairs in the waiting room
    private int occupied; // chairs currently occupied
    private boolean barber_sleeping; // if the barber is sleeping

    public BarberShop(int n) {
        this.n = n;
        this.occupied = 0;
        this.barber_sleeping = true;
    }


    public synchronized void enterBarberShop(String customer_name) {
        System.out.println(customer_name + " enters the barbershop.");

        if (occupied < n) {
            //  available chairs
            occupied++;
            System.out.println(customer_name + "  a seat in the waiting room--> Occupied chairs: " + occupied);

            if (barber_sleeping) {
                // Wake up the barber && waiting customers
                barber_sleeping = false;
                System.out.println("Barber is woken up by " + customer_name);
                notifyAll();
            }
        } else {
            // No available chairs, customer leaves
            System.out.println("There is no available chairs. " + customer_name + " leaves the barbershop.");
        }
    }

    // cut hair
    public synchronized void cutHair(String barber_name) {
        System.out.println(barber_name + " is ready to cut hair.");

        while (occupied == 0) {
            // Barber is sleeping && wait for customers to arrive
            System.out.println(barber_name + " is sleeping.");
            barber_sleeping = true;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        occupied--;
        System.out.println(barber_name + "  cutting hair--> Occupied chairs: " + occupied);
    }
}
