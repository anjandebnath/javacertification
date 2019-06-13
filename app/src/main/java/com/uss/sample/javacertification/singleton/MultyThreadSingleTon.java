package com.uss.sample.javacertification.singleton;

public class MultyThreadSingleTon {

    /**
     * initialization on demand holder” idiom
     * to ensure multythread singleton
     */

    private MultyThreadSingleTon(){
         // private constructor
    }

    // inner class that is used to mange multythread singleton
    public static class SingleTonClassHolder{
        public static MultyThreadSingleTon multyThreadSingleTon = new MultyThreadSingleTon();
    }

    public static MultyThreadSingleTon getInstance(){
        return SingleTonClassHolder.multyThreadSingleTon;
    }

    public void log(String s){

        System.err.println(s);
    }
}
