package mod_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareSumImpl implements SquareSum {

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException {

        if (numberOfThreads > values.length) {
            numberOfThreads = values.length;
        }

        List<Long> tasksResult = new ArrayList<>();
        int step = values.length / numberOfThreads;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        int start = 0;
        int end = step;
        Phaser phaser = new Phaser(1);

        for (int i = 0; i < numberOfThreads; i++) {

            if (i + 1 == numberOfThreads) {
                end = values.length;
            }

            Future<Long> future = service.submit(new Task(start, end, values, tasksResult, phaser));


            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            phaser.register();
            start += step;
            end += step;
        }

        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndDeregister();
        service.shutdown();

        long result = 0;
        for (Long number : tasksResult) {
            result += number;
        }
        return result;
    }
}