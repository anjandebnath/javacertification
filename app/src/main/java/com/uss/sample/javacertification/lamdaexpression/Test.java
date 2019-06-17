package com.uss.sample.javacertification.lamdaexpression;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;

public class Test {


    interface FuncInterface{

        void abstractFunc(int x);

        default void normalFun(){

            System.out.println("Hello");
        }
    }


    public static void main(String args[]){

        // lambda expression to implement above functional interface.
        // This interface by default implements abstructFunc()
        FuncInterface fobj = (int x)-> {System.out.println(2*x); };
        fobj.abstractFunc(3);





        // without Lamda expression
        State state1 = new State(new OnStateChangeListener() {
            @Override
            public int onStateChange() {
                return 5;
            }
        });

        // with Lambda expression
        State state11 = new State(()-> {
            return 5;
        });



        // without Lamda expression
        State state2 = new State(new HasStateChangeListener() {
            @Override
            public boolean hasStateChanged(int oldState, int newState) {
                return (oldState!= newState);
            }
        });

        // Usually, the data types of the parameters in a Lambda expression can be omitted;
        // But, when there is ambiguity, you have to specify the data types of the parameters in a Lambda expression.

        // Notice that the right-hand side of the arrow does not require a return statement if you do not surround the block with { and }
        State state22 = new State((oldState, newState) -> (oldState!= newState));




    }




}
