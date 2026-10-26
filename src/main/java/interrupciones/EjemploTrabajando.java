package interrupciones;

class BuscadorPrimos implements Runnable {
    @Override
    public void run() {
        long candidato = 2;
        long encontrados = 0;

        while (!Thread.currentThread().isInterrupted()) {
            if (esPrimo(candidato)) {
                encontrados++;
            }
            candidato++;
        }
        System.out.printf("Me han pedido parar. He encontrado %d primos (he llegado hasta el %d).%n",
                encontrados, candidato);
    }

    private boolean esPrimo(long n) {
        for (long d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }
}

public class EjemploTrabajando {
    public static void main(String[] args) throws InterruptedException {
        Thread buscador = new Thread(new BuscadorPrimos());
        buscador.start();

        Thread.sleep(2000);          // le dejamos buscar 2 segundos
        buscador.interrupt();
    }
}