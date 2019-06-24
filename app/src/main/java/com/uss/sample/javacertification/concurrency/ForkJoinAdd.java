package com.uss.sample.javacertification.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

// this will sum all the numbers from the range
public class ForkJoinAdd extends RecursiveTask<Long> {

    public static final long THRESH_HOLD = 10_000;

    // the given input will be converted to the numbers array and we will calculate the sum
    private final long[] numbers;
    private final int start;
    private final int end;

    @Override
    protected Long compute() {

        int length = end - start;

        // here single thread can perform the task
        if(length < THRESH_HOLD){
            return add();
        }

        int split = length/2;

        ForkJoinAdd firstTask = new ForkJoinAdd(numbers, start, start+ split);
        firstTask.fork(); // The fork() method allows a ForkJoinTask to be planned for asynchronous execution. A new task can be created with this method.

        ForkJoinAdd secondTask = new ForkJoinAdd(numbers, start+split, end);

        long secondTaskResult = secondTask.compute();
        long firstTaskResult = firstTask.join(); // The join() method returns the result of the computation when the computation is done.


        return firstTaskResult + secondTaskResult;
    }


    public ForkJoinAdd(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinAdd(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    private long add() {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += numbers[i];
        }
        return result;
    }


    public static long startForkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinAdd(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
