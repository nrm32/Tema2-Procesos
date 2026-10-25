package synchronize;

public class CenaFilosofos {

    public static void main(String[] args) {
        final int N = 5;
        Object[] tenedores = new Object[N];
        for (int i = 0; i < N; i++) {
            tenedores[i] = new Object();
        }
/*
        for (int i = 0; i < N; i++) {
            Object izquierdo = tenedores[i];
            Object derecho = tenedores[(i + 1) % N];
            // Todos cogen primero el izquierdo: PELIGRO
            new Thread(new Filosofo(i, izquierdo, derecho)).start();
        }*/
        for (int i = 0; i < N; i++) {
            int a = i;
            int b = (i + 1) % N;
            // Siempre primero el tenedor de número menor
            Object primero = tenedores[Math.min(a, b)];
            Object segundo = tenedores[Math.max(a, b)];
            new Thread(new Filosofo(i, primero, segundo)).start();
        }
    }
}
