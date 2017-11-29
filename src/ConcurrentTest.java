/**
 * Created by Stepan on 14.04.17.
 */
public class ConcurrentTest {

    public static void main(String[] args) throws InterruptedException {

        LockFreeSetImpl<Integer> lockFreeSet = new LockFreeSetImpl<>();

        Runnable task1 = () -> {
            System.out.println(Thread.currentThread().getName() + " start");
            for (int i = 0; i < 10; i++) {
                lockFreeSet.add(i);
            }

        };

        Runnable task2 = () -> {
            System.out.println(Thread.currentThread().getName() + " start");
            for (int i = 10; i < 20; i++) {
                lockFreeSet.add(i);
            }
        };


        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();

        System.out.println("after thread 1 start");
        lockFreeSet.print();

        thread2.start();
        System.out.println("after thread 2 start");
        lockFreeSet.print();
        thread1.join();

        thread2.join();


        lockFreeSet.print();
    }
}
