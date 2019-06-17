package com.uss.sample.javacertification.generics;

public class Person implements Comparable<Person>{


    // compareTo(obj) is the method of the Comparable interface
    // that is called on one object, to compare it to another object,
    // so the object to be compared has to implement this interface.
    @Override
    public int compareTo(Person person) {
        if(this.getAge() > person.getAge() ){
           return 1;
        }else if(this.getAge() < person.getAge()){
            return -1;
        }
        else
           return 0;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;
    private String name;

    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }



}
