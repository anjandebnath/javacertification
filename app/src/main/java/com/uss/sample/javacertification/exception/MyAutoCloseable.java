package com.uss.sample.javacertification.exception;

public class MyAutoCloseable implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("Closed!");
    }

    public void someMethod() {
        System.out.println("Doing something");
    }
}
