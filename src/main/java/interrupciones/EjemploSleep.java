package interrupciones;

class Recordatorio implements Runnable {
    @Override
    public void run() {
        System.out.println("Recordatorio programado: aviso dentro de 10 segundos.");
        try {
            Thread.sleep(10000);
            System.out.println("¡Recordatorio! Tienes clase de PSP.");
        } catch (InterruptedException e) {
            System.out.println("Recordatorio cancelado.");
        }
    }
}

public class EjemploSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread recordatorio = new Thread(new Recordatorio());
        recordatorio.start();

        Thread.sleep(1000);          // al segundo, el usuario cambia de idea
        recordatorio.interrupt();
    }
}