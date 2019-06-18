# javacertification


# Class
- https://guides.codepath.com/android/Lambda-Expressions

# Generic & Collections
- https://www.codejava.net/java-core/collections/18-java-collections-and-generics-best-practices




## Key points to code review

- **Use singleton in a smart way so it is well managed in multi thread**.

        public static class SingleTonClassHolder{
            public static MultyThreadSingleTon multyThreadSingleTon = new MultyThreadSingleTon();
        }
        
        public static MultyThreadSingleTon getInstance(){
                return SingleTonClassHolder.multyThreadSingleTon;
            }
        
- **Use static block to load any thing that is necessary to load during JVM loads the class in memory**.

        private static int count;
        
            static{
                // code in this static block will be executed when
                // the JVM loads the class into memory
                count =1;
            }
        
            public Counter(){
                //A constructor will be invoked when an instance of the
                //class is created, while the static block will be invoked when the JVM loads the corresponding class.
                count++;
            }
            
- **Java 8 has introduced *default* keyword which is susceptible to define class body inside interface**.

        public interface Interface1 {
        
            default void foo(){
                System.out.println("Interface1's foo");
            }
        }
        
- **If an interface contain only one abstract method then use @FunctionalInterface annotation.**

        @FunctionalInterface // that has one abstract method, so it will compile cleanly
        public interface AnnotationTest {
        
            abstract int foo();
        }

- **If 2 different interfaces contain method that has same name then it is called diamond problem and in that case we can use *super* *Interface1.super.method()* like this**.

        @Override
            public void foo() {
                //resolve the conflict manually by using the super keyword within the Diamond class to
                //explicitly mention which method definition to use:
                Interface1.super.foo();
            }
            
- **To initialize collections always use Interface type in this way**

        List<String> myList = new ArrayList<String>()
        
- **Use isEmpty() to check list size**

        if (!myList.isEmpty()) {
            // dos something if the list is not empty
        }   
            
- **Use enhanced for loop rather using classic for loop**

        for(Person p: personList)  {
        // dos something
        } 
        
- **Use stream api on collections** (**filter the list of people who is upper or equal to age 50**)


        List<Person> olderList = new ArrayList<>();
        for(Person person: l ){
             if(person.getAge() >= 50){
                 olderList.add(person);
             }
        }
        olderList.forEach(person -> System.out.println(person.getAge()));


        // now using stream api to filter the list using one conditions
        olderList = l.stream()
                     .filter(p->p.getAge()>= 50)
                     .collect(Collectors.toList());
        olderList.stream().forEach(person -> System.out.println(person.getAge()));  
        
       
- **Lambda expression need to use on methods**

        ()-> {block statement} 
        
  **Example**:
         
        // without Lamda expression
        State state1 = new State(new OnStateChangeListener() {
            @Override
            public int onStateChange() {
                return 5;
            }
        });

        // with Lambda expression
        State state11 = new State(()-> {
            return 5;
        });  
        
- **Method reference in lambda expression**  
There are four types of method references (assuming a class named Person with **getName()** method and a value named **p of that type**)

**Type**                                  **Method Reference**         **Lambda Expression**    **Traditional**

Reference to a static method              Math::square                  n-> Math.square(n)       void square(n)   {Math.square(n)}             
Reference to a constructor                Capital::new                  i-> new Capital(i)       public Capital(i) { new Capital(i)}
Reference to an instance method           Person::getName               p-> p.getName()          public String getName(Person p){p.getName()}