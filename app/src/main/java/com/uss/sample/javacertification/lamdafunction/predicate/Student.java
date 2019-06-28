package com.uss.sample.javacertification.lamdafunction.predicate;

public class Student {

    public String firstName;
    public String lastName;
    public Double grade;
    public Double feeDiscount = 0.0;
    public Double baseFee = 20000.0;

    public Student(String firstName, String lastName,
                   Double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public void printFee(){

        if(feeDiscount > 0.0){
            Double newFee = baseFee - ((baseFee*feeDiscount)/100);
            System.out.println("The fee after discount: "+newFee);
        }else{
            System.out.println(" No discount is applicable");
        }

    }

}
