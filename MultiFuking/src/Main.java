public class Main {

    public static int k = 0;
    public static int mainNum = 0;
    public static int runNum1 = 0;
    public static int runNum2 = 0;
    public static final int OPERETIONS = 1000000000;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Run1());
        Thread thread2 = new Thread(new Run2());
        thread1.start();
        thread2.start();

        System.out.println("Main start   " + "Счётчик в момент запуска потока: " + k);
        for (int i = 0; k < OPERETIONS; i++) {
            k++;
            mainNum++;
        }
        System.out.println("Main end   " + "Количество добавлений в потоке main: " + mainNum);

        thread1.join();

    }

    public static class Run1 implements Runnable {

        @Override
        public void run() {
            System.out.println("Run1 start   " + "Счётчик в момент запуска потока: " + k);
            for (int i = 0; k < OPERETIONS; i++) {
                k++;
                runNum1++;
            }
            System.out.println("Run1 end   " + "Количество добавлений в потоке  Run1: " + runNum1);
        }
    }

    public static class Run2 implements Runnable {

        @Override
        public void run() {
            System.out.println("Run2 start   " + "Счётчик в момент запуска потока: " + k);
            for (int i = 0; k < OPERETIONS; i++) {
                k++;
                runNum2++;
            }
            System.out.println("Run2 end   " + "Количество добавлений в потоке  Run2: " + runNum2);
        }
    }
}