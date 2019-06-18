# javacertification


# Class
- https://guides.codepath.com/android/Lambda-Expressions

# Generic & Collections
- https://www.codejava.net/java-core/collections/18-java-collections-and-generics-best-practices




## Key points to code review

- Use singleton in a smart way so it is well managed in multi thread.
- Use static block to load any thing that is necessary to load during JVM loads the class in memory.
- Java 8 has introduced **default** keyword which is susceptible to define class body inside interface.
- If an interface contain only one abstract method then use @FunctionalInterface annotation.
- If 2 different interfaces contain method that has same name then it is called diamond problem and in that case we can use **super** *Interface1.super.method()*.

        @Override
            public void foo() {
                //resolve the conflict manually by using the super keyword within the Diamond class to
                //explicitly mention which method definition to use:
                Interface1.super.foo();
            }
            
- To initialize collections always use Interface type in this way

        List<String> myList = new ArrayList<String>()
        
- Use isEmpty() to check list size

        if (!myList.isEmpty()) {
            // dos something if the list is not empty
        }   
            
- Use enhanced for loop rather using classic for loop

        for(Person p: personList)  {
        // dos something
        }       