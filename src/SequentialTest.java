import java.util.Random;

/**
 * Created by Stepan on 14.04.17.
 */
public class SequentialTest {
    public static void main(String[] args) {
        LockFreeSetImpl<Integer> lockFreeSet = new LockFreeSetImpl<>();
        Random random = new Random();

        System.out.println(lockFreeSet.isEmpty());


        for (int i = 0; i < 100; i++) {
            lockFreeSet.add(random.nextInt(100));
        }

        lockFreeSet.print();
    }
}
