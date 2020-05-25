package labs.lab7;

public class Lab7 {

    public static void main(String[] args) throws Exception {
        int threadCount = 8;
        int iterationCount = 1_000_000_000;

        long start = System.currentTimeMillis();
        double pi = MyPiCalculator.calculatePi(threadCount, iterationCount);
        long time = System.currentTimeMillis() - start;

        System.out.println("PI is " + pi);
        System.out.println("THREADS " + threadCount);
        System.out.println("ITERATIONS " + iterationCount);
        System.out.println("TIME " + time + "ms");
    }
}
