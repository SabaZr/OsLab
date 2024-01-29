class Barber extends Thread {
    private BarberShop barberShop;
    private String name;

    public Barber(BarberShop barberShop, String name) {
        this.barberShop = barberShop;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            barberShop.cutHair(name);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}