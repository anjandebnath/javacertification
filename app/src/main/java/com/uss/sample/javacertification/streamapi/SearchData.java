package com.uss.sample.javacertification.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SearchData {

    public static void main(String args[]){

        Optional<Integer> any = Stream.of(1, 10, 5, 3, 13, 20)
                .filter(i -> i % 2 == 0)
                .findAny(); //can return 10 or 20

        //System.out.println("search of any "+any);

        OptionalInt first = IntStream.of(1, 10, 5, 3, 13, 20)
                .filter(i -> i % 2 == 0)
                .findFirst();

        //System.out.println("search of first "+first.getAsInt());

        List<String> myList = Arrays.asList("follow your heart but take your brain with you".split(" "));
        //myList.stream().distinct().sorted().forEach(System.out::println);

        Comparator<String> byLengthComparator = (s1, s2) -> Integer.compare( s1.length(), s2.length());
        Comparator<String> byLettersComparator = (s1, s2) -> s1.compareTo(s2);

       /* Stream.of("hello","good bye", "black", "white", "good", "bad")
                .sorted(byLengthComparator.thenComparing(byLettersComparator))
                .forEach(s -> System.out.println(s));*/


        //to collect the elements of a list to another list
        List<String> letters = new ArrayList<>();
        letters.add("H");
        letters.add("e");
        letters.add("l");
        letters.add("l");
        letters.add("o");
        List<String> word = letters.stream().collect(() -> new ArrayList<>(), // Supplier<R> supplier
                (c, s) -> c.add(s.toUpperCase()),                             // BiConsumer<R,? super T> accumulator
                (c1, c2) -> c1.addAll(c2));                                   // BiConsumer<R,R> combiner

        word.forEach(s1-> System.out.println("collect list to another list:"+s1));



        // flatMap to aggregate
        List<List<Integer>> intsOfInts = Arrays.asList(
                Arrays.asList(1, 3, 5),
                Arrays.asList(2, 4));

        intsOfInts.stream()
                .flatMap(ints -> ints.stream())
                .sorted()
                .map(i -> i * i)
                .forEach(System.out::println);



        List<Student> physicsStudents = new ArrayList<>();
        physicsStudents.add(new Student(101, "Paul"));
        physicsStudents.add(new Student(102,"Adams"));
        physicsStudents.add(new Student(103,"Pat"));
        physicsStudents.add(new Student(104,"Symcox"));

        List<Student> chemistryStudents = new ArrayList<>();
        chemistryStudents.add(new Student(201, "alexa"));
        chemistryStudents.add(new Student(202,"Mondy"));
        chemistryStudents.add(new Student(201, "Racho"));



        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Physics", physicsStudents));
        courses.add(new Course("Chemistry", chemistryStudents));

        List<String> students = courses.stream()
                .flatMap(course -> course.getStudents().stream()) //Get the students of each course
                .map(student -> student.getName()) //Now that we have a stream with all the students, we extract their name
                .collect(Collectors.toList());

        students.forEach(System.out::println);





    }


}
