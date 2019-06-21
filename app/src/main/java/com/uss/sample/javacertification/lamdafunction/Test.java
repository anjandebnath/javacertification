package com.uss.sample.javacertification.lamdafunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Test {

    public static void main(String args[]){


        List<String> l = new ArrayList<>();
        l.add("Successfully");
        l.add("easy");
        l.add("fortune");

        //a Predicate is a functional interface that can be used anywhere you need to evaluate a boolean condition
        List<String> filtered = l.stream()
                .filter(s -> s.length() > 5)
                .collect(Collectors.<String>toList());

        System.out.print("Predicate::"+filtered);


        Predicate<String> isALongWord = s -> s.length() > 10;
        String s = "successfull";
        boolean result = isALongWord.test(s); // You can call test() method on a Predicate object.
        System.out.println("Predicate Result:" + result);




        // a Consumer is a functional interface that perform some operations based on the argument but do
        //not return anything to their callers
        Consumer<String> printUpperCase = s1 -> System.out.println(s1.toUpperCase());
        printUpperCase.accept("hello"); //The accept() method “consumes” an object and returns nothing (void).


        String str = "I am going to write OCP8 exam";
        Predicate<String> predContains = s1 -> s1.contains(str);
        checkString(predContains, "OCPJP");



        //A BiFunction is similar to Function , but the difference is that it takes two arguments: it takes
        //arguments of generic types T and U and returns an object of generic type R .
        BiFunction<String, String, String> concatStr = (x, y) -> x + y;
        System.out.println("BiFunction:: "+concatStr.apply("hello ", "world"));


        //UnaryOperator is a functional interface that receives a
        // value of a certain type and returns a value of the same type.
        UnaryOperator<Integer> unary = v -> v * 10;
        // This means the same as the UnaryOperator above.
        Function<Integer, Integer> function = v -> v * 10;

        System.out.println("Unary:: "+unary.apply(10));
        System.out.println(function.apply(10));


    }

    static void checkString(Predicate<String> predicate, String str) {
        System.out.println(predicate.test(str) ? "contains" : "doesn't contain");
    }
}
