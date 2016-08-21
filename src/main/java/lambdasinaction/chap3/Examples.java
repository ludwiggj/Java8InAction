package lambdasinaction.chap3;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Examples {

  private static void process(Runnable r) {
    r.run();
  }

  private static void runnableRunout() {
    // Simple example
    Runnable r1 = () -> System.out.println("Hello!");

    process(r1);

    Runnable r2 = new Runnable() {
      public void run() {
        System.out.println("Hello World 2");
      }
    };

    process(r2);

    process(() -> System.out.println("Hello World 3"));
  }

  private static void quiz_3_1() {
    // Quiz 3.1
    Runnable r1 = () -> {
    };
  }

  private static void quiz_3_5() {
    // Quiz 3.5 - Why doesn't the following compile?
    // Compilation error: Target type of a lambda conversion must be an interface
    // Object o = () -> {System.out.println("Tricky example");};

    Runnable r2 = () -> {
      System.out.println("Tricky example");
    };
  }

  private static void usingLocalVariables() {
    // Using local variables

    int portNumber = 1337;

    Runnable r3 = () -> {
      System.out.println(portNumber);

      // Not allowed to modify local variable
      // Error: Variable used in lambda expression should be final or effectively final

      // portNumber = portNumber - 1;
    };
    r3.run();

    // Not allowed. Error: Variable used in lambda expression should be final or effectively final
    // portNumber += 1;
  }

  private static void methodReferences() {
    //  Quiz 3.6: Method references
    //  What are equivalent method references for the following lambda expressions?

    Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);

    // Equivalent
    Function<String, Integer> stringToIntegerRef = Integer::parseInt;

    BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);

    // Equivalent
    BiPredicate<List<String>, String> containsRef = List::contains;
  }

  public static void main(String... args) {
    runnableRunout();

    quiz_3_1();

    quiz_3_5();

    usingLocalVariables();

    methodReferences();
  }
}