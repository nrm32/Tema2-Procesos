package synchronize;

class Filosofo implements Runnable {

    private final int id;
    private final Object primerTenedor;
    private final Object segundoTenedor;

    Filosofo(int id, Object primerTenedor, Object segundoTenedor) {
        this.id = id;
        this.primerTenedor = primerTenedor;
        this.segundoTenedor = segundoTenedor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.printf("Filósofo %d piensa%n", id);
            pausa(100);

            synchronized (primerTenedor) {
                System.out.printf("Filósofo %d coge su primer tenedor%n", id);
                pausa(100);   // facilita que se produzca el interbloqueo

                synchronized (segundoTenedor) {
                    System.out.printf("Filósofo %d coge el segundo tenedor y COME%n", id);
                    pausa(200);
                }
            }
        }
    }

    private void pausa(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

