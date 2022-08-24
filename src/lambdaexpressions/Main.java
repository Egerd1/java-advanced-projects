package lambdaexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*
         * Challenge #1:
         * Write the following anonymous class as a lambda expression

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

         * */

        Runnable runnable = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };


         /*
        * Challenge #2:
        * Write the following method as a lambda expression. Don't worry about assigning it to anything
        * This method prints every second character in the string. e.g. "1234567890" -> "24680"

        public static String everySecondChar(String source) {
         StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }

            return returnVal.toString();
        }

        * */

        Function<String, String> lambdaFunction = (source) -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }

            return returnVal.toString();
        };


        /*
         * Challenge #3:
         * Right now, the function doesn't do anything. Write the code that will execute the
         * function with an argument "1234567890"
         * */
        System.out.println(lambdaFunction.apply("1234567890"));


        /*
         * Challenge #4:
         * Instead of executing this function directly, suppose we want to pass it to a method.
         * Write a method called everySecondCharacter that accepts the function as a parameter
         * and executes it with the argument "1234567890".
         *
         * It should return the result of the function.
         * */

        String result = everySecondCharacter(lambdaFunction);
        System.out.println(result);


        /*
         * Challenge #5:
         * Write a lambda expression that maps to java.util.Supplier interface.
         * This lambda should return the string "I love Java!"
         * Assign it to a variable called iLoveJava
         * */
        Supplier<String> iLoveJava = () -> "I love Java!";


        /*
         * Challenge #6:
         * As with Function, the Supplier won't do anything until we use it.
         * Use this supplier to assign the string "I love Java!" to a variable called supplierResult.
         * Then print the variable to the console.
         * */
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        /*
         * Challenge #7:
         * There are many interfaces in Java SDK, and sometimes we can use a lambda expression
         * instead of creating an instance that implements the interface we want to use.
         *
         * Question:
         * Given a specific interface, how we can tell whether we can map a lambda expression to it?
         * What's the criteria that has to be met?
         *
         * Answer:
         * The interface has to be a functional interface. It can have only a single method that must be implemented.
         * A functional interface can contain more than one method, but all the methods but one must have default implementations.
         *
         * Most of the time, the documentation for an interface will state whether it's a functional interface.
         *
         * Question:
         * With that in mind, can we use a lambda expression to represent an instance of the java.util.concurrent.Callable interface?
         * Hint: The callable documentation can be found here: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Callable.html
         *
         * Answer:
         * The Callable interface has a single method named call(). So we can use a lambda for it.
         *
         *
         * Question:
         * Is the java.util.Comparator interface a functional interface?
         * https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
         * Yes, despite containing over 10 methods, only one method has to be implemented, compare().
         */



        /*
        * Challenge #8:
        * Suppose we have the following list of the top 5 male and female names:
        List<String> topNames = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        * Write code to print the items in the list in sorted order, and with the first letter in each name upper-cased.
        * The name 'harry' should be printed as 'Harry' and should be printed after 'Emily' and before 'Isla'.
        * Use Lambda expressions wherever possible.
        *
        * */

        List<String> topNames = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        System.out.println("CHALLENGE 8");
        // capitalize first letters
        List<String> firstUppercaseList = new ArrayList<>();
        topNames.forEach(name -> firstUppercaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
        // sort all names
        firstUppercaseList.sort((s1, s2) -> s1.compareTo(s2));
        // print them out to console
        firstUppercaseList.forEach(name -> System.out.println(name));


        /*
         * Challenge #9:
         * Change the code so that it uses method references.
         * Remember that a method reference looks like Class::methodName
         * */
        System.out.println("CHALLENGE 9");
        firstUppercaseList.sort(String::compareTo);
        firstUppercaseList.forEach(System.out::println);


        /*
         * Challenge #10:
         * Now do the same thing (uppercase first letter, sort and print) using
         * a stream and a chain of stream operations.
         *
         * Hint: map, sorted and forEach in Streams API
         *
         * */
        System.out.println("CHALLENGE 10");
        topNames.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .sorted()
                .forEach(System.out::println);


        /*
         * Challenge #11:
         * Instead of printing out the sorted names, print out how many names begin with the letter 'A'
         *
         * Hints:
         * - You'll have to modify the stream chain
         * - You'll have to add another statement to print the number of items. You can't print it as part of the stream chain.
         * */
        System.out.println("CHALLENGE 11");
        long namesBeginningWithA = topNames.stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println("Number of names beginning with A is: " + namesBeginningWithA);


        /*
        * Challenge #12:
        * Let's go back to the previous version of the code, when we were printing out the sorted names (Challenge #10).
        * Let's suppose we want to debug what's going on when the chain is executed.
        *
        * Instead of printing out the names at the end of the chain, maybe we're not sure if the code that uppercases the first letter
        * is working correctly.
        *
        * Let's use peek() to print out the names after the map() method has executed.
        *
        * What will the following code print to the console?
        *
        topNames
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo);

        * Answer: nothing. The chain doesn't contain a terminal operation.
        * Remember that stream chains are evaluated lazily. Nothing happens until a terminal operation is added to the chain.
        * At that point, the chain is executed.
        */

        System.out.println("CHALLENGE 12");
        topNames
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo);

        /*
         * Challenge #13:
         * Add a terminal operation to this chain so that the peek() call will execute.
         * */
        System.out.println("CHALLENGE 13");
        topNames
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());


    }


    public static String everySecondCharacter(Function<String, String> func) {
        return func.apply("1234567890");
    }
}
