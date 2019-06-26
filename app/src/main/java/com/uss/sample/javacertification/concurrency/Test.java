package com.uss.sample.javacertification.concurrency;

import java.util.concurrent.ForkJoinPool;

public class Test {

    public static void main(String args[]){

    /*    // The ExecutorService interface represents a mechanism that executes tasks in the background.
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //The Callable interface is similar to Runnable,
        // they're both designed to be executed by another thread,
        // a Runnable however, does not return a result and cannot throw a checked exception.
        Callable c = () -> {

            int n=0;
            for (int i=0; i< 100; i++){n+= i;}
            return n;

        };


        Future<Long> future = executor.submit(c);
        try {

            Long result = future.get(); //waits for the thread to complete
            System.out.println(result);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();*/


        //System.out.println("Sum of 1 to 1_000_000:: "+ForkJoinAdd.startForkJoinSum(1_000_000));

        //SumOfNUsingForkJoin.countForkJoinSum();

        ForkJoinFibonacci.compute50thFibonacci();




    }
}
