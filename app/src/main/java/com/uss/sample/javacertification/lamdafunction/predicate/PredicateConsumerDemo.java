package com.uss.sample.javacertification.lamdafunction.predicate;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PredicateConsumerDemo {

    // api to update student fee
    public static Student updateStudentFee(Student student,
                                           Predicate<Student> predicate,
                                           Consumer<Student> consumer){

        //Use the predicate to decide when to update the discount.
        if ( predicate.test(student)){
            //Use the consumer to update the discount value.
            consumer.accept(student);
        }
        return student;
    }


    // api to update the name
    public static String updateName(Student student,
                                     Predicate<String> predicate,
                                     Function<String, String> function){
        if( predicate.test(student.lastName)){
            return function.apply(student.lastName);
        }

        return null;
    }

    // api to get new student object
    public static Student getStudent(){

        Supplier<Student> studentSupplier = () -> {
            return new Student("Varun", "Dhawan", 7.5);
        };

        return studentSupplier.get();
    }


    public static void main(String[] args) {


        // Use of Consumer
        Student student1 = new Student("Ashok","Kumar", 8.5);
        student1 = updateStudentFee(student1,
                //Lambda expression for Predicate interface
                student -> student.grade > 8.5,
                //Lambda expression for Consumer inerface
                student -> student.feeDiscount = 30.0);
        student1.printFee();

        Student student2 = new Student("Rajat","Verma", 8.0);
        student2 = updateStudentFee(student2,
                student -> student.grade >= 8,
                student -> student.feeDiscount = 20.0);
        student2.printFee();


        // Use of Function
        Student student3 = new Student("Monoj", "Tiwari", 5.5);

        String name = updateName(student3,
                str -> str.contains("Tiwari"),
                str -> str.replace("Tiwari", "Paramanik"));
        if(name!= null){
            student3.lastName = name;
        }

        System.out.println("Updated name:: " + student3.lastName);


        // Use of Supplier
        Student student4 = getStudent();


    }
}
