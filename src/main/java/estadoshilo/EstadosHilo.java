package estadoshilo;

public class EstadosHilo {

    public static void main(String[] args) throws InterruptedException {
        Thread h = new Thread(new Dormilon());
        System.out.println("Antes de start(): " + h.getState());   // NEW

        h.start();
        Thread.sleep(100);    // damos tiempo a que el hilo empiece a dormir
        System.out.println("Mientras duerme:  " + h.getState());   // TIMED_WAITING

        h.join();             // esperamos a que termine
        System.out.println("Tras join():      " + h.getState());   // TERMINATED
    }
}

// Tarea del hilo: dormir medio segundo
class Dormilon implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
