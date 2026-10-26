package interrupciones;

class Buzon {
    private String mensaje = null;

    public synchronized void depositar(String m) {
        mensaje = m;
        notifyAll();
    }

    public synchronized String recoger() throws InterruptedException {
        while (mensaje == null) {
            wait();                  // espera a que alguien deposite un mensaje
        }
        return mensaje;
    }
}

class Lector implements Runnable {
    private final Buzon buzon;

    Lector(Buzon buzon) {
        this.buzon = buzon;
    }

    @Override
    public void run() {
        System.out.println("Lector: espero un mensaje en el buzón...");
        try {
            String m = buzon.recoger();
            System.out.println("Lector: he recibido \"" + m + "\"");
        } catch (InterruptedException e) {
            System.out.println("Lector: nadie me ha escrito y me han pedido parar. Me voy.");
        }
    }
}

public class EjemploWait {
    public static void main(String[] args) throws InterruptedException {
        Buzon buzon = new Buzon();
        Thread lector = new Thread(new Lector(buzon));
        lector.start();

        Thread.sleep(2000);          // nadie llama a depositar()...
        lector.interrupt();          // ...así que lo liberamos nosotros
    }
}