package altoNivel;

import java.util.concurrent.atomic.AtomicInteger;

public class EjemploAtomic implements Runnable {

    static int normal = 0;
    static AtomicInteger atomico = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            normal++;                     // se pueden perder sumas
            atomico.incrementAndGet();    // nunca se pierde ninguna
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread h1 = new Thread(new EjemploAtomic());
        Thread h2 = new Thread(new EjemploAtomic());
        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println("Con int:           " + normal);
        System.out.println("Con AtomicInteger: " + atomico.get());
    }
}