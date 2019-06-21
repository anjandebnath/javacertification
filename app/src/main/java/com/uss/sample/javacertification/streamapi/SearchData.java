package com.uss.sample.javacertification.streamapi;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SearchData {

    public static void main(String args[]){

        Optional<Integer> any = Stream.of(1, 10, 5, 3, 13, 20)
                .filter(i -> i % 2 == 0)
                .findAny(); //can return 10 or 20

        System.out.println("search of any "+any);

        OptionalInt first = IntStream.of(1, 10, 5, 3, 13, 20)
                .filter(i -> i % 2 == 0)
                .findFirst();

        System.out.println("search of first "+first.getAsInt());
    }


}
