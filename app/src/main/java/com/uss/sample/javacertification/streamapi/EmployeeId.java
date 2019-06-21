package com.uss.sample.javacertification.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EmployeeId {

    static class Employee{

        private int id;
        private String name;

        public Employee(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }

    }


    public static void main(String args[]){

        // Primitive type List
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Paul"));
        employees.add(new Employee(102, "Adams"));
        employees.add(new Employee(103, "Polok"));
        employees.add(new Employee(104, "Paul"));

        employees.stream()
                .mapToInt(e -> e.getId())
                .forEach(s -> System.out.println(s));



        List<String> str = new ArrayList<>();
        str.add("the");
        str.add("good");
        str.add("bad");
        str.add("ugly");

        str.stream()
                .filter(s -> s.length() > 3)
                .peek(s -> System.out.println("Filtered value: " + s)) //peek()
                .map(s -> s.toUpperCase())
                .peek(s -> System.out.println("Mapped value: " + s))
                .collect(Collectors.toList());


    }
}
