package com.uss.sample.javacertification;

import com.uss.sample.javacertification.singleton.MultyThreadSingleTon;

public class MyMain {

    private MultyThreadSingleTon multyThreadSingleTon = MultyThreadSingleTon.getInstance();

    public static void main(String args[]){

        MyMain main = new MyMain();
        main.printLogger("Hello All");
    }

    private void printLogger(String s){
        multyThreadSingleTon.log(s);
    }
}
