package altoNivel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Pizza implements Runnable {
    private final int numero;

    Pizza(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println("Pizza " + numero + " -> la prepara " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);   // tarda un segundo en hacerse
        } catch (InterruptedException e) {
            return;
        }
    }
}

public class Pizzeria {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cocineros = Executors.newFixedThreadPool(2);   // 2 cocineros

        for (int i = 1; i <= 5; i++) {
            cocineros.execute(new Pizza(i));   // 5 pedidos
        }

        cocineros.shutdown();                                // no se aceptan más pedidos
        cocineros.awaitTermination(1, TimeUnit.MINUTES);     // esperamos a que acaben
        System.out.println("Todas las pizzas listas");
    }
}