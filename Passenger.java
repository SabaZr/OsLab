class Passenger extends Thread {
    private final int id;
    private final RollerCoaster rollerCoaster;

    public Passenger(int id, RollerCoaster rollerCoaster) {
        this.id = id;
        this.rollerCoaster = rollerCoaster;
    }

    @Override
    public void run() {
        try {
            while (true) {
                rollerCoaster.ride(id);

                Thread.sleep((long) (Math.random() * 500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}