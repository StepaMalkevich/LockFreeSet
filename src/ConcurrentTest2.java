import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Stepan on 14.04.17.
 */
public class ConcurrentTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        LockFreeSetImpl<Integer> lockFreeSet = new LockFreeSetImpl<>();

        Future future1 = executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start");

            for (int i = 0; i < 1000000; i++) {
                lockFreeSet.add(i);

                if (i % 10000 == 0){
                    System.out.println(i);
                }
            }
        });


        Future future2 = executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start");

            for (int i = 0; i < 1000000; i++) {
                lockFreeSet.remove(i);
                if (i % 10000 == 0){
                    System.out.println(i);
                }
            }
        });


        future2.get();

        executor.shutdown();
        lockFreeSet.print();


    }
}
