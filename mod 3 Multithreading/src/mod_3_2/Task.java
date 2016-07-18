package mod_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

public class Task implements Callable<Long> {

    int start;
    int end;
    int[] values;
    List<Long> tasksResult = new ArrayList<>();
    Phaser phaser;

    public Task(int start, int end, int[] values, List<Long> tasksResult, Phaser phaser) {
        this.start = start;
        this.end = end;
        this.values = values;
        this.tasksResult = tasksResult;
        this.phaser = phaser;
    }

    @Override
    public Long call() throws Exception {
        Long squareSum = 0L;
        for (int i = start; i < end; i++) {
            squareSum += values[i] * values[i];
        }
        System.out.println(Thread.currentThread().getName() + " выполнил " + squareSum);
        tasksResult.add(squareSum);
        phaser.arriveAndDeregister();

        return squareSum;
    }
}
