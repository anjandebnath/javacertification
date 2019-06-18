package com.uss.sample.javacertification.generics;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericTest {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String args[]){


        Person p1 = new Person(60, "James");
        Person p2 = new Person(55, "Bryan");
        Person p3 = new Person(45, "John");
        Person p4 = new Person(65, "Merry");


        if (p1.compareTo(p2) > 0) {
            System.out.println(p1.getName() + " is older.");
        } else {
            System.out.println(p2.getName() + " is older.");
        }

        //  Sorted by age
        // It is always better to use interface type when declaring a collection

        List<Person> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);

        Collections.sort(l, new AgeComparator());

        // it is always better to avoid classic for loop
        //and use this enhanced for loop
        /*for (Person p : l)
            System.out.println(p.getName());*/

        // lambda expression and foreach is another good technique
        //l.forEach(person -> System.out.println(person.getName()));

        //Now find the solution without stream api to get the list of older who are almost 50 years old
        List<Person> olderList = new ArrayList<>();
        for(Person person: l ){
             if(person.getAge() >= 50){
                 olderList.add(person);
             }
        }
        //olderList.forEach(person -> System.out.println(person.getAge()));


        // now using stream api to filter the list using one conditions
        olderList = l.stream()
                     .filter(p->p.getAge()>= 50)
                     .collect(Collectors.toList());
        //olderList.stream().forEach(person -> System.out.println(person.getAge()));


        // if we want to use multiple conditions then we need to use Predicate
        Predicate<Person> nameNotNull = p -> p.getName() != null;
        Predicate<Person> ageAbove60 = p -> p.getAge() >= 60;

        Predicate<Person> multipleConditions = nameNotNull.and(ageAbove60);
        List<Person> multipleFilterd = l.stream()
                .filter(multipleConditions)
                .collect(Collectors.toList());

        multipleFilterd.stream().forEach(System.out::println);

        /*PersonPredicates.filterPersons(l, PersonPredicates.isYearAbove50())
                .forEach(System.out::println);*/

    }




}
