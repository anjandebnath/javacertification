package com.uss.sample.javacertification.enumcl;

public class MainTest {

    public static void main(String []args){

        Colors color = Colors.BLUE;

        switch (color) {

            case RED:
                System.out.println("You have choose the color RED");
                break;
            case BLUE:
                System.out.println("You have choose the color BLUE");
                break;
            case BLACK:
                System.out.println("You have choose the color BLACK");
                break;
        }

        for (Colors c : Colors.values()) {
            System.out.println(c);
        }
    }
}
