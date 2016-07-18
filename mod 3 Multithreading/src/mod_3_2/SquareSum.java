package mod_3_2;

import java.util.concurrent.ExecutionException;

public interface SquareSum {
    long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException;
}
