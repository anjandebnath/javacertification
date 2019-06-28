package com.uss.sample.javacertification.lamdafunction;

import java.util.function.Function;

public class FunctionInterface {

    //API which accepts an implementation of
    //Function interface
    static void modifyTheValue(int valueToBeOperated,
                               Function<Integer, Integer> function){

        int newValue = function.apply(valueToBeOperated);
        /**
         * Do some operation using the new value
         */
        System.out.println("New value is :" + newValue);
    }

    public static void main(String []args){

        int incr = 20;
        int myNumber = 10;

        //  the lambda expressions being created accept one parameter and return some value.
        modifyTheValue(myNumber, val -> val + incr);

    }
}
