# javacertification


# Class
- https://guides.codepath.com/android/Lambda-Expressions

# Generic & Collections
- https://www.codejava.net/java-core/collections/18-java-collections-and-generics-best-practices




## Key points to code review

## Class Design with access modifiers

- in fact, when no access modifier is specified, the member has **default** access.

- Note that **Default** access is also known as **package-protected access.**

- Private access is comparable to a safe deposit box room in a bank, which can only be accessed by a set of authorized
  personnel and safe deposit box owners.

- Protected and default accesses are comparable to the situation in an office where a conference room is
  accessible only to one department.
  
- One significant difference between **Protected** and **Default** access modifiers arises when 
  we talk about a **subclass belonging to another package than its superclass**.
  
  - protected member can be access within the package and outside the package but within the child classes by using **extends**.
  
    
- **Serialization is a mechanism of converting the state of an object into a byte stream. Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory.**

     ![](https://github.com/anjandebnath/javacertification/blob/master/pdf/serialize-deserialize-java.png)

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
            
- **Java 8 has introduced *default* keyword which is susceptible to define method body inside interface**.

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
        
        
## Generics in detail

- **Use Bounded Wildcards to Increase API Flexibility**

   - List is neither subtype nor a supertype of List. There's a special kind of parameterized type called a BOUNDED WILDCARD TYPE.
   
   - List of some subtype of E' can be written as List<? extends E> - **Upper bounded wildcards.**
   
   - List of some supertype of E' can be written as List<? super E> - **Lower bounded wildcards.**
   
        
   
   - To write the method that works on lists of **Number** and the **subtypes** of Number, such as **Integer**, **Double**, and **Float**, you would specify List<? extends Number>. 
   
   - To write the method that works on lists of **Integer** and the **supertypes** of Integer, such as **Integer**, **Number**, and **Object**, you would specify List<? super Integer>.
   
   
- **Use *Comparable* Interface to compare one object to other**

        public class Person implements Comparable<Person>{
        
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
            
        }

- **compare(obj1, obj2) is the method of the *Comparator* interface that is called on some object to compare two other objects** 

        The Comparator interface is typically used for sorting data structures (such as Collections.sort or Arrays.sort).     


                   
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
        
        
- **If an enum contains attributes and methods, their definition must always come after the list of constants in the enum and the list of constants must be terminated by a semicolon.**
        
        
- **Method reference in lambda expression**  
There are four types of method references (assuming a class named Person with **getName()** method and a value named **p of that type**)

|**Type**                                  | **Method Reference**        | **Lambda Expression**  | **Traditional**|
|---                                       |---                          | ---                    | ---            |
|Reference to a static method              |Math::square                 |n-> Math.square(n)      |void square(n)   {Math.square(n)}|            
|Reference to a constructor                |Capital::new                 |i-> new Capital(i)      |public Capital(i) { new Capital(i)}|
|Reference to an instance method           |Person::getName              |p-> p.getName()         |public String getName(Person p){p.getName()}|

     
 ## Lambda Expression methods in detail    
 
- **Built-in Functional Interfaces**

|**Functional Interface**         | **Brief Description**                                | **Common Use**         
|---                              |---                                                   | ---                    
|Predicate<T>                     |Checks a condition and returns a boolean              |filter(Predicate<? super E> filter)                 
|Consumer<T>                      |Takes an argument but returns nothing                 |foreach(Consumer<? super T> action)      
|Function<T, R>                   |Take an argument and return a result                  |map(Function<? super T, ? super R> function) 
|Supplier<T>                      |Takes nothing but returns a value to the caller       |generate(Supplier<? super T> supplier)


- **primitive versions of functional interfaces**


        instead of using
        
        Predicate<Integer> p = i -> i > 10;
        You can use
        
        IntPredicate p = i -> i > 10;



-**A BiFunction is similar to Function , but the difference is that it takes two arguments: it takes arguments of generic types T and U and returns an object of generic type R**.
        
        BiFunction<String, String, String> concatStr = (x, y) -> x + y;
        System.out.println("BiFunction:: "+concatStr.apply("hello ", "world"));  
        
        
-**UnaryOperator is a functional interface that receives a
           value of a certain type and returns a value of the same type.**    
           
           UnaryOperator<Integer> unary = v -> v * 10;
           // This means the same as the UnaryOperator above.
           Function<Integer, Integer> function = v -> v * 10;      
        
        
  ## Stream API in detail 
        
- We can `Extract` data from a stream and also we can `Search`  data from a stream.

  - **Extract data using peek() & map()**
  
        List<String> str = new ArrayList<>();
        str.add("the");
        str.add("good");
        str.add("bad");
        str.add("ugly");

        str.stream()
                .filter(s -> s.length() > 3)
                .peek(s -> System.out.println("Filtered value: " + s)) // peek()
                .map(s -> s.toUpperCase())                             // map()
                .peek(s -> System.out.println("Mapped value: " + s))
                .collect(Collectors.toList());
       
        
   
  - **Search data using findXxx() or xxxMatch()**
  
  1. `For searching operations findFirst() and findAny(), matching elements may not be present in the Stream, so they return Optional<T>`
  2. `The class java.util.Optional is a holder for value that can be null`
  3. `In parallel streams, findAny() is faster to use than findFirst()`
  4. `searching methods such as findFirst() are short-circuiting. Once the result is determined, the rest of the elements in the stream are
          not processed.`
      
  
          Optional<Integer> any = Stream.of(1, 10, 5, 3, 13, 20)
                  .filter(i -> i % 2 == 0)
                  .findAny(); //can return 10 or 20
  
          System.out.println("search of any "+any);
  
          OptionalInt first = IntStream.of(1, 10, 5, 3, 13, 20)
                  .filter(i -> i % 2 == 0)
                  .findFirst(); // return 10
  
          System.out.println("search of first "+first.getAsInt());
          
          
          
  5.     Optional<String> empty  = Optional.empty();
         Optional<String> string = Optional.of("Hello");
         Optional<String> empty2  = Optional.ofNullable(null); 
          
         
  - **Stream Data Methods**
  
        
        `max(), min(), count()`   
        
        Comparator<String> byLength = (s1, s2) -> Integer.compare( s1.length(), s2.length());
        Optional<String> max = Stream.of("hello","good bye", "black", "white", "good", "bad")
                .max(byLength); //returns "good bye"                            
        
  - **Stream Calculation Methods**
  
  
         `max(), min(), average(), sum()` 
         
         OptionalInt max = IntStream.of(1, 34, 667, 3, 32, 23).max(); // max() returns 667
         
  - **Sort a collection using Stream api**
  
  
          public static void main(String []args) {
          
              List words = Arrays.asList("follow your heart but take your brain with you".split(" "));
              words.stream()
              .distinct()     // to remove duplicates and get unique
              .sorted()
              .forEach(System.out::println);
          } 
          
  - **If you want to compare using multiple criteria, in Java 8 there's a new method to make this easily, Comparator.thenComparing()**
  
           
          Comparator<String> byLengthComparator = (s1, s2) -> Integer.compare( s1.length(), s2.length());
          Comparator<String> byLettersComparator = (s1, s2) -> s1.compareTo(s2);
          
          Stream.of("hello","good bye", "black", "white", "good", "bad")
                    .sorted(byLengthComparator.thenComparing(byLettersComparator))
                    .forEach(s -> System.out.println(s));    
                    
     
  - **Save result into collection using the collect method and group/partition data using the Collectors class**
  
  
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
  
          word.forEach(s1-> System.out.println(s1)); 
          
         
  - **Use of merge() and flatMap() methods of the Stream API**  
  
  `The flatMap() method operates on elements just like map() method. However, flatMap() flattens
   the streams that result from mapping each of its elements into one flat stream.`
   
   
   
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
  

 ## Exception and Assertion

 - The only situation where a finally block will not be executed, it's if the program exits abruptly
  (**either by calling System.exit() or by a fatal error that causes the process to abort**). 
  
  
- **Why do we need the throws clause? By looking at the throws clause, you can get a clear idea of what exceptions the method can
    throw.**  
    
- **A method can throw checked exceptions; the clause throw specifies these checked exceptions in the method signature.**    

    
        public class ThrowClause1 {
        
            public static void main(String []args) {
                Scanner consoleScanner = new Scanner(new File("integer.txt"));
                System.out.println("You typed the integer value: " + consoleScanner.nextInt());
            }
        }
        
        
   - This code will result in a compiler error of **unreported exception FileNotFoundException; must
   be caught or declared to be thrown**. If you look at the declaration of this Scanner method, you’ll see a
   throws clause:
   
        `public Scanner(File source) throws FileNotFoundException {`
        
   So, any method that invokes this constructor should either handle this exception or add a throws clause to declare that the method can throw this exception.    
   
- **While implementing a method, it is acceptable to either provide the throws clause
    listing the same exception type as the base method or a more specific type than
    the base method.**
    
    
        interface IntReader {
            int readIntFromFile() throws IOException;
        }   
        
        
        public class ThrowClause1 implements IntReader{
        
            @Override
            public int readIntFromFile() throws FileNotFoundException {
                return 0;
            }
        }
        
   - **Unchecked exceptions can still be added or removed from the contract when compared to the base class method’s throws clause.**
   
   
            @Override
            public int readIntFromFile() throws FileNotFoundException, NoSuchElementException {
                return 0;
            }    
    
   - NoSuchElementException can get thrown from the readIntFromFile() method. This exception is an unchecked exception.

- **Static initialization blocks cannot throw any checked exceptions**  
    Why? 
    
    `Remember that static initialization blocks are invoked when the class is loaded, so there is no
     way to handle the thrown exceptions in the caller. Further, there is no way to declare
     the checked exceptions in a throws clause (because they are blocks, not methods). ` 
     
     
- **An overriding method cannot declare more checked exceptions in the throws clause
    than the list of exceptions declared in the throws clause of the base method.**  
    
    
- **Use Autoclose resources with a try-with-resources statement**

  - It is a fairly common mistake by Java programmers to forget releasing resources, even in the finally block. 
  - Normally, a finally block is used to ensure that a resource is closed whether the try statement completes normally or not.
  - **However, Java 7 introduced the try-with-resources statement, which ensures that each resource is closed at the end of the statement.**
  
          public String fileOperation(String path){
              
              try (BufferedReader brr = new BufferedReader(new FileReader(path))) {
                  return brr.readLine();
              } catch (IOException e) {
                  e.printStackTrace();
              }
              
              return null;
          } 
          
  - **Can you provide try-with-resources statements without any explicit catch or finally blocks?** 
    Yes! Remember that a try block can be associated with a catch block, finally block, or both. 
    
  - **You may declare more than one resources in a try-with-resources statement, separated by a semicolon.**    
   
  
        try (BufferedReader br1 = new BufferedReader(new FileReader(path1)); 
              BufferedReader br2 = new BufferedReader(new FileReader(path2))) {
                String l1 =  br1.readLine();
                String l2 =  br2.readLine();
            }         
  
  
  
- We can create our own exceptions by extending the `java.lang.Exception` class..You can also extend from `RuntimeException`, to make your exception unchecked (so it doesn't have to be catched if you don't want to).   

- You can implement the **java.lang.AutoCloseable** or **java.lang.Closeable** interfaces in your own classes and use them with a try-with-resources block.    


        public class MyAutoCloseable implements AutoCloseable {
        
            @Override
            public void close() throws Exception {
                System.out.println("Closed!");
            }
        
            public void someMethod() {
                System.out.println("Doing something");
            }
        }
        
        
        
        public static void main(String []args) throws FileNotFoundException {
        
            try(MyAutoCloseable autoCloseable = new MyAutoCloseable()){
                  autoCloseable.someMethod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            } 
            
            
## Java SE Date and Time

- LocalDateTime uses the default time zone, but Instant doesn’t. It uses the Greenwich time 


        LocalDateTime is: 2019-06-25T15:59:51.802 
        Instant is: 2019-06-25T09:59:51.802Z 
        
                   
- Summary of the Instant , Period and Duration Class

|**Instant**                                              | **Period**                                           | **Duration**         
|---                                                      |---                                                   | ---                    
|Represents machine time starting from Unix Epoch         |Represents time in terms of Y,M & D                   |represents time in terms of H,M,S & Ns                 
|Typically used for **Timestamp**                         |used to difference between **LocalDate** Objects      |used to difference between **LocalTime** Objects      


- **ZoneId identifies a time zone**, such as Asia/Kolkata. Another class, **ZoneOffset, represents the time-zone
  offset from UTC/Greenwich**. For example, zone ID “Asia/Dhaka” has a zone offset of +06:00 (plus 6 hours) from UTC/Greenwich.            
            
 ## Java Concurrency 
 
- **Use Callable and Future with ExecutorService because Runnable does not return any value after execution.**
 
 
         ExecutorService executor = Executors.newSingleThreadExecutor();
 
         //The Callable interface is similar to Runnable,
         // they're both designed to be executed by another thread,
         // a Runnable however, does not return a result and cannot throw a checked exception.
         Callable c = () -> {
 
             int n=0;
             for (int i=0; i< 100; i++){n+= i;}
             return n;
 
         };
 
 
         Future<Long> future = executor.submit(c);
         try {
 
             Long result = future.get(); //waits for the thread to complete
             System.out.println(result);
 
         } catch (ExecutionException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         
         
- We can use `Atomic` that support lock-free and thread-safe programming on single variables. Among them, the `AtomicInteger` class is a wrapper class for an int value that allows it to be updated atomically. 
         
         AtomicInteger at = new AtomicInteger(0);    
         
- Fork/Join  framework

Good reference [link](https://www.pluralsight.com/guides/introduction-to-the-fork-join-framework)
  
  - The fork/join framework is available since Java 7, to make it easier to write parallel programs.
  - **Dividing** the task into smaller tasks is `forking`, and **merging** the results from the smaller tasks is `joining`.
  - The Fork/Join framework uses the **work-stealing algorithm**: when a worker thread completes its work and is free, it takes (or “steals”) work from other threads that are still busy doing some work.  
  
  
  -**It looks obvious to call fork() for both the subtasks (if you are splitting in two subtasks) and call join() two times. It is correct—but inefficient. Why?** 
  
          Well,basically you are creating more parallel tasks than are useful. In this case, the original
       thread will be waiting for the other two tasks to complete, which is inefficient
       considering task creation cost. That is why you call fork() once and call compute()
       for the second task. 
       
  
  - **The placement of fork() and join() calls are very important.**
   - For instance, let’s assume that you place the calls in the following order:
    
    
        first.fork();
        resultFirst = first.join();
        resultSecond = second.compute();
        
   - This usage is a serial execution of two tasks, since the second task starts executing
    only after the first is complete. Thus, it is **less efficient even than its sequential
    version** since this version also includes cost of the task creation.
    
  - Each worker thread in the Fork/Join framework has a work queue, which is implemented using a **Deque.**
  
  - **One of the main things to consider when implementing an algorithm using fork/join parallelism is choosing the threshold which determines whether a task will execute a sequential computation rather than forking parallel sub-tasks.**
  
  
  
  
  
  
  
  
  
  
  
  
  