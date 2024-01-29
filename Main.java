public class Main {

    public static void main(String[] args) {
        int numChairs = 3;
        BarberShop barberShop = new BarberShop(numChairs);

        Barber barber = new Barber(barberShop, "Barber");
        barber.start();


        int numCustomers = 10;
        for (int i = 1; i <= numCustomers; i++) {
            Customer customer = new Customer(barberShop, "Customer " + i);
            customer.start();


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
