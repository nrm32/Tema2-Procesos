package hilos;



// La clase con la tarea del hilo: implementa Runnable
class Hilo implements Runnable {

    private final String nombre;

    Hilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.printf("Hola, soy el hilo: %s%n", this.nombre);
        System.out.printf("Hilo %s terminado.%n", this.nombre);
    }
}

public class LanzaHilos {

    public static void main(String[] args) {
        // Creamos dos objetos Thread, cada uno con su tarea
        Thread h1 = new Thread(new Hilo("H1-patata"));
        Thread h2 = new Thread(new Hilo("H2"));

        // Los ponemos en marcha: se ejecutara run() en cada hilo
        h1.start();
        h2.start();

        System.out.println(h1.isAlive());
        System.out.println("Hilo principal terminado.");
    }
}
