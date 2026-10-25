package synchronize;

public class hiloSynchronized2 {
     static class Timbre{
        public void timbre(){
            System.out.print("Ding...");
            try{
                Thread.sleep(2000);
                System.out.printf("Dong");
            } catch (InterruptedException e) {
                System.out.println("La alarma de incendios comienza a sonar");
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
        Thread hilo = new Thread( new Visitante(timb));
        hilo.start();
        hilo.join();

    }
}
