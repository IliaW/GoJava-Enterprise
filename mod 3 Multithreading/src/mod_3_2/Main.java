package mod_3_2;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        SquareSumImpl squareSum = new SquareSumImpl();
        int[] array = {23, 21, 14, 17, 45};
        System.out.println("Введите кол-во потоков:");
        int numberOfThreads = scanner.nextInt();
        System.out.println(squareSum.getSquareSum(array, numberOfThreads));
    }
}
