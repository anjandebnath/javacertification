package com.uss.sample.javacertification.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {

    private static AtomicInteger at = new AtomicInteger(0);

    static class MyRunnable implements Runnable{

        private int myCounter;
        private int myPrevCounter;
        private int myCounterPlusFive;
        private boolean isNine;

        @Override
        public void run() {

            // the value is incremented and its new value is returned
            myCounter = at.incrementAndGet();
            System.out.println("Thread " + Thread.currentThread().getId() + "  / Counter : " + myCounter);

            //the value is incremented, but its previous value is returned.
            myPrevCounter = at.getAndIncrement();
            System.out.println("Thread " + Thread.currentThread().getId() + " / Previous : " + myPrevCounter);


            // the delta is added to the value and the new value is returned
            myCounterPlusFive = at.addAndGet(5);
            System.out.println("Thread " + Thread.currentThread().getId() + " / plus five : " + myCounterPlusFive);



            //the value is compared to the expect param, and if they are equal, then the value is set to the update param and true is returned
            isNine = at.compareAndSet(9, 3);
            if (isNine) {
                System.out.println("Thread " + Thread.currentThread().getId()
                        + " / Value was equal to 9, so it was updated to " + at.intValue());
            }

        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        t1.start();
        t2.start();
    }
}
