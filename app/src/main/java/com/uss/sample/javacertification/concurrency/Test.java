package com.uss.sample.javacertification.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String args[]){

        // The ExecutorService interface represents a mechanism that executes tasks in the background.
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
        executor.shutdown();

    }
}
