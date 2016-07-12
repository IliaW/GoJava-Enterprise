public class RealizSemaphore implements Semaphore {

    private final int MAX_PERMITS;
    private int permits;
    private final Object lock = new Object();

    public RealizSemaphore(int permits) {
        this.permits = permits;
        this.MAX_PERMITS = permits;
    }

    @Override
    public void acquire() throws InterruptedException {
        synchronized (lock) {
            if (permits > 0) { // Если есть свободное разрешение - захватываем его
                permits--;     // при условии что есть свободные.
            } else {           // Иначе -
                lock.wait();   // ставим поток на паузу пока не появится свободное разрешение.
            }
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {
        synchronized (lock) {                          // Запрашивает определённое количество разрешений.
            if (this.permits - permits > 0) {          // Проверяет, есть ли у нас запрошенное количество разрешений.
                this.permits = this.permits - permits; // Если есть - захватывает.
            } else {                                   // Иначе -
                lock.wait();                           // приостанавливает поток до появления нужного количества разрешений.
            }
        }
    }

    @Override
    public void release() {
        synchronized (lock) {            // Отпускает разрешение возвращая его семафору.
            if (permits < MAX_PERMITS) { // Условие, исключающее превышение заданого изначально количества разрешений.
                permits++;               // Отпускаем разрешение.
            }
            lock.notify();               // Запускаем один ожидающий поток.
        }
    }

    @Override
    public void release(int permits) {
        synchronized (lock) {
            if (this.permits < MAX_PERMITS) {
                this.permits = this.permits + permits;
            }
            lock.notifyAll();
        }
    }

    @Override
    public int getAvailablePermits() {
        return permits;
    }
}