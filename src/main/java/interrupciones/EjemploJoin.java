package interrupciones;

class Descarga implements Runnable {
    @Override
    public void run() {
        System.out.println("Descarga: empiezo a descargar el fichero...");
        try {
            Thread.sleep(5000);      // simula una descarga de 5 segundos
        } catch (InterruptedException e) {
            return;
        }
        System.out.println("Descarga: fichero descargado.");
    }
}

class Instalador implements Runnable {
    private final Thread descarga;

    Instalador(Thread descarga) {
        this.descarga = descarga;
    }

    @Override
    public void run() {
        System.out.println("Instalador: espero a que termine la descarga...");
        try {
            descarga.join();
            System.out.println("Instalador: instalando el programa.");
        } catch (InterruptedException e) {
            System.out.println("Instalador: instalación cancelada por el usuario.");
        }
    }
}

public class EjemploJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread descarga = new Thread(new Descarga());
        Thread instalador = new Thread(new Instalador(descarga));
        descarga.start();
        instalador.start();

        Thread.sleep(1000);          // el usuario pulsa "Cancelar instalación"
        instalador.interrupt();
    }
}