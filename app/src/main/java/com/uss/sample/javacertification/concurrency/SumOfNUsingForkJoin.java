package com.uss.sample.javacertification.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

//Since we are returning value so we are using Recursive Task
public class SumOfNUsingForkJoin extends RecursiveTask<Long> {

    private static long N = 10_00_000; // one million - we want to compute sum
    // from 1 .. one million

    private static final int NUM_THREADS = 10; // number of threads

    private long from, to;

    public SumOfNUsingForkJoin(long from, long to){

        this.from = from;
        this.to = to;

    }

    @Override
    protected Long compute() {

        // the range is something that can be handled
        // by a thread, so do summation
        if((to-from) <= N/NUM_THREADS){

            long localSum = 0;
            // add in range 'from' .. 'to' inclusive of the value 'to'
            for(long i = from; i <= to; i++) {
                localSum += i;
            }
            System.out.printf("\tSum of value range %d to %d is %d %n",
                    from, to, localSum);
            return localSum;
        }
        // no, the range is too big for a thread to handle,
        // so fork the computation
        // we find the mid-point value in the range from..to
        else{

            long mid = (from + to)/2;
            System.out.printf("Forking computation into two ranges: " +
                    "%d to %d and %d to %d %n", from, mid, mid, to);
            // determine the computation for first half
            // with the range from..mid
            SumOfNUsingForkJoin firstHalfTask = new SumOfNUsingForkJoin(from, mid);
            // now, fork off that task
            firstHalfTask.fork();
            // determine the computation for second half
            // with the range mid+1..to
            SumOfNUsingForkJoin secondHalfTask = new SumOfNUsingForkJoin(mid + 1, to);
            long resultSecond = secondHalfTask.compute();
            // now, wait for the first half of computing sum to
            // complete, once done, add it to the remaining part
            return firstHalfTask.join() + resultSecond;
        }
    }

    public static void countForkJoinSum() {

        ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);

        ForkJoinTask<Long> sumCalcTask = new SumOfNUsingForkJoin(0, N);

        // submit the computation task to the fork-join pool
        long computedSum = pool.invoke(sumCalcTask);


        // this is the formula sum for the range 1..N
        long formulaSum = (N * (N + 1)) / 2;

        // Compare the computed sum and the formula sum
        System.out.printf("Sum for range 1..%d; computed sum = %d, " +
                "formula sum = %d %n", N, computedSum, formulaSum);


    }

}
