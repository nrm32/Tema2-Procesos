package synchronize;

public class HiloSynchronized2 {
     static class Timbre{
        public void timbre(){
            //System.out.println("Me acerco al timbre\n");
            //Así es exactamente igual que si lo pusiera en la declaracion de la funcion
            synchronized (this){
                System.out.print("Ding...");
                try{
                    Thread.sleep(2000);
                    System.out.printf("Dong\n");
                } catch (InterruptedException e) {
                    System.out.println("La alarma de incendios comienza a sonar");
                }
            }

        }

    }
    static class Visitante implements Runnable{
      Timbre t;

        public Visitante(Timbre t) {
            this.t = t;
        }
        public void run(){
            t.timbre();
        }
    }
    public static void main(String[] args) throws  InterruptedException{
        Timbre timb = new Timbre();
        Thread hilo1 = new Thread( new Visitante(timb));
        Thread hilo2 = new Thread( new Visitante(timb));
        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();
    }
}
