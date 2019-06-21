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
        
- **Method reference in lambda expression**  
There are four types of method references (assuming a class named Person with **getName()** method and a value named **p of that type**)

|**Type**                                  | **Method Reference**        | **Lambda Expression**  | **Traditional**|
|---                                       |---                          | ---                    | ---            |
|Reference to a static method              |Math::square                 |n-> Math.square(n)      |void square(n)   {Math.square(n)}|            
|Reference to a constructor                |Capital::new                 |i-> new Capital(i)      |public Capital(i) { new Capital(i)}|
|Reference to an instance method           |Person::getName              |p-> p.getName()         |public String getName(Person p){p.getName()}|



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
        
 
 ## Lambda Expression methods in detail       
        
        
        
        
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
  
                        