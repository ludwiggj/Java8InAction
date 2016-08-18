package lambdasinaction.chap3;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Lambdas {

  public static void process(Runnable r) {
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

  public static void main(String... args) {
    runnableRunout();

    int portNumber = 1337;
    Runnable r = () -> {
      System.out.println(portNumber);
      // Not allowed to modify local variable
      // portNumber = portNumber - 1;
    };
    r.run();

    // Not allowed. Variable used in lambda, so must be final or effectively final
    // portNumber += 1;

    // Quiz 3.1
    Runnable rr = () -> {
    };

    Supplier<String> eating = () -> "Raoul";

    Supplier<String> wii = () -> {
      return "Mario";
    };

    // This is incorrect...
    // Function<Integer, String> doobry = (Integer i) -> return "Alan" + i;

    // Alternatives
    Function<Integer, String> doobry = (Integer i) -> {
      return "Alan" + i;
    };
    Function<Integer, String> doobry2 = (Integer i) -> "Alan" + i;

    // This is incorrect...
    // UnaryOperator<String> un = (String s) -> {"Iron Man";}

    // Alternatives
    UnaryOperator<String> un = (String s) -> "Iron Man";
    UnaryOperator<String> un2 = (String s) -> {
      return "Iron Man";
    };
  }
}