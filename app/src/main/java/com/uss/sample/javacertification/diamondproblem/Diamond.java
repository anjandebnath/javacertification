package com.uss.sample.javacertification.diamondproblem;

public class Diamond implements Interface1, Interface2 {


    public static void main(String[] args){

        Diamond d = new Diamond();
        d.foo();

    }

    @Override
    public void foo() {
        //resolve the conflict manually by using the super keyword within the Diamond class to
        //explicitly mention which method definition to use:
        Interface1.super.foo();
    }

    // Points to Remember (PTR)
    //If a base class and a base interface define methods with the same signature, the method
    //definition in the class is used and the interface definition is ignored
}
