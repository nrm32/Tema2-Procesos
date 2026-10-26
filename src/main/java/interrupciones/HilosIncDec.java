package interrupciones;


class Contador {
    private int cuenta;
    Contador(int valorInicial) { this.cuenta = valorInicial; }

    synchronized public int getCuenta() { return cuenta; }
    synchronized public int incrementa() { return ++this.cuenta; }
    synchronized public int decrementa() { return --this.cuenta; }
}

// Hilo que incrementa sin parar
class HiloIncr implements Runnable {
    private final String id;
    private final Contador cont;
    HiloIncr(String id, Contador c) { this.id = id; this.cont = c; }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                this.cont.incrementa();
                this.cont.notify();   // avisa: ya hay algo que decrementar
                System.out.printf("%s incrementa -> %d%n", id, cont.getCuenta());
            }
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

// Hilo que decrementa; si no puede, espera
class HiloDecr implements Runnable {
    private final String id;
    private final Contador cont;
    HiloDecr(String id, Contador c) { this.id = id; this.cont = c; }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                // espera NO activa mientras no haya nada que decrementar
                while (this.cont.getCuenta() < 1) {
                    System.out.printf("%s no puede decrementar (0). Espera.%n", id);
                    try {
                        this.cont.wait();
                    } catch (InterruptedException e) {}
                }
                this.cont.decrementa();
                System.out.printf("%s decrementa -> %d%n", id, cont.getCuenta());
            }
        }
    }
}

public class HilosIncDec {
    public static void main(String[] args) {
        Contador c = new Contador(0);
        for (int i = 0; i < 5; i++) new Thread(new HiloIncr("INC" + i, c)).start();
        for (int i = 0; i < 5; i++) new Thread(new HiloDecr("DEC" + i, c)).start();
    }
}
