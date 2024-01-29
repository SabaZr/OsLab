class Car extends Thread {
    private final RollerCoaster rollerCoaster;

    public Car(RollerCoaster rollerCoaster) {
        this.rollerCoaster = rollerCoaster;
    }

    @Override
    public void run() {
        try {
            while (true) {
                rollerCoaster.startRide();

                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}