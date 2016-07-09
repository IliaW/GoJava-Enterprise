package Lessons;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

public class Synchronizers {

    public static void main(String[] args) throws InterruptedException {
        Synchronizers synchronizers = new Synchronizers();
        synchronizers.testExchenge();
    }

    public void testCyclickBarier() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("Барьер заполнен"));
        while (true) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " ожидание заполнения барьера");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " конец ожидания потоков");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(1000);
        }
    }

    public void testExchenge() {
        Exchanger<String> exchange = new Exchanger<>();
        Random random = new Random();
        IntStream.range(0, 2).forEach((i) -> new Thread(() -> {
            try {
                Thread.sleep(random.nextInt(1000));
                String name = Thread.currentThread().getName();
                System.out.println(name + " готов к обмену");
                System.out.println(name + " < - > " + exchange.exchange(name));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }).start());
    }
}