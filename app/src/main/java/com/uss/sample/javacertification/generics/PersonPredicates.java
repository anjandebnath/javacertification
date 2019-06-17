package com.uss.sample.javacertification.generics;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonPredicates {

    // in Java 8 we can use Predicate to filter the collections (Arraylist, Set, Map)
    //The way to filter collections is through the use of a Predicate, which is basically something that returns a boolean value.

    public static Predicate<Person> isYearAbove50() {
        return person -> person.getAge() > 50;
    }

    public static List<Person> filterPersons(List<Person> person, Predicate<Person> predicate) {
        return person.stream().filter(predicate).collect(Collectors.toList());
    }
}
