
class Customer extends Thread {
    private BarberShop barberShop;
    private String name;

    public Customer(BarberShop barberShop, String name) {
        this.barberShop = barberShop;
        this.name = name;
    }

    @Override
    public void run() {
        barberShop.enterBarberShop(name);
    }
}
