package Lessons;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    private final Lock lock = new ReentrantLock(); // Если дописать true - будет просыпаться

    // поток который дольше всего ждёт
    // Если false (или без параметров) - будет работать быстрее но нет последовательности
    public static void main(String[] args) {
        Locks locks = new Locks();
        //IntStream.range(0, 10).forEach(i -> new Thread(locks::testTryLock));
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    locks.testTryLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void testLock() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " попытка блокировки");
        lock.lock();
        try {
            System.out.println(threadName + " выполнене критической секции");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(threadName + " поток освобождён");
            lock.unlock();
        }
    }

    public void testTryLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " попытка блокировки");
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(threadName + " выполнене критической секции");
                    Thread.sleep(20);

                } finally {
                    System.out.println(threadName + " поток освобождён");
                    lock.unlock();
                }
            } else {
                System.out.println(threadName + " невозможно захватить поток");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}