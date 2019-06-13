package com.uss.sample.javacertification.staticblock;

public class Counter {

    /*A static variable is associated with its class rather than
     its object or instance; hence they are known as class variables.*/
    private static int count;

    static{
        // code in this static block will be executed when
        // the JVM loads the class into memory
        count =1;
    }

    public Counter(){
        //A constructor will be invoked when an instance of the
        //class is created, while the static block will be invoked when the JVM loads the corresponding class.
        count++;
    }
    public static void printCount(){
        System.out.println("Number of instances created so far is: " + count);
    }

    public static void main(String[] args){
        Counter anInstance = new Counter();
        Counter.printCount();
        Counter anotherInstance = new Counter();
        Counter.printCount();
    }

}
