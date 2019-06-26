package com.uss.sample.javacertification.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.util.stream.Collectors.toList;

public class ForkJoinFibonacci extends RecursiveAction {

    private static final long threshold = 5;
    private List<Long> data;
    private long total = 0;
    long sum = 0;

    public ForkJoinFibonacci(List<Long> data) {
        this.data = data;
    }

    private long getTotal(){
        return total;
    }


    @Override
    protected void compute() {

        if (data.size() <= threshold) {
            // base case
            sum = computeSumDirectly();
            System.out.format("Sum of %s: %d\n", data.toString(), sum);

        } else {
            // recursive case
            // Calcuate new range
            int mid = data.size() / 2;

            ForkJoinFibonacci firstSubtask =
                    new ForkJoinFibonacci(data.subList(0, mid));
            ForkJoinFibonacci secondSubtask =
                    new ForkJoinFibonacci(data.subList(mid, data.size()));



//            firstSubtask.fork(); // queue the first task
//            secondSubtask.compute(); // compute the second task
//            firstSubtask.join(); // wait for the first task result

            // Or simply call
            invokeAll(firstSubtask, secondSubtask);

            total = firstSubtask.sum + secondSubtask.sum;
        }

    }

    private long computeSumDirectly() {
        long sum = 0;

        for (Long l: data) {
            sum += l;
        }
        return sum;
    }

    public static void compute50thFibonacci(){

        Random random = new Random();

        List<Long> data = random
                .longs(10, 1, 5)
                .boxed()
                .collect(toList());


        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinFibonacci task = new ForkJoinFibonacci(data);
        pool.invoke(task);

        System.out.println("Total is : "+task.getTotal());

    }
}
