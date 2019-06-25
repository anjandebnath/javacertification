package com.uss.sample.javacertification.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateTime {

    public static void main(String args[]){


        // It requires minimum api level 26
        LocalDate today = LocalDate.now();
        System.out.println("Today's date is: " + today);


        // calculate visa expiry date
        long visaValidityDays = 180L;
        LocalDate currDate = LocalDate.now();
        System.out.println("My Visa expires on: " + currDate.plusDays(visaValidityDays));


        LocalTime currTime = LocalTime.now();
        System.out.println("Current time is: " + currTime);


        LocalDateTime localDateTime = LocalDateTime.now();
        // It will give the output as 2019-06-25T15:47:45.825
        //the character T stands for time, and it separates the date and time components.
        System.out.println("Today's date and current time is: " + localDateTime);
        System.out.println("The date component is: " + localDateTime.toLocalDate());
        System.out.println("The time component is: " + localDateTime.toLocalTime());

        Instant instant = Instant.now();
        System.out.println("LocalDateTime is: " + localDateTime + " \nInstant is: " + instant);





        System.out.println("My zone id is: " + ZoneId.systemDefault());




    }
}
